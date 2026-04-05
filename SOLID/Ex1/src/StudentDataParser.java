import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Responsible only for turning the raw key=value input string
 * into a structured map that the rest of the pipeline can use.
 */
public class StudentDataParser {

    public Map<String, String> parse(String raw) {
        Map<String, String> kv = new LinkedHashMap<>();
        String[] parts = raw.split(";");
        for (String p : parts) {
            String[] t = p.split("=", 2);
            if (t.length == 2) {
                kv.put(t[0].trim(), t[1].trim());
            }
        }
        return kv;
    }
}
