public class InvoiceTotals {
    public final double subtotal;
    public final double taxPct;
    public final double tax;
    public final double discount;
    public final double total;

    public InvoiceTotals(double subtotal,double taxPct,double tax,double discount,double total) {
        this.subtotal = subtotal;
        this.taxPct = taxPct;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }
}
