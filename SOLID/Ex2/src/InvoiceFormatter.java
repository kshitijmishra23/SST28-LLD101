import java.util.*;

/**
 * Responsible only for building the formatted invoice text.
 * No money calculations happen here — it just takes computed values and renders them.
 */
public class InvoiceFormatter {

    public String format(String invoiceId, Map<String, MenuItem> menu, List<OrderLine> lines,
                         double subtotal, double taxPct, double tax, double discount, double total) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invoiceId).append("\n");

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));

        return out.toString();
    }
}
