package org.ledgerco.repository;

import org.ledgerco.model.LoanDetails;

import java.util.HashMap;
import java.util.Map;

public class Repository {
    private static Repository repository;
    private Map<String, Map<String, LoanDetails>> loanMap;

    private Repository(Map<String, Map<String, LoanDetails>> loanMap) {
        this.loanMap = loanMap;
    }

    public static Repository getRepository() {
        if (repository == null) {
            repository = new Repository(new HashMap<>());
        }
        return repository;
    }

    public LoanDetails getLoanDetails(String bankName, String customerName) {
        Map<String, LoanDetails> bankLoans = this.loanMap.get(bankName);
        return bankLoans.get(customerName);
    }

    public void addLoan(String bankName, LoanDetails loanDetails) {
        if (this.loanMap.containsKey(bankName)) {
            Map<String, LoanDetails> bankLoans = this.loanMap.get(bankName);
            bankLoans.put(loanDetails.name, loanDetails);
        } else {
            HashMap<String, LoanDetails> loanInfo = new HashMap<>();
            loanInfo.put(loanDetails.name, loanDetails);
            this.loanMap.put(bankName, loanInfo);
        }
    }
}
