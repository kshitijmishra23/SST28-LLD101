package com.example.payments;

// adapts SafeCashClient - this one is trickier because param order is different
// and we need to call confirm() on the payment object to get the txn id
public class SafeCashAdapter implements PaymentGateway {
    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = client;
    }

    @Override
    public String charge(String customerId, int amountCents) {
        // note: SafeCash takes (amount, user) not (user, amount)
        SafeCashPayment payment = client.createPayment(amountCents, customerId);
        return payment.confirm();
    }
}
