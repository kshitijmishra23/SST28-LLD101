public class StudentValidator {
    public ValidationResult validate(StudentInput input) {

        ValidationResult result = new ValidationResult();

        if (input.name.isBlank())
            result.add("name is required");

        if (input.email.isBlank() || !input.email.contains("@"))
            result.add("email is invalid");

        if (input.phone.isBlank() ||
                !input.phone.chars().allMatch(Character::isDigit))
            result.add("phone is invalid");

        if (!(input.program.equals("CSE") ||
                input.program.equals("AI") ||
                input.program.equals("SWE")))
            result.add("program is invalid");

        return result;
    }
}
