package com.interview.automatedparkinglot.service.impl;

import com.interview.automatedparkinglot.service.PaymentStrategy;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing cash payment of $" + amount);

    }
}