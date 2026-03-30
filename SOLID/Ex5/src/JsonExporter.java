public class JsonExporter extends Exporter {

    private final FormatEncoder encoder = new JsonEncoder();

    @Override
    protected FormatEncoder encoder() {
        return encoder;
    }
}