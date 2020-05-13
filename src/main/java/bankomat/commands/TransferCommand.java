package bankomat.commands;

import bankomat.User;

import java.math.BigDecimal;

public class TransferCommand extends Command{
    private final String amount;
    private final User accountToTransferMoney;
    private BigDecimal oldBalance;
    private User user;

    public TransferCommand(String amountToTransfer,User accountToTransferMoney) {
        this.amount = amountToTransfer;
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
        }
    }

    @Override
    public String toString() {
        return "Transfer £" + this.amount + " from acc #" + this.user.getAccNumber()+" to acc #";
    }

    private void setNewAccountBalanceForUser(User user){
        user.setAccBalance(user.getAccBalance().subtract(new BigDecimal(this.amount)));
    }
    private void transferMoneyToAnotherAccount(User accountToTransferMoney){
        accountToTransferMoney.setAccBalance(accountToTransferMoney.getAccBalance().add(new BigDecimal(this.amount)));
    }
}
