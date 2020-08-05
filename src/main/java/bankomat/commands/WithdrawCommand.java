package bankomat.commands;

import bankomat.Account;
import bankomat.User;

public class WithdrawCommand extends Command {

    public WithdrawCommand(String amountToWithdraw) {
        super(amountToWithdraw);
    }

    @Override
    public void execute(Account account) {
            super.oldBalance = account.getBalance();
            setNewAccountBalanceForUser(account);
            super.account = account;
    }

    @Override
    public void undo() {
        if (super.oldBalance != null && super.account != null) {
            super.account.setBalance(super.oldBalance);
        }
    }

    @Override
    public String toString() {
        return "Withdraw £" + super.amount.toString() + " from account #" + super.account.getAccountNumber();
    }

    private void setNewAccountBalanceForUser(Account account) {
        account.setBalance(account.getBalance().subtract(super.amount));
    }
}
