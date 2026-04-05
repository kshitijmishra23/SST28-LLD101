import java.util.*;

/**
 * Orchestrates the onboarding workflow by delegating to focused components.
 * No longer does parsing, validation, formatting, or persistence directly.
 */
public class OnboardingService {
    private final StudentRepository repository;
    private final StudentDataParser parser;
    private final StudentValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentRepository repository) {
        this.repository = repository;
        this.parser = new StudentDataParser();
        this.validator = new StudentValidator();
        this.printer = new OnboardingPrinter();
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        // step 1 — parse the raw string into structured data
        Map<String, String> data = parser.parse(raw);

        // step 2 — validate the parsed fields
        List<String> errors = validator.validate(data);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        // step 3 — build the student record
        String name = data.getOrDefault("name", "");
        String email = data.getOrDefault("email", "");
        String phone = data.getOrDefault("phone", "");
        String program = data.getOrDefault("program", "");

        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        // step 4 — persist
        repository.save(rec);

        // step 5 — confirm
        printer.printSuccess(id, repository.count(), rec);
    }
}
