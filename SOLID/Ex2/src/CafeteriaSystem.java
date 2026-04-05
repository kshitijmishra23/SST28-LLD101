import java.util.*;

/**
 * Orchestrates the checkout flow by delegating to focused components.
 * It no longer handles tax/discount logic, formatting, or persistence details.
 */
public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final TaxCalculator taxCalc;
    private final DiscountCalculator discountCalc;
    private final InvoiceFormatter formatter;
    private final InvoiceStore store;
    private int invoiceSeq = 1000;

    public CafeteriaSystem() {
        this.taxCalc = new TaxRules();
        this.discountCalc = new DiscountRules();
        this.formatter = new InvoiceFormatter();
        this.store = new FileStore();
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        // compute subtotal
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            subtotal += item.price * l.qty;
        }

        // delegate tax and discount calculations
        double taxPct = taxCalc.getTaxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = discountCalc.getDiscount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        // format & print
        String printable = formatter.format(invId, menu, lines, subtotal, taxPct, tax, discount, total);
        System.out.print(printable);

        // persist
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
