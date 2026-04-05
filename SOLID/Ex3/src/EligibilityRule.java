/**
 * Common interface for all eligibility rules.
 * Each rule checks one specific condition and returns a reason string
 * if the student fails that rule, or null if they pass.
 */
public interface EligibilityRule {
    /**
     * Evaluate the student against this rule.
     * @return a failure reason string, or null if the student passes this rule
     */
    String evaluate(StudentProfile student);
}
