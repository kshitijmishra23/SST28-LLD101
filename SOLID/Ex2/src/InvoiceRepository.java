public interface InvoiceRepository {
    void save(String invId, String content);
    int countLines(String invId);
}
