package net.demilich.metastone.game.spells;

import com.github.fromage.quasi.fibers.Suspendable;
import net.demilich.metastone.game.GameContext;
import net.demilich.metastone.game.Player;
import net.demilich.metastone.game.entities.Actor;
import net.demilich.metastone.game.entities.Entity;
import net.demilich.metastone.game.spells.desc.SpellArg;
import net.demilich.metastone.game.spells.desc.SpellDesc;
import net.demilich.metastone.game.targeting.EntityReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class RelativeToTargetEffectSpell extends Spell {
	private static Logger logger = LoggerFactory.getLogger(AdjacentEffectSpell.class);

	@Override
	@Suspendable
	protected void onCast(GameContext context, Player player, SpellDesc desc, Entity source, Entity target) {
		checkArguments(logger, context, source, desc, SpellArg.SPELL1, SpellArg.SPELL2);
		EntityReference sourceReference = source != null ? source.getReference() : null;
		List<Actor> adjacentMinions = getActors(context, target);
		SpellDesc primary;
		SpellDesc secondary;
		if (desc.containsKey(SpellArg.SPELL) &&
				!desc.containsKey(SpellArg.SPELL1)
				&& !desc.containsKey(SpellArg.SPELL2)) {
			primary = (SpellDesc) desc.get(SpellArg.SPELL);
			secondary = primary;
		} else {
			primary = (SpellDesc) desc.get(SpellArg.SPELL1);
			if (primary != null) {
				context.getLogic().castSpell(player.getId(), primary, sourceReference, target.getReference(), true);
			}

			secondary = (SpellDesc) desc.get(SpellArg.SPELL2);
			if (secondary == null) {
				secondary = primary;
			}
		}

		for (Entity adjacent : adjacentMinions) {
			context.getLogic().castSpell(player.getId(), secondary, sourceReference, adjacent.getReference(), true);
		}
	}

	protected abstract List<Actor> getActors(GameContext context, Entity target);
}
