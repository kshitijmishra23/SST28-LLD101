public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        // LSP smell: truncates silently, changing meaning
        String body = n.body;
        if (body.length() > 40) body = body.substring(0, 40);
        
        // Imagine this is a real email sender instead of a println.
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + body);
        audit.add("email sent");
    }
}
