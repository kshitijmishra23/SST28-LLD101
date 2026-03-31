public class CsvExporter extends Exporter {

    private final FormatEncoder encoder = new CsvEncoder();

    @Override
    protected FormatEncoder encoder() {
        return encoder;
    }
}