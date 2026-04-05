/**
 * Open/close by clicking to extend or retract the tip.
 */
public class ClickStrategy implements OpenCloseStrategy {

    @Override
    public void open() {
        System.out.println("  [click] extending tip...");
    }

    @Override
    public void close() {
        System.out.println("  [click] retracting tip.");
    }
}
