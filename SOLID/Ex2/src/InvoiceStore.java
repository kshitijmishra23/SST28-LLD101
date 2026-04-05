/**
 * Abstraction for saving invoices.
 */
public interface InvoiceStore {
    void save(String name, String content);
    int countLines(String name);
}
