public class TreasurerTool implements FinanceOperations {

    private final BudgetLedger ledger;

    public TreasurerTool(BudgetLedger ledger) {
        this.ledger = ledger;
    }

    @Override
    public void addIncome(double amt, String note) {
        ledger.add(amt, note);
    }

    @Override
    public void addExpense(double amt, String note) {
        ledger.add(-amt, note);
    }
}