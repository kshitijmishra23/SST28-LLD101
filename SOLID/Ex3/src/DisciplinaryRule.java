import java.util.*;
public class DisciplinaryRule implements EligibilityRule {
    public void apply(StudentProfile s, List<String> reasons) {
        if (s.disciplinaryFlag != LegacyFlags.NONE) {
            reasons.add("disciplinary flag present");
        }
    }
}