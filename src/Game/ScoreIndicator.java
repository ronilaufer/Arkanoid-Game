package Game;

import Sprite.Sprite;
import biuoop.DrawSurface;

/**
 * The {@code ScoreIndicator} class is a {@link Sprite} that displays the current score on the screen.
 * It renders a rectangle at the top of the screen and shows the current score value as text.
 * This class is typically added to the game as a visual element to give feedback to the player.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructs a {@code ScoreIndicator} with the specified score counter.
     *
     * @param score the {@link Counter} object that holds the current score to be displayed
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawRectangle(0, 0, 800, 20);
        d.drawText(370, 15, "Score: " + score.getValue(), 18);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
