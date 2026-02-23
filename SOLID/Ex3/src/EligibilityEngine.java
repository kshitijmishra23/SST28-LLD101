import java.util.*;

public class EligibilityEngine {

    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store,
                             List<EligibilityRule> rules) {
        this.store = store;
        this.rules = rules;
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {

        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        for (EligibilityRule rule : rules) {
            String result = rule.evaluate(s);
            if (result != null) {
                status = "NOT_ELIGIBLE";
                reasons.add(result);
                break; // preserve original else-if behavior
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }
}

// ✅ Keep this class here
class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;

    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}