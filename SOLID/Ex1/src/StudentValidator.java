import java.util.*;

public class StudentValidator {
    List<String> validate(ParseStudentData data){

        List<String> errors = new ArrayList<>();
        if (data.name.isBlank())
            errors.add("name is required");
        if (data.email.isBlank() || !data.email.contains("@"))
            errors.add("email is invalid");
        if (data.phone.isBlank() || !data.phone.chars().allMatch(Character::isDigit))
            errors.add("phone is invalid");
        if (!(data.program.equals("CSE") || data.program.equals("AI") || data.program.equals("SWE")))
            errors.add("data.program is invalid");
        return errors;
    }
}