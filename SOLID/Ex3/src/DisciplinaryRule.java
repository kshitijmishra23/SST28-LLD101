public class DisciplinaryRule implements EligibilityRule {

    public String evaluate(StudentProfile s) {
        if (s.disciplinaryFlag != LegacyFlags.NONE) {
            return "disciplinary flag present";
        }
        return null;
    }
}