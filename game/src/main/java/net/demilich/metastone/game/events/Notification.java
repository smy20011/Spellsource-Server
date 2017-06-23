package net.demilich.metastone.game.events;

import net.demilich.metastone.game.GameContext;
import net.demilich.metastone.game.targeting.EntityReference;

import java.io.Serializable;

/**
 * Represents a general notification from inside the {@link net.demilich.metastone.game.logic.GameLogic} that the
 * {@link GameContext} or players might be interested in.
 * <p>
 * Unlike a {@link GameEvent}, a notification does not say anything about having side effects or triggering other rules.
 */
public interface Notification extends Serializable {
	/**
	 * For visualization purposes, what is the source of this notification?
	 *
	 * @return A reference to the entity that is the visualizable source of this notification.
	 */
	EntityReference getSourceReference();

	/**
	 * For visualization purposes, what is the target of this notification?
	 *
	 * @return A reference to the entity that is the visualizable target of this notification.
	 */
	EntityReference getTargetReference();

	/**
	 * When true, indicates to processors of this notification that it belongs in the power history.
	 *
	 * @return {@code true} if this notification should be stored in the power history of the game where it occurred.
	 */
	boolean isPowerHistory();

	/**
	 * A user-renderable description of what occurred in this notification.
	 * @return
	 * @param context
	 * @param playerId
	 */
	String getDescription(GameContext context, int playerId);
}