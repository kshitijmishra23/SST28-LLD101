public class ExportResult {

    public final String contentType;
    public final byte[] bytes;

    public ExportResult(String contentType, byte[] bytes) {
        this.contentType = contentType;
        this.bytes = bytes;
    }
}