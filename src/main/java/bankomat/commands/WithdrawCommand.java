package bankomat.commands;

import bankomat.Account;
import bankomat.errors.InsufficientFundsException;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class WithdrawCommand implements Command {
    protected final Account account;
    protected final BigDecimal amount;
//    protected final BigDecimal balance;

//    public WithdrawCommand(Account account, BigDecimal amountToDeposit) {
//        this.amount = amountToDeposit;
//    }

    @Override
    public void execute() throws InsufficientFundsException {
//        balance = account.getBalance();
//        setNewAccountBalanceForUser(account);
        account.setBalance(account.withdraw(amount));
    }

    @Override
    public void undo() {
        account.setBalance(account.getBalance().add(amount));
    }

    @Override
    public String description() {
        return "Withdraw £" + amount.toString() + " from account #" + account.getAccountNumber();
    }


//    private void setNewAccountBalanceForUser(Account account) {
//        account.setBalance(account.getBalance().subtract(amount));
//    }
}
