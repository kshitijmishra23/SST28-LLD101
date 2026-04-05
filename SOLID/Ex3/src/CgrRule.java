/**
 * Fails if the student's CGR is below the minimum threshold.
 */
public class CgrRule implements EligibilityRule {
    private final double minCgr;

    public CgrRule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public String evaluate(StudentProfile student) {
        if (student.cgr < minCgr) {
            return "CGR below " + String.format("%.1f", minCgr);
        }
        return null;
    }
}
