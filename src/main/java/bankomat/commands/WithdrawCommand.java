package bankomat.commands;

import bankomat.User;

import java.math.BigDecimal;

public class WithdrawCommand extends Command {
    private final String amount;
    private BigDecimal oldBalance;
    private User user;

    public WithdrawCommand(String amountToWithdraw) {
        this.amount = amountToWithdraw;
    }

    @Override
    public void execute(User user) {
        oldBalance = user.getAccBalance();
        setNewAccountBalanceForUser(user);
        this.user = user;
    }

    @Override
    public void undo() {
        if (oldBalance != null && user != null) {
            this.user.setAccBalance(oldBalance);
        }
    }

    @Override
    public String toString() {
        return "Withdraw £" + this.amount + " from account #" + this.user.getAccNumber();
    }

    private void setNewAccountBalanceForUser(User user){
        user.setAccBalance(user.getAccBalance().subtract(new BigDecimal(this.amount)));
    }
}
