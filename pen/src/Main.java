/**
 * Quick demo — cap pen vs click pen, refill swap, and empty-refill guard.
 */
public class Main {
    public static void main(String[] args) {

        // --- cap pen ---
        System.out.println("=== Cap Pen ===");
        Pen capPen = new Pen(new CapStrategy(), new Refill(Color.BLUE));
        capPen.write("Hello");

        capPen.changeRefill(new Refill(Color.RED));
        capPen.write("World");

        // --- click pen ---
        System.out.println("\n=== Click Pen ===");
        Pen clickPen = new Pen(new ClickStrategy(), new Refill(Color.BLACK));
        clickPen.write("Design Patterns");

        clickPen.changeRefill(new Refill(Color.GREEN));
        clickPen.write("are fun");

        // --- empty refill guard ---
        System.out.println("\n=== Empty Refill ===");
        Refill dying = new Refill(Color.BLUE);
        dying.exhaust();
        Pen deadPen = new Pen(new CapStrategy(), dying);
        deadPen.write("this won't print");

        deadPen.changeRefill(new Refill(Color.BLACK));
        deadPen.write("back in action");
    }
}
