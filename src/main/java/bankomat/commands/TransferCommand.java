package bankomat.commands;

import bankomat.Account;
import bankomat.User;

public class TransferCommand extends Command {
    private final Account accountToTransferMoney;

    public TransferCommand(String amountToTransfer, Account accountToTransferMoney) {
        super(amountToTransfer);
        this.accountToTransferMoney = accountToTransferMoney;
    }

    @Override
    public void execute(Account account) {
            super.oldBalance = account.getBalance();
            setNewAccountBalanceForUser(account);
            transferMoneyToAnotherAccount(accountToTransferMoney);
            super.account = account;
    }

    @Override
    public void undo() {
        if (super.oldBalance != null && super.account != null) {
            super.account.setBalance(super.oldBalance);
            undoTransferMoney(accountToTransferMoney);
        }
    }

    @Override
    public String toString() {
        return "Transfer £" + super.amount + " from acc #" + super.account.getAccountNumber() +
                " to acc #" + accountToTransferMoney.getAccountNumber();
    }

    private void setNewAccountBalanceForUser(Account account) {
        account.setBalance(account.getBalance().subtract(super.amount));
    }

    private void transferMoneyToAnotherAccount(Account accountToTransferMoney) {
        accountToTransferMoney.setBalance(accountToTransferMoney.getBalance().add(super.amount));
    }

    private void undoTransferMoney(Account accountToTransferMoney) {
        accountToTransferMoney.setBalance(accountToTransferMoney.getBalance().subtract(super.amount));
    }
}
