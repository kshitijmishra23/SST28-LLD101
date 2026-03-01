import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    @Override
    public ExportResult doExport(ExportRequest req) {

        String body = req.body.replace("\"", "\"\"");
        String csv = "title,body\n\"" + req.title + "\",\"" + body + "\"\n";

        return new ExportResult(
                "text/csv",
                csv.getBytes(StandardCharsets.UTF_8)
        );
    }
}
