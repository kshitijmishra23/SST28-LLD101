import java.util.*;


public class EligibilityEngine {
    private final List<EligibilityRule> rules;
    private final EligibilityStore store;

    public EligibilityEngine(List<EligibilityRule> rules, EligibilityStore store) {
        this.rules = rules;
        this.store = store;
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter printer = new ReportPrinter();
        EligibilityEngineResult result = evaluate(s);
        printer.print(s, result);
        store.save(s.rollNo, result.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        // Iterate rules in order; short-circuit on first failure (preserves original
        // behaviour).
        for (EligibilityRule rule : rules) {
            String reason = rule.check(s);
            if (reason != null) {
                reasons.add(reason);
                break;
            }
        }
        String status = reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE";
        return new EligibilityEngineResult(status, reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;

    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
