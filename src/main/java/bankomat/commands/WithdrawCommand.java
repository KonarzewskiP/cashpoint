package bankomat.commands;

import bankomat.User;

public class WithdrawCommand extends Command {

    public WithdrawCommand(String amountToWithdraw) {
        super(amountToWithdraw);
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
        return "Withdraw £" + super.amount.toString() + " from account #" + super.user.getAccNumber();
    }

    private void setNewAccountBalanceForUser(User user){
        user.setAccBalance(user.getAccBalance().subtract(super.amount));
    }
}
