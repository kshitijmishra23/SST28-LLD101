import java.util.*;

public class OnboardingService {
    private final StudentRepository repo;
    private final RawInputParser parser = new RawInputParser();

    public OnboardingService(StudentRepository repo) { this.repo = repo; }
    private final StudentValidator validator = new StudentValidator();
    private final StudentPrinter printer = new StudentPrinter();
    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);
        ParseStudentData data = parser.parse(raw);

        String name = data.name;
        String email = data.email;
        String phone = data.phone;
        String program = data.program;

        // validation inline, printing inline
        List<String> errors = validator.validate(data);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        repo.save(rec);

        printer.printSuccess(rec, repo.count());
    }
}