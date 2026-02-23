import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    @Override
    protected ExportResult doExport(ExportRequest req) {

        // CSV encoding rule: replace line breaks and commas
        String body = req.body
                .replace("\n", " ")
                .replace(",", " ");

        String csv = "title,body\n" + req.title + "," + body + "\n";

        return new ExportResult("text/csv",
                csv.getBytes(StandardCharsets.UTF_8));
    }
}