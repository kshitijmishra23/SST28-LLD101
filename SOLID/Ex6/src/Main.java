import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();
        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        List<NotificationSender> senders = List.of(
                new EmailSender(audit),
                new SmsSender(audit),
                new WhatsAppSender(audit)
        );

        // No try-catch needed! LSP is satisfied.
        for (NotificationSender sender : senders) {
            sender.send(n);
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}