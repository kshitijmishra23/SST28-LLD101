package com.example.payments;

// adapts FastPayClient to our PaymentGateway interface
public class FastPayAdapter implements PaymentGateway {
    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = client;
    }

    @Override
    public String charge(String customerId, int amountCents) {
        // fastpay already takes same params so just forward the call
        return client.payNow(customerId, amountCents);
    }
}
