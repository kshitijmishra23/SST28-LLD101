import java.nio.charset.StandardCharsets;

/**
 * PDF exporter.
 *
 * Instead of throwing for content > 20 chars (which tightened the base
 * contract's precondition — an LSP violation), we now return an error result.
 * Callers check isSuccess() instead of catching exceptions.
 */
public class PdfExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        if (req.body != null && req.body.length() > 20) {
            return ExportResult.fail("PDF cannot handle content > 20 chars");
        }
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
