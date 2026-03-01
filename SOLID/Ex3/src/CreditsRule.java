import java.util.*;
public class CreditsRule implements EligibilityRule {
    private final int minCredits;

    public CreditsRule(int minCredits) {
        this.minCredits = minCredits;
    }

    public void apply(StudentProfile s, List<String> reasons) {
        if (s.earnedCredits < minCredits) {
            reasons.add("credits below " + minCredits);
        }
    }
}
