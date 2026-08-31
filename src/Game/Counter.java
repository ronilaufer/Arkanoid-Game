package Game;

/**
 * The {@code Counter} class is a simple utility to keep track of a count,
 * allowing increments and decrements by specified amounts.
 */
public class Counter {
    private int count;

    /**
     * Constructs a new {@code Counter} initialized to zero.
     */
    public Counter() {
        count = 0;
    }
    /**
     * Increases the counter's value by the specified amount.
     *
     * @param number the amount to increase the counter by
     */
    public void increase(int number) {
        count += number;
    }
    /**
     * Decreases the counter's value by the specified amount.
     *
     * @param number the amount to decrease the counter by
     */
    public void decrease(int number) {
        count -= number;
    }
    /**
     * Returns the current value of the counter.
     *
     * @return the current count value
     */
    public int getValue() {
        return count;
    }
}

