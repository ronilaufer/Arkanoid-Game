package Sprite;

import Game.Game;
import biuoop.DrawSurface;
/**
 * The {@code Sprite} interface represents an object that can be drawn on the screen
 * and can respond to the passage of time (such as for animation or game logic).
 * All objects that want to be part of the game's drawing and update loop should
 * implement this interface.
 */
public interface Sprite {
    // draw the sprite to the screen
    /**
     * Draws the sprite onto the given drawing surface.
     *
     * @param d the {@code DrawSurface} on which the sprite should be drawn
     */
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed
    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is typically called once per frame and allows the sprite
     * to update its state, such as position or appearance.
     */
    void timePassed();
    /**
     * Adds the sprite to the specified game.
     * This method should register the sprite so that it will be drawn and updated
     * as part of the game loop.
     *
     * @param g the {@code Game} to which the sprite should be added
     */
    void addToGame(Game g);
}
