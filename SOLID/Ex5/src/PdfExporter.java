public class PdfExporter extends Exporter {

    private final FormatEncoder encoder = new PdfEncoder();

    @Override
    protected FormatEncoder encoder() {
        return encoder;
    }
}