package org.ledgerco.service;

import org.ledgerco.repository.Repository;
import org.ledgerco.model.LoanDetails;

public class BalanceService {
    //    private static BalanceService balanceService;
    private final Repository repository;

    public BalanceService(Repository repository) {
        this.repository = repository;
    }

    public void process(String bank, String customer, Integer numberOfInstallmentsPaid) {
        LoanDetails loanDetails = repository.getLoanDetails(bank, customer);

        Double installmentAmount = loanDetails.getInstallmentAmount();
        Integer totalNumberOfInstallments = loanDetails.getTotalNumberOfInstallments();

        double totalAmountPaid = installmentAmount * numberOfInstallmentsPaid;
        Integer remainingInstallments = totalNumberOfInstallments - numberOfInstallmentsPaid;

        StringBuilder entry = new StringBuilder();
        entry.append(bank)
                .append(" ")
                .append(customer)
                .append(" ")
                .append((int) totalAmountPaid)
                .append(" ")
                .append(remainingInstallments);

        System.out.println(entry);
    }

//    public static BalanceService getBalanceService() {
//        if (balanceService == null) {
//            Repository repository = Repository.getRepository();
//            balanceService = new BalanceService(repository);
//        }
//        return balanceService;
//    }
}
