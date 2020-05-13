package bankomat.commands;

import bankomat.User;

import java.math.BigDecimal;

public class DepositCommand extends Command{
    private final String amount;
    private BigDecimal oldBalance;
    private User user;


    public DepositCommand(String amount){
        this.amount = amount;
    }

    @Override
    public void execute(User user) {
        oldBalance = user.getAccBalance();
        user.setAccBalance(user.getAccBalance().add(new BigDecimal(this.amount)));
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
}
