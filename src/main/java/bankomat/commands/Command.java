package bankomat.commands;

import bankomat.Account;
import bankomat.errors.InsufficientFundsException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Command {


//    public abstract void execute(Account account);
    public abstract void execute() throws InsufficientFundsException;

    public abstract void undo() throws InsufficientFundsException;

    public abstract String description();
}
