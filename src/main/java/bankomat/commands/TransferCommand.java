package bankomat.commands;

import bankomat.ATM;
import bankomat.Account;
import bankomat.errors.InsufficientFundsException;
import bankomat.errors.NoSuchAccountException;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class TransferCommand implements Command {
    protected final Account account;
    protected final BigDecimal amount;
    private final String accountToTransferMoney;

    @Override
    public void execute() throws InsufficientFundsException, NoSuchAccountException {
        Account accToTransfer = findAccToTransfer();
        account.setBalance(account.withdraw(amount));
        accToTransfer.setBalance(accToTransfer.getBalance().add(amount));
    }

    @Override
    public void undo() throws NoSuchAccountException, InsufficientFundsException {
        Account accToTransfer = findAccToTransfer();
        account.setBalance(account.getBalance().add(amount));
        accToTransfer.setBalance(accToTransfer.withdraw(amount));
    }

    @Override
    public String description() {
        return "Transfer £" + amount + " from acc #" + account.getAccountNumber() +
                " to acc #" + accountToTransferMoney;
    }

    private Account findAccToTransfer() throws NoSuchAccountException {
        return ATM.getAllAccount().stream()
                .filter(acc -> acc.getAccountNumber().equals(accountToTransferMoney))
                .findFirst()
                .orElseThrow(NoSuchAccountException::new);
    }
}
