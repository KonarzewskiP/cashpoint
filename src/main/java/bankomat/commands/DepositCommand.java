package bankomat.commands;

import bankomat.Account;
public class DepositCommand extends Command{

    public DepositCommand(String amountToDeposit){
        super(amountToDeposit);
    }

    @Override
    public void execute(Account account) {
        super.oldBalance = account.getBalance();
        setNewAccountBalanceForaccount(account);
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
        return "Deposit £" + super.amount + " from account #" + super.account.getAccountNumber();
    }

    private void setNewAccountBalanceForaccount(Account account){
        account.setBalance(account.getBalance().add(super.amount));
    }
}
