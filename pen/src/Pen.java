/**
 * A pen that writes using a refill and uses a pluggable open/close mechanism.
 *
 * Strategy pattern — the open/close behaviour is injected, so Pen doesn't
 * need to know whether it's a cap pen, click pen, or anything else.
 * Refill is composed in and can be swapped at runtime.
 */
public class Pen {
    private final OpenCloseStrategy mechanism;
    private Refill refill;

    public Pen(OpenCloseStrategy mechanism, Refill refill) {
        this.mechanism = mechanism;
        this.refill = refill;
    }

    /**
     * Writes the given text — opens the pen, prints, then closes.
     * Won't write if the refill is empty.
     */
    public void write(String text) {
        if (refill.isEmpty()) {
            System.out.println("  Refill is empty, can't write. Change refill first.");
            return;
        }
        mechanism.open();
        System.out.println("  Writing '" + text + "' in " + refill.getColor().label());
        mechanism.close();
    }

    public void changeRefill(Refill newRefill) {
        System.out.println("  Swapping refill: " + refill.getColor().label() + " -> " + newRefill.getColor().label());
        this.refill = newRefill;
    }

    public Color getColor() { return refill.getColor(); }
}
