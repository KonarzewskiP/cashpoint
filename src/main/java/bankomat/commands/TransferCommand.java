package bankomat.commands;

import bankomat.User;

import java.math.BigDecimal;
import java.util.List;

public class TransferCommand extends Command{
    private final String amount;
    private final String accToTransfer;
    private final List<User> listOdBankUsers;
    private BigDecimal oldBalance;
    private User user;

    public TransferCommand(String amount, String accToTransfer, List<User> listOdBankUsers) {
        this.amount = amount;
        this.accToTransfer = accToTransfer;
        this.listOdBankUsers = listOdBankUsers;
    }

    @Override
    public void execute(User user) {
        oldBalance = user.getAccBalance();
        user.setAccBalance(user.getAccBalance().subtract(new BigDecimal(this.amount)));
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
        return "Transfer £" + this.amount + " from acc #" + this.user.getAccNumber()+" to acc #";
    }
}
