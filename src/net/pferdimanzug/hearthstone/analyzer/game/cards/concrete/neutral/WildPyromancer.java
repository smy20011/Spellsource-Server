package net.pferdimanzug.hearthstone.analyzer.game.cards.concrete.neutral;

import net.pferdimanzug.hearthstone.analyzer.game.cards.MinionCard;
import net.pferdimanzug.hearthstone.analyzer.game.cards.Rarity;
import net.pferdimanzug.hearthstone.analyzer.game.entities.heroes.HeroClass;
import net.pferdimanzug.hearthstone.analyzer.game.entities.minions.Minion;
import net.pferdimanzug.hearthstone.analyzer.game.spells.DamageSpell;
import net.pferdimanzug.hearthstone.analyzer.game.spells.TargetPlayer;
import net.pferdimanzug.hearthstone.analyzer.game.spells.desc.SpellDesc;
import net.pferdimanzug.hearthstone.analyzer.game.spells.trigger.AfterSpellCastedTrigger;
import net.pferdimanzug.hearthstone.analyzer.game.spells.trigger.SpellTrigger;
import net.pferdimanzug.hearthstone.analyzer.game.targeting.EntityReference;

public class WildPyromancer extends MinionCard {

	public WildPyromancer() {
		super("Wild Pyromancer", 3, 2, Rarity.RARE, HeroClass.ANY, 2);
		setDescription("After you cast a spell, deal 1 damage to ALL minions.");
	}

	@Override
	public int getTypeId() {
		return 225;
	}

	@Override
	public Minion summon() {
		Minion wildPyromancer = createMinion();
		SpellDesc damageSpell = DamageSpell.create(1);
		damageSpell.setTarget(EntityReference.ALL_MINIONS);
		SpellTrigger trigger = new SpellTrigger(new AfterSpellCastedTrigger(TargetPlayer.SELF), damageSpell);
		wildPyromancer.setSpellTrigger(trigger);
		return wildPyromancer;
	}
}
