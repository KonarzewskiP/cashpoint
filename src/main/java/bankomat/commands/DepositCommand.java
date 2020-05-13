package bankomat.commands;

import bankomat.User;

import java.math.BigDecimal;

public class DepositCommand extends Command{
    private final String amount;
    private BigDecimal oldBalance;
    private User user;


    public DepositCommand(String amountToDeposit){
        this.amount = amountToDeposit;
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
        return "Deposit £" + amount + " from account #" + user.getAccNumber();
    }

    private void setNewAccountBalanceForUser(User user){
        user.setAccBalance(user.getAccBalance().add(new BigDecimal(this.amount)));
    }
}
