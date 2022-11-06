package org.ledgerco.model;

public class LoanDetails {
    public final String customerName;
    public final Integer principalAmount;
    public final Integer numberOfYears;
    public final Integer interestRate;

    public LoanDetails(String customerName, Integer principalAmount, Integer numberOfYears, Integer interestRate) {
        this.customerName = customerName;
        this.principalAmount = principalAmount;
        this.numberOfYears = numberOfYears;
        this.interestRate = interestRate;
    }

    public Integer getTotalAmountToRepay() {
        Integer interestAmount = (this.principalAmount * this.numberOfYears * this.interestRate) / 100;
        return this.principalAmount + interestAmount;
    }

    public Double getInstallmentAmount() {
        return Math.ceil((double) this.getTotalAmountToRepay() / this.getTotalNumberOfInstallments());
    }

    public Integer getTotalNumberOfInstallments() {
        return this.numberOfYears * 12;
    }

}
