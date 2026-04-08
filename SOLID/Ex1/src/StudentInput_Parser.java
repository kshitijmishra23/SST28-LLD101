// The parser class


import java.util.*;

public class StudentInput_Parser {

    public StudentInput parse(String raw){
        HashMap<String , String> data = new HashMap<>();

        String[] parts = raw.split(";");
        for (String part : parts) {
            String[] keyValue = part.split("=", 2);
            if (keyValue.length == 2) {
                data.put(keyValue[0], keyValue[1]);
            }
        }

        return new StudentInput(
                data.get("name"),
                data.get("email"),
                data.get("phone"),
                data.get("program")
        );
    }
}
