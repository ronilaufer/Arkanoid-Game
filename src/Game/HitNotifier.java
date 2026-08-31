package Game;
/**
 * The {@code HitNotifier} interface should be implemented by objects
 * that can notify others about hit events.
 *
 * <p>Implementing classes allow adding and removing {@link HitListener}
 * instances which listen for hit events.</p>
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.
    /**
     * Adds a hit listener to the list of listeners that will be notified
     * when a hit event occurs.
     *
     * @param hl the {@link HitListener} to add
     */
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.
    /**
     * Removes a hit listener from the list of listeners so it will no longer
     * be notified of hit events.
     *
     * @param hl the {@link HitListener} to remove
     */
    void removeHitListener(HitListener hl);
}