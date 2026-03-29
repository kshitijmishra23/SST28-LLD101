import java.nio.charset.StandardCharsets;

public class PdfEncoder implements FormatEncoder {

    @Override
    public String contentType() {
        return "application/pdf";
    }

    @Override
    public byte[] encode(ExportRequest req) {

        String body = req.body;

        // Preserve previous observable behavior
        if (body != null && body.length() > 20) {
            throw new IllegalArgumentException("PDF cannot handle content > 20 chars");
        }

        String fakePdf = "PDF(" + req.title + "):" + body;

        return fakePdf.getBytes(StandardCharsets.UTF_8);
    }
}