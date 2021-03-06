package net.demilich.metastone.game.spells.custom;

import com.github.fromage.quasi.fibers.Suspendable;
import net.demilich.metastone.game.GameContext;
import net.demilich.metastone.game.Player;
import net.demilich.metastone.game.cards.Card;
import net.demilich.metastone.game.entities.Entity;
import net.demilich.metastone.game.spells.Spell;
import net.demilich.metastone.game.spells.desc.SpellArg;
import net.demilich.metastone.game.spells.desc.SpellDesc;
import net.demilich.metastone.game.spells.desc.trigger.EnchantmentDesc;
import net.demilich.metastone.game.targeting.Zones;
import net.demilich.metastone.game.utils.Attribute;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Adds an enchantment that is active in the player's hand or deck.
 */
public class AddTriggerToCardSpell extends Spell {

	private static Logger logger = LoggerFactory.getLogger(AddTriggerToCardSpell.class);

	@Override
	@Suspendable
	protected void onCast(GameContext context, Player player, SpellDesc desc, Entity source, Entity target) {
		if (!(target instanceof Card)) {
			logger.error("onCast {} {}: Use AddEnchantmentSpell instead.", context.getGameId(), source);
			return;
		}
		Card targetCard = (Card) target;

		Attribute triggerAttribute = desc.getAttribute();
		if (!Arrays.asList(Attribute.PASSIVE_TRIGGERS, Attribute.GAME_TRIGGERS, Attribute.DECK_TRIGGERS).contains(triggerAttribute)) {
			logger.error("onCast {} {}: Invalid Trigger Attribute", context.getGameId(), source);
			return;
		}

		EnchantmentDesc enchantmentDesc = (EnchantmentDesc) desc.get(SpellArg.TRIGGER);
		EnchantmentDesc[] enchantmentDescs = (EnchantmentDesc[]) desc.get(SpellArg.TRIGGERS);

		EnchantmentDesc[] enchantmentDescsToAdd;

		if (enchantmentDesc == null) {
			enchantmentDescsToAdd = enchantmentDescs;
		} else {
			enchantmentDescsToAdd = new EnchantmentDesc[]{enchantmentDesc};
		}

		if (enchantmentDescsToAdd == null || enchantmentDescsToAdd.length == 0) {
			logger.error("onCast {} {}: No triggers were specified.", context.getGameId(), source);
			return;
		}

		if (triggerAttribute == Attribute.GAME_TRIGGERS
				|| (targetCard.getZone().equals(Zones.HAND) && triggerAttribute == Attribute.PASSIVE_TRIGGERS)
				|| (targetCard.getZone().equals(Zones.DECK) && triggerAttribute == Attribute.DECK_TRIGGERS)) {
			for (EnchantmentDesc enchantmentDescToAdd : enchantmentDescsToAdd) {
				context.getLogic().processTriggerDesc(player, targetCard, enchantmentDescToAdd);
			}
		} else if (triggerAttribute == Attribute.PASSIVE_TRIGGERS) {
			if (targetCard.getPassiveTriggers() != null && targetCard.getPassiveTriggers().length > 0) {
				EnchantmentDesc[] oldEnchantmentDescs = targetCard.getPassiveTriggers();
				EnchantmentDesc[] newEnchantDescs = ArrayUtils.addAll(oldEnchantmentDescs, enchantmentDescsToAdd);
				targetCard.setAttribute(triggerAttribute, newEnchantDescs);
			} else targetCard.setAttribute(triggerAttribute, enchantmentDescsToAdd);
		} else if (triggerAttribute == Attribute.DECK_TRIGGERS) {
			if (targetCard.getDeckTriggers() != null && targetCard.getDeckTriggers().length > 0) {
				EnchantmentDesc[] oldEnchantmentDescs = targetCard.getDeckTriggers();
				EnchantmentDesc[] newEnchantDescs = ArrayUtils.addAll(oldEnchantmentDescs, enchantmentDescsToAdd);
				targetCard.setAttribute(triggerAttribute, newEnchantDescs);
			} else targetCard.setAttribute(triggerAttribute, enchantmentDescsToAdd);
		}
	}
}
