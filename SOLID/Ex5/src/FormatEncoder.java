import java.nio.charset.StandardCharsets;

public interface FormatEncoder {

    String contentType();

    byte[] encode(ExportRequest req);

}