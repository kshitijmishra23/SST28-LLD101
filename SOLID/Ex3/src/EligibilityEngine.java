import java.util.*;

public class EligibilityEngine {

    private final List<EligibilityRule> rules;
    private final FakeEligibilityStore store;

    public EligibilityEngine(List<EligibilityRule> rules,
                             FakeEligibilityStore store) {
        this.rules = rules;
        this.store = store;
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {

        List<String> reasons = new ArrayList<>();

        for (EligibilityRule rule : rules) {
            rule.apply(s, reasons);
        }

        String status =
                reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE";

        return new EligibilityEngineResult(status, reasons);
    }
}
