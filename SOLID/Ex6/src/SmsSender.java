public class SmsSender extends NotificationSender {

    public SmsSender(AuditLog audit) {
        super(audit, new SmsValidator());
    }

    @Override
    protected void doSend(Notification n) {
        System.out.println(
            "SMS -> to=" + n.phone +
            " body=" + n.body
        );

        audit.add("sms sent");
    }
}
