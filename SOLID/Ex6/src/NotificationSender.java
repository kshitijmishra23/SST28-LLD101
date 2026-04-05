/**
 * Base class for all notification senders.
 *
 * Contract:
 *   Preconditions — caller must provide:
 *     - n must not be null
 *     - n.body must not be null
 *     - n.email and n.phone should be provided, but if a specific channel
 *       cannot deliver (e.g., invalid phone format for WhatsApp), it should
 *       handle the error gracefully (report it, don't throw).
 *
 *   Postconditions — every sender guarantees:
 *     - The send attempt is always logged in the audit trail
 *     - No RuntimeException is thrown for valid Notification objects
 *
 * Subtypes must not tighten preconditions beyond what the base contract defines.
 * If a channel has format requirements (like WhatsApp needing E.164 numbers),
 * it should handle invalid input gracefully and report the failure.
 */
public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) { this.audit = audit; }

    public abstract void send(Notification n);
}
