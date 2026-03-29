package com.example.payments;


public interface PaymentGateway {
    String pay(double amount);
}