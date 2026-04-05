/**
 * WhatsApp notification sender.
 *
 * Original LSP issue: threw an exception if the phone didn't start with '+',
 * which tightened the precondition of the base contract. Callers had to
 * use instanceof checks or try-catch to handle WhatsApp differently.
 *
 * Fix: instead of throwing, we handle the validation failure gracefully by
 * printing an error message and logging the failure. This way, callers can
 * treat all NotificationSenders uniformly (true substitutability).
 *
 * Note: we keep the exact same error message text to match expected output.
 */
public class WhatsAppSender extends NotificationSender {

    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        // instead of throwing, handle invalid phone gracefully
        if (n.phone == null || !n.phone.startsWith("+")) {
            System.out.println("WA ERROR: phone must start with + and country code");
            audit.add("WA failed");
            return;
        }
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}
