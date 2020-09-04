package bankomat.commands;

import bankomat.Account;
import bankomat.Menu;
import bankomat.errors.UnknownCommandException;

import java.math.BigDecimal;

public class CommandFactory {
    private final Account account;

    public CommandFactory(Account account) {
        this.account = account;
    }

    public Command fromUserInput(String userInput) throws UnknownCommandException {
        String[] inputParts = userInput.split(" ");

        if (inputParts[0].equalsIgnoreCase(Menu.WITHDRAW.toString())){
            return createWithdrawCommand(inputParts);
        } else if (inputParts[0].equalsIgnoreCase(Menu.DEPOSIT.toString())){
            return createDepositCommand(inputParts);
        } else if (inputParts[0].equalsIgnoreCase(Menu.TRANSFER.toString())){
            return createTransferCommand(inputParts);
        } else {
            throw new UnknownCommandException("Unrecognized command, please try again. Type 'help' to list available commands.");
        }
    }

    private Command createTransferCommand(String[] inputParts) throws UnknownCommandException {
        if (inputParts.length < 3) {
            throw new UnknownCommandException("Malformed command. Should be: 'transfer <amount> <target account #>'");
        }
        BigDecimal amount = new BigDecimal(inputParts[1]);
        String targetAccount = inputParts[2];
        return new TransferCommand(account, amount, targetAccount);

    }

    private Command createWithdrawCommand(String[] inputParts) throws UnknownCommandException {
        if (inputParts.length < 2) {
            throw new UnknownCommandException("Malformed command. Should be: 'withdraw <amount>'");
        }
        BigDecimal amount = new BigDecimal(inputParts[1]);
        return new WithdrawCommand(account, amount);
    }

    private Command createDepositCommand(String[] inputParts) throws UnknownCommandException {
        if (inputParts.length < 2) {
            throw new UnknownCommandException("Malformed command. Should be: 'deposit <amount>'");
        }
        BigDecimal amount = new BigDecimal(inputParts[1]);
        return new DepositCommand(amount, account);
    }


}
