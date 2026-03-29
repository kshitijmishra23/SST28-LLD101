public abstract class Exporter {

    protected abstract FormatEncoder encoder();

    public ExportResult export(ExportRequest req) {

        // Base contract enforcement
        if (req == null) {
            return new ExportResult(
                    encoder().contentType(),
                    new byte[0]
            );
        }

        byte[] bytes = encoder().encode(req);

        return new ExportResult(
                encoder().contentType(),
                bytes
        );
    }
}