public class OnboardingService {

    private final StudentRepository repository;
    private final StudentInput_Parser parser;
    private final StudentValidator validator;
    private final ConsolePrinter printer;

    public OnboardingService(StudentRepository repository) {
        this.repository = repository;
        this.parser = new StudentInput_Parser();
        this.validator = new StudentValidator();
        this.printer = new ConsolePrinter();
    }

    public void registerFromRawInput(String raw) {

        printer.printInput(raw);

        StudentInput input = parser.parse(raw);

        ValidationResult result = validator.validate(input);

        if (!result.isValid()) {
            printer.printErrors(result);
            return;
        }

        String id = IdUtil.nextStudentId(repository.count());

        StudentRecord record = new StudentRecord(
                id,
                input.name,
                input.email,
                input.phone,
                input.program
        );

        repository.save(record);

        printer.printSuccess(id, repository.count(), record);
    }
}