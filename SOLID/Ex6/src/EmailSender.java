/**
 * Email notification sender.
 *
 * Original LSP issue: silently truncated messages at 40 chars, changing the meaning
 * of the notification without the caller knowing.
 *
 * Fix: sends the full body. Email has no practical length limitation for our use case,
 * so there's no reason to truncate. This makes it consistent with the base contract.
 * (For the given sample input, body.length() < 40 anyway, so output is unchanged.)
 */
public class EmailSender extends NotificationSender {

    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        // no more silent truncation — send the full message
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
        audit.add("email sent");
    }
}
