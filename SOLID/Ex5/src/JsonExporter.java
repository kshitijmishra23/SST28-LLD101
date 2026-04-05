import java.nio.charset.StandardCharsets;

/**
 * JSON exporter.
 *
 * Original LSP issue: handled null request differently than other exporters
 * (returned empty result instead of following the same contract).
 *
 * Fix: null request handling follows the base contract — req must not be null.
 * If someone passes null, we let it fail naturally (NullPointerException),
 * same as other exporters would. Null body/title are still handled gracefully.
 */
public class JsonExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        // no more special null-request handling — base contract says req must not be null
        String title = escape(req.title);
        String body = escape(req.body);
        String json = "{\"title\":\"" + title + "\",\"body\":\"" + body + "\"}";
        return new ExportResult("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"");
    }
}
