public class SmsValidator implements NotificationValidator {
    public void validate(Notification n) {
        if (n.phone == null) {
            throw new IllegalArgumentException("phone required");
        }
    }
}
