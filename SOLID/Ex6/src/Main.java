/**
 * Main demo.
 *
 * After the LSP refactoring, we no longer need try-catch or instanceof checks.
 * Every NotificationSender can be used uniformly — if there's a channel-specific
 * issue (like WhatsApp needing E.164 format), the sender handles it gracefully.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender email = new EmailSender(audit);
        NotificationSender sms = new SmsSender(audit);
        NotificationSender wa = new WhatsAppSender(audit);

        // no more try-catch needed — all senders follow the same contract
        email.send(n);
        sms.send(n);
        wa.send(n);

        System.out.println("AUDIT entries=" + audit.size());
    }
}
