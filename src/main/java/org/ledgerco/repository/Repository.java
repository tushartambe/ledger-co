package org.ledgerco.repository;

import org.ledgerco.model.LoanDetails;
import org.ledgerco.model.PaymentDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Repository {
    private final Map<String, Map<String, LoanDetails>> loanMap;
    private final Map<String, Map<String, PaymentDetails>> paymentMap;

    public Repository(Map<String, Map<String, LoanDetails>> loanMap, Map<String, Map<String, PaymentDetails>> paymentMap) {
        this.loanMap = loanMap;
        this.paymentMap = paymentMap;
    }

    public Optional<LoanDetails> getLoanDetails(String bankName, String customerName) {
        if (this.loanMap.containsKey(bankName)) {
            Map<String, LoanDetails> bankLoans = this.loanMap.get(bankName);
            return Optional.ofNullable(bankLoans.get(customerName));
        }
        return Optional.empty();
    }

    public void addLoan(String bankName, LoanDetails loanDetails) {
        if (this.loanMap.containsKey(bankName)) {
            Map<String, LoanDetails> bankLoans = this.loanMap.get(bankName);
            bankLoans.put(loanDetails.customerName, loanDetails);
        } else {
            HashMap<String, LoanDetails> loanInfo = new HashMap<>();
            loanInfo.put(loanDetails.customerName, loanDetails);
            this.loanMap.put(bankName, loanInfo);
        }
    }

    public void addPayment(String bank, PaymentDetails paymentDetails) {
        if (this.paymentMap.containsKey(bank)) {
            Map<String, PaymentDetails> payments = this.paymentMap.get(bank);
            payments.put(paymentDetails.customerName, paymentDetails);
        } else {
            HashMap<String, PaymentDetails> paymentInfo = new HashMap<>();
            paymentInfo.put(paymentDetails.customerName, paymentDetails);
            this.paymentMap.put(bank, paymentInfo);
        }
    }

    public Optional<PaymentDetails> getPaymentDetails(String bankName, String customerName) {
        if (this.paymentMap.containsKey(bankName)) {
            Map<String, PaymentDetails> payments = this.paymentMap.get(bankName);
            return Optional.ofNullable(payments.get(customerName));
        }
        return Optional.empty();
    }
}
