import java.util.*;

/**
 * Iterates over a list of pluggable rules and builds the eligibility result.
 * Adding a new rule = create a class + add it to the list. No conditionals to edit.
 *
 * Note: the original code was an if/else-if chain, so only the first failing rule
 * produced a reason. We preserve that behavior here by stopping at the first failure.
 */
public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store) {
        this.store = store;

        // wire the rules in the same order as the original if-else chain
        this.rules = new ArrayList<>();
        rules.add(new DisciplinaryFlagRule());
        rules.add(new CgrRule(8.0));
        rules.add(new AttendanceRule(75));
        rules.add(new CreditsRule(20));
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

        // iterate through each rule — stop at first failure (matches original behavior)
        for (EligibilityRule rule : rules) {
            String failReason = rule.evaluate(s);
            if (failReason != null) {
                status = "NOT_ELIGIBLE";
                reasons.add(failReason);
                break; // original was if/else-if, so only one reason at a time
            }
        }

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
