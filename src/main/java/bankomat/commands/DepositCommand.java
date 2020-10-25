package bankomat.commands;

import bankomat.Account;
import bankomat.errors.InsufficientFundsException;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class DepositCommand implements Command {
    protected final BigDecimal amount;
    protected final Account account;


    @Override
    public void execute() {
        account.setBalance(account.getBalance().add(amount));
    }

    @Override
    public void undo() throws InsufficientFundsException {
        account.setBalance(account.withdraw(amount));
    }

    @Override
    public String description() {
        return "Deposit £" + amount + " to account #" + account.getAccountNumber();
    }

}
