package bankomat.commands;

import bankomat.User;

public class TransferCommand extends Command {
    private final User accountToTransferMoney;

    public TransferCommand(String amountToTransfer, User accountToTransferMoney) {
        super(amountToTransfer);
        this.accountToTransferMoney = accountToTransferMoney;
    }

    @Override
    public void execute(User user) {
            super.oldBalance = user.getAccBalance();
            setNewAccountBalanceForUser(user);
            transferMoneyToAnotherAccount(accountToTransferMoney);
            super.user = user;
    }

    @Override
    public void undo() {
        if (super.oldBalance != null && super.user != null) {
            super.user.setAccBalance(super.oldBalance);
            undoTransferMoney(accountToTransferMoney);
        }
    }

    @Override
    public String toString() {
        return "Transfer £" + super.amount + " from acc #" + super.user.getAccNumber() +
                " to acc #" + accountToTransferMoney.getAccNumber();
    }

    private void setNewAccountBalanceForUser(User user) {
        user.setAccBalance(user.getAccBalance().subtract(super.amount));
    }

    private void transferMoneyToAnotherAccount(User accountToTransferMoney) {
        accountToTransferMoney.setAccBalance(accountToTransferMoney.getAccBalance().add(super.amount));
    }

    private void undoTransferMoney(User accountToTransferMoney) {
        accountToTransferMoney.setAccBalance(accountToTransferMoney.getAccBalance().subtract(super.amount));
    }
}
