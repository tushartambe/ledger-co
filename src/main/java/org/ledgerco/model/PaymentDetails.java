package org.ledgerco.model;

public class PaymentDetails {
    public final String customerName;
    public final Integer paymentAmount;
    public final Integer installmentNumber;

    public PaymentDetails(String customerName, Integer paymentAmount, Integer installmentNumber) {
        this.customerName = customerName;
        this.paymentAmount = paymentAmount;
        this.installmentNumber = installmentNumber;
    }

}
