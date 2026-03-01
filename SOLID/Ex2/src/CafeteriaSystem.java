import java.util.*;

public class CafeteriaSystem {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceCalculator calculator;
    private final InvoicePrinter printer;
    private final InvoiceRepository repository;

    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceCalculator calculator,InvoicePrinter printer,InvoiceRepository repository) {
        this.calculator = calculator;
        this.printer = printer;
        this.repository = repository;
    }

    public void addToMenu(MenuItem item) {
        menu.put(item.id, item);
    }

    public void checkout(String customerType, List<OrderLine> lines) {

        String invId = "INV-" + (++invoiceSeq);

        double subtotal = 0.0;
        List<String> lineDetails = new ArrayList<>();

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;

            lineDetails.add(
                String.format("- %s x%d = %.2f\n",
                        item.name, l.qty, lineTotal)
            );
        }

        InvoiceTotals totals = calculator.calculate(customerType, subtotal, lines.size());

        
        String printable = printer.format(invId, lineDetails, totals);

        
        System.out.print(printable);

        
        repository.save(invId, printable);

        System.out.println(
                "Saved invoice: " + invId +
                " (lines=" + repository.countLines(invId) + ")"
        );
    }
}
