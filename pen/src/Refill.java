/**
 * An ink refill that holds a fixed color.
 * Immutable — swap refills instead of mutating.
 */
public class Refill {
    private final Color color;
    private boolean empty;

    public Refill(Color color) {
        this.color = color;
        this.empty = false;
    }

    public Color getColor() { return color; }

    public boolean isEmpty() { return empty; }

    /** Marks the refill as used up. */
    public void exhaust() { this.empty = true; }
}
