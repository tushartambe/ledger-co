package org.ledgerco.service;

import org.ledgerco.model.LoanDetails;
import org.ledgerco.repository.Repository;

public class LoanService {
    private final Repository repository;

    public LoanService(Repository repository) {
        this.repository = repository;
    }

    public void recordLoan(String bank, LoanDetails loanDetails) {
        repository.addLoan(bank, loanDetails);
    }

}
