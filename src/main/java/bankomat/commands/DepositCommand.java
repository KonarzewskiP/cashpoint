package bankomat.commands;

import bankomat.User;

public class DepositCommand extends Command{

    public DepositCommand(String amountToDeposit){
        super(amountToDeposit);
    }

    @Override
    public void execute(User user) {
        super.oldBalance = user.getAccBalance();
        setNewAccountBalanceForUser(user);
        super.user = user;
    }

    @Override
    public void undo() {
        if (super.oldBalance != null && super.user != null) {
            super.user.setAccBalance(super.oldBalance);
        }
    }

    @Override
    public String toString() {
        return "Deposit £" + super.amount + " from account #" + super.user.getAccNumber();
    }

    private void setNewAccountBalanceForUser(User user){
        user.setAccBalance(user.getAccBalance().add(super.amount));
    }
}
