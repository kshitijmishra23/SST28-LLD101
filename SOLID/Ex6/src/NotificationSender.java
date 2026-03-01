public abstract class NotificationSender {

    protected final AuditLog audit;
    private final NotificationValidator validator;

    protected NotificationSender(AuditLog audit,
                                 NotificationValidator validator) {
        this.audit = audit;
        this.validator = validator;
    }

    public final void send(Notification n) {
        if (n == null)
            throw new IllegalArgumentException("notification cannot be null");

        validator.validate(n);
        doSend(n);
    }

    protected abstract void doSend(Notification n);
}
