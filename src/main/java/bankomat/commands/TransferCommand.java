package bankomat.commands;

import bankomat.Account;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class TransferCommand implements Command {
    protected final Account account;
    protected final BigDecimal amount;
    private final String accountToTransferMoney;
    //    protected final BigDecimal balance;

//    public TransferCommand(Account account, BigDecimal amountToDeposit, String accountToTransferMoney) {
//        this.amount = amountToDeposit;
//        this.accountToTransferMoney = accountToTransferMoney;
//    }

    @Override
    public void execute() {
//            balance = account.getBalance();
//            setNewAccountBalanceForUser(account);
//            transferMoneyToAnotherAccount(accountToTransferMoney);
        account.setBalance(account.getBalance().subtract(amount));
    }

    @Override
    public void undo() {
//        if (balance != null && account != null) {
//            account.setBalance(balance);
////            undoTransferMoney(accountToTransferMoney);
//        }
    }

    @Override
    public String description() {
        return "Transfer £" + amount + " from acc #" + account.getAccountNumber() +
                " to acc #" + accountToTransferMoney;
    }

    private void setNewAccountBalanceForUser(Account account) {
        account.setBalance(account.getBalance().subtract(amount));
    }

    private void transferMoneyToAnotherAccount(Account accountToTransferMoney) {
        accountToTransferMoney.setBalance(accountToTransferMoney.getBalance().add(amount));
    }

    private void undoTransferMoney(Account accountToTransferMoney) {
        accountToTransferMoney.setBalance(accountToTransferMoney.getBalance().subtract(amount));
    }
}
