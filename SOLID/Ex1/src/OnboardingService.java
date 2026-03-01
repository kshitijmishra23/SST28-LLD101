import java.util.*;

public class OnboardingService {

    private final StudentRepository repo;
    private final StudentInputParser parser;
    private final StudentValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentRepository repo,
                             StudentInputParser parser,
                             StudentValidator validator,
                             OnboardingPrinter printer) {

        this.repo = repo;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {

        //Print input (delegated)
        printer.printInput(raw);

        //Parse
        Map<String, String> kv = parser.parse(raw);

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        //Validate
        List<String> errors =
                validator.validate(name, email, phone, program);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        //Generate ID
        String id = IdUtil.nextStudentId(repo.count());

        //Create record
        StudentRecord rec =
                new StudentRecord(id, name, email, phone, program);

        //Persist
        repo.save(rec);

        //Print success
        printer.printSuccess(rec, repo.count());
    }
}
