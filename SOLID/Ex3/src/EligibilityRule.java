import java.util.*;
public interface EligibilityRule {
    void apply(StudentProfile s, List<String> reasons);
}
