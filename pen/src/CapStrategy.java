/**
 * Open/close by removing and replacing a cap.
 */
public class CapStrategy implements OpenCloseStrategy {

    @Override
    public void open() {
        System.out.println("  [cap] removing cap...");
    }

    @Override
    public void close() {
        System.out.println("  [cap] cap back on.");
    }
}
