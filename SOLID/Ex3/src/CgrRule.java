public class CgrRule implements EligibilityRule {

    private final double minCgr;

    public CgrRule(double minCgr) {
        this.minCgr = minCgr;
    }

    public String evaluate(StudentProfile s) {
        if (s.cgr < minCgr) {
            return "CGR below " + String.format("%.1f", minCgr);
        }
        return null;
    }
}