import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handles all the validation rules for student data.
 * Returns a list of error messages (empty = valid).
 */
public class StudentValidator {

    private static final List<String> ALLOWED_PROGRAMS = List.of("CSE", "AI", "SWE");

    public List<String> validate(Map<String, String> data) {
        List<String> errors = new ArrayList<>();

        String name = data.getOrDefault("name", "");
        String email = data.getOrDefault("email", "");
        String phone = data.getOrDefault("phone", "");
        String program = data.getOrDefault("program", "");

        if (name.isBlank()) errors.add("name is required");
        if (email.isBlank() || !email.contains("@")) errors.add("email is invalid");
        if (phone.isBlank() || !phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
        if (!ALLOWED_PROGRAMS.contains(program)) errors.add("program is invalid");

        return errors;
    }
}
