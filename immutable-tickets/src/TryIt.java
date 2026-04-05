import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demo showing immutability in action:
 * - Tickets are created via Builder
 * - "Updates" produce new ticket instances (originals unchanged)
 * - External tag list modifications have no effect on the ticket
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        // Create a ticket via Builder (validation happens in build())
        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // "Updates" return NEW ticket instances — original is unchanged
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nOriginal (unchanged): " + t);
        System.out.println("After assign + escalate (new object): " + escalated);

        // External tag mutation has no effect — list is unmodifiable
        List<String> tags = escalated.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("BUG: external mutation should not work!");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nExternal tag mutation blocked (UnsupportedOperationException)");
        }
        System.out.println("Ticket tags still safe: " + escalated.getTags());
    }
}
