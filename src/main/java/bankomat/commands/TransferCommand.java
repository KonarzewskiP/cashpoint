package bankomat.commands;

import bankomat.User;

import java.math.BigDecimal;

public class TransferCommand extends Command {
    private final BigDecimal amount;
    private final User accountToTransferMoney;
    private BigDecimal oldBalance;
    private User user;

    public TransferCommand(String amountToTransfer, User accountToTransferMoney) {
        this.amount = new BigDecimal(amountToTransfer);
        this.accountToTransferMoney = accountToTransferMoney;
    }

    @Override
    public void execute(User user) {
        oldBalance = user.getAccBalance();
        setNewAccountBalanceForUser(user);
        transferMoneyToAnotherAccount(accountToTransferMoney);
        this.user = user;
    }

    @Override
    public void undo() {
        if (oldBalance != null && user != null) {
            this.user.setAccBalance(oldBalance);
            undoTransferMoney(accountToTransferMoney);
        }
    }

    @Override
    public String toString() {
        return "Transfer £" + this.amount + " from acc #" + this.user.getAccNumber() + " to acc #";
    }

    private void setNewAccountBalanceForUser(User user) {
        user.setAccBalance(user.getAccBalance().subtract(this.amount));
    }

    private void transferMoneyToAnotherAccount(User accountToTransferMoney) {
        accountToTransferMoney.setAccBalance(accountToTransferMoney.getAccBalance().add(this.amount));
    }

    private void undoTransferMoney(User accountToTransferMoney) {
        accountToTransferMoney.setAccBalance(accountToTransferMoney.getAccBalance().subtract(this.amount));
    }
}

//TODO
//How BigDecimal behave in case of adding negative number
//BigDecimal var1 = new BigDecimal(100);
//BigDecimal var2 = new BigDecimal(-50);
//var1.add(var2);
//var1.subtract(va2);
