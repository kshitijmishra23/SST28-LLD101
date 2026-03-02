public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        // LSP violation: tightens precondition
        if (n.phone == null || !n.phone.startsWith("+")) {
            throw new IllegalArgumentException("phone must start with + and country code");
        }
        
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}
