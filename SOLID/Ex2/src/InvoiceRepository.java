public interface InvoiceRepository {
    void save(String name, String content);
    int countLines(String name);
}