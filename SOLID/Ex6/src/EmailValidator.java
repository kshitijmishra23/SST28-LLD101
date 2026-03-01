public class EmailValidator implements NotificationValidator {
    public void validate(Notification n) {
        if (n.email == null) {
            throw new IllegalArgumentException("email required");
        }
    }
}
