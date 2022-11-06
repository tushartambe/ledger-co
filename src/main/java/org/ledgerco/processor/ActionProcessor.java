package org.ledgerco.processor;

import org.ledgerco.constant.ActionType;
import org.ledgerco.model.LoanDetails;
import org.ledgerco.model.PaymentDetails;
import org.ledgerco.repository.Repository;
import org.ledgerco.service.BalanceService;
import org.ledgerco.service.LoanService;
import org.ledgerco.service.PaymentService;

public class ActionProcessor {
    public void process(String input) {
        String[] args = input.split(" ");
        ActionType action = ActionType.valueOf(args[0]);

        Repository repository = Repository.getRepository();
        switch (action) {
            case LOAN:
                LoanService loanService = new LoanService(repository);
                LoanDetails loanDetails = new LoanDetails(args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
                loanService.recordLoan(args[1], loanDetails);
                break;
            case BALANCE:
                BalanceService balanceService = new BalanceService(repository);
                balanceService.showBalance(args[1], args[2], Integer.parseInt(args[3]));
                break;
            case PAYMENT:
                PaymentDetails paymentDetails = new PaymentDetails(args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                PaymentService paymentService = new PaymentService(repository);
                paymentService.recordPayment(args[1], paymentDetails);
                break;
        }
    }
}
