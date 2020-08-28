package bankomat.commands;

import bankomat.Account;
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
    public void execute() {
//        balance = account.getBalance();
        setNewAccountBalanceForUser(account);
        account.setBalance(account.getBalance().subtract(amount));
    }

    @Override
    public void undo() {
//        if (balance != null && account != null) {
//            account.setBalance(balance);
//        }
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String toString() {
        return "Withdraw £" + amount.toString() + " from account #" + account.getAccountNumber();
    }

    private void setNewAccountBalanceForUser(Account account) {
        account.setBalance(account.getBalance().subtract(amount));
    }
}
