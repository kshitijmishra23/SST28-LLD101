import java.util.*;
public class AttendanceRule implements EligibilityRule {
    private final int minAttendance;

    public AttendanceRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    public void apply(StudentProfile s, List<String> reasons) {
        if (s.attendancePct < minAttendance) {
            reasons.add("attendance below " + minAttendance);
        }
    }
}
