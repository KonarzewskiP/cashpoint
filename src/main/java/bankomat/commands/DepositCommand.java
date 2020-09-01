package bankomat.commands;

import bankomat.Account;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class DepositCommand implements Command {
    protected final BigDecimal amount;
//    protected final BigDecimal balance;
    protected final Account account;


//    public DepositCommand(Account account, BigDecimal amountToDeposit) {
//        super(account);
//        this.amount = amountToDeposit;
//    }

    @Override
    public void execute() {
//        balance = account.getBalance();
//        setNewBalanceForAccount();
        account.setBalance(account.getBalance().add(amount));
    }

    @Override
    public void undo() {
//        if (balance != null && account != null) {
//            account.setBalance(balance);
//        }
    }

    @Override
    public String description() {
        return "Deposit £" + amount + " to account #" + account.getAccountNumber();
    }


//    private void setNewBalanceForAccount() {
//        account.setBalance(account.getBalance().add(amount));
//    }
}
