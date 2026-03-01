import java.util.*;
public class InvoicePrinter {

    public String format(String invId,List<String> lineDetails,InvoiceTotals totals) {

        StringBuilder out = new StringBuilder();

        out.append("Invoice# ").append(invId).append("\n");

        for (String l : lineDetails) {
            out.append(l);
        }

        out.append(String.format("Subtotal: %.2f\n", totals.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", totals.taxPct, totals.tax));
        out.append(String.format("Discount: -%.2f\n", totals.discount));
        out.append(String.format("TOTAL: %.2f\n", totals.total));

        return out.toString();
    }
}
