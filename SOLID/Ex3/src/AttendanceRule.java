/**
 * Fails if the student's attendance is below the minimum percentage.
 */
public class AttendanceRule implements EligibilityRule {
    private final int minAttendance;

    public AttendanceRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    @Override
    public String evaluate(StudentProfile student) {
        if (student.attendancePct < minAttendance) {
            return "attendance below " + minAttendance;
        }
        return null;
    }
}
