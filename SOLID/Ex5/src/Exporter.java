public abstract class Exporter {

    public final ExportResult export(ExportRequest req) {

        if (req == null)
            throw new IllegalArgumentException("request cannot be null");

        String title = req.title == null ? "" : req.title;
        String body = req.body == null ? "" : req.body;

        return doExport(new ExportRequest(title, body));
    }

    public abstract ExportResult doExport(ExportRequest req);
}
