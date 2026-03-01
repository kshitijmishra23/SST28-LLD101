public class InvoiceCalculator {

    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;

    public InvoiceCalculator(TaxPolicy taxPolicy, DiscountPolicy discountPolicy) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
    }

    public InvoiceTotals calculate(String customerType,double subtotal,int distinctLines) {

        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = discountPolicy.discountAmount(customerType, subtotal, distinctLines);

        double total = subtotal + tax - discount;

        return new InvoiceTotals(subtotal, taxPct, tax, discount, total);
    }
}
