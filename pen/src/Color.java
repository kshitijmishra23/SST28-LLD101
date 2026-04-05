/**
 * Supported ink colors for a refill.
 */
public enum Color {
    RED,
    BLUE,
    BLACK,
    GREEN;

    /** Lowercase label for display. */
    public String label() {
        return name().toLowerCase();
    }
}
