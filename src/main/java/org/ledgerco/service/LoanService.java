package org.ledgerco.service;

import org.ledgerco.model.LoanDetails;
import org.ledgerco.repository.Repository;

public class LoanService {
    //    private static LoanService loanService;
    private final Repository repository;

    public LoanService(Repository repository) {
        this.repository = repository;
    }

    public void process(String bank, LoanDetails loanDetails) {
        repository.addLoan(bank, loanDetails);
    }

//    public static LoanService getLoanService() {
//        if (loanService == null) {
//            Repository repository = Repository.getRepository();
//            loanService = new LoanService(repository);
//        }
//        return loanService;
//    }
}
