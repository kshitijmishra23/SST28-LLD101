import java.util.*;

public class ValidationResult {
    private final List<String> errors = new ArrayList<>();

    public void add(String error) {
        errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }
}
