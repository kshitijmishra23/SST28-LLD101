/**
 * SMS notification sender.
 *
 * Original LSP issue: silently ignores the subject field. The base contract
 * doesn't require all fields to be used by every channel, but this behavior
 * should be documented.
 *
 * Fix: SMS by nature has no concept of "subject", so not using it is fine —
 * it's a weaker postcondition (which is allowed by LSP), not a tighter precondition.
 * We just document it clearly.
 */
public class SmsSender extends NotificationSender {

    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        // SMS doesn't support subject — this is documented channel behavior, not an LSP violation
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
    }
}
