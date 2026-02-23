import java.util.*;

public class CafeteriaSystem {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceRepository repository;
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;
    private final PricingService pricingService;
    private final InvoiceFormatter formatter;

    private int invoiceSeq = 1000;

    public CafeteriaSystem(
            InvoiceRepository repository,
            TaxPolicy taxPolicy,
            DiscountPolicy discountPolicy,
            PricingService pricingService,
            InvoiceFormatter formatter) {

        this.repository = repository;
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
        this.pricingService = pricingService;
        this.formatter = formatter;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {

        String invId = "INV-" + (++invoiceSeq);

        double subtotal = pricingService.calculateSubtotal(lines, menu);

        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = discountPolicy.discountAmount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

        String printable = formatter.format(
                invId, lines, menu,
                subtotal, taxPct, tax, discount, total
        );

        System.out.print(printable);

        repository.save(invId, printable);
        System.out.println("Saved invoice: " + invId +
                " (lines=" + repository.countLines(invId) + ")");
    }
}