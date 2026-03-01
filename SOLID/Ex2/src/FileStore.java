import java.util.*;

public class FileStore implements InvoiceRepository {
    private final Map<String, String> files = new HashMap<>();

    public void save(String name, String content) { files.put(name, content); }
    public int countLines(String name) {
        String c = files.getOrDefault(name, "");
        if (c.isEmpty()) return 0;
        return c.split("\n").length;
    }
}
