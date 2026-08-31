package Sprite;

import biuoop.DrawSurface;

import java.util.List;
import java.util.ArrayList;
/**
 * The {@code SpriteCollection} class manages a list of {@link Sprite} objects.
 * It allows adding sprites to the collection and provides methods to update
 * and render all sprites in the collection.
 */
public class SpriteCollection {
    private List<Sprite> spriteList = new ArrayList<Sprite>();
    /**
     * Adds a sprite to the collection.
     *
     * @param s the {@code Sprite} to add
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }
    /**
     * Calls the {@code timePassed()} method on all sprites in the collection.
     * This method is typically called once per frame to notify all sprites
     * that a unit of time has passed, allowing them to update their internal state.
     */
    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        List<Sprite> copy = new ArrayList<Sprite>(this.spriteList);
        for (Sprite s : copy) {
            s.timePassed();
        }
    }
    /**
     * Calls the {@code drawOn(DrawSurface)} method on all sprites in the collection.
     * This method is used to render all the sprites onto the given drawing surface.
     *
     * @param d the {@code DrawSurface} to draw the sprites on
     */
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spriteList) {
            s.drawOn(d);
        }
    }

    /**
     * Removes the specified sprite from the list of sprites.
     *
     * @param s the {@link Sprite} to be removed
     */
    public void deleteSprite(Sprite s) {
        spriteList.remove(s);
    }
}
