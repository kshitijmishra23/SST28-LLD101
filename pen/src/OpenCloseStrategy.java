/**
 * Strategy for how a pen is opened before writing and closed after.
 *
 * Different pen types have different mechanisms — cap, click, twist, etc.
 * Adding a new mechanism means implementing this interface, not changing Pen.
 */
public interface OpenCloseStrategy {
    void open();
    void close();
}
