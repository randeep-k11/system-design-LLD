package com.interview.automatedparkinglot.service.impl;

import com.interview.automatedparkinglot.service.PaymentStrategy;

public class UpiPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing UPI payment of $" + amount);
       
    }
}