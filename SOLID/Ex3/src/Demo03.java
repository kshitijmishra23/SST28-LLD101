import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Placement Eligibility ===");

        StudentProfile s =
                new StudentProfile("23BCS1001",
                        "Ayaan",
                        8.10,
                        72,
                        18,
                        LegacyFlags.NONE);

        List<EligibilityRule> rules = List.of(
                new DisciplinaryRule(),
                new CgrRule(8.0),
                new AttendanceRule(75),
                new CreditsRule(20)
        );

        EligibilityEngine engine =
                new EligibilityEngine(rules,
                        new FakeEligibilityStore());

        engine.runAndPrint(s);
    }
}
