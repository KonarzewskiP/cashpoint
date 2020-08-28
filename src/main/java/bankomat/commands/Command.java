package bankomat.commands;

import bankomat.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Command {


//    public abstract void execute(Account account);
    public abstract void execute();

    public abstract void undo();

    public abstract String description();
}
