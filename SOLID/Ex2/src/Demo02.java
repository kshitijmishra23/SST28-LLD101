import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Cafeteria Billing ===");

        //Create policies
        TaxPolicy taxPolicy = new DefaultTaxPolicy();
        DiscountPolicy discountPolicy = new DefaultDiscountPolicy();

        //Create calculator
        InvoiceCalculator calculator =
                new InvoiceCalculator(taxPolicy, discountPolicy);

        //Create printer
        InvoicePrinter printer = new InvoicePrinter();

        //Create repository
        InvoiceRepository repository = new FileStore();

        //Inject into CafeteriaSystem
        CafeteriaSystem sys =
                new CafeteriaSystem(calculator, printer, repository);

        //Setup menu
        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        //Create order
        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );

        //Checkout
        sys.checkout("student", order);
    }
}
