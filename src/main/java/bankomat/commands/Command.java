package bankomat.commands;

import bankomat.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Command {
    private final LocalDateTime timeOfTransaction = LocalDateTime.now();
    protected final BigDecimal amount;
    protected BigDecimal oldBalance;
    protected User user;

    public Command(String amount) {
        this.amount = new BigDecimal(amount);
    }

    public abstract void execute(User user);

    public abstract void undo();

    @Override
    public abstract String toString();

    public String getTimeOfTransaction() {
        return timeOfTransaction.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
