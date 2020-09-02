package bankomat;

import bankomat.commands.Command;
import bankomat.commands.CommandExecution;
import bankomat.errors.InsufficientFundsException;
import lombok.extern.slf4j.Slf4j;

import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.Stack;

@Slf4j
public class CommandExecutor {
    private final Account account;

    public CommandExecutor(Account account) {
        this.account = account;
    }

    private final Stack<CommandExecution> commandHistory = new Stack<>(); //TODO check if it can hold more than 16 commands

    public void execute(Command command) {
        command.execute();
        commandHistory.push(new CommandExecution(command,LocalDateTime.now()));
        System.out.println(command.description());
    }

    public void undo() {
        try {
            Command previousCommand = commandHistory.pop().getCommand();
            previousCommand.undo();
            System.out.println("Undo: " + previousCommand.description());
            printBalance();
        } catch (EmptyStackException e) {
            System.out.println("No action done in this  session");
        } catch (InsufficientFundsException e) {
            throw new RuntimeException("FATAL: Could not undo command.");
        }

    }

    public void printHistory() {
        commandHistory.
                forEach(commandExecution ->
                        System.out.println("[" + commandExecution.getExecutionTime() + "] " +commandExecution.getCommand().description()));
    }


    public void printBalance() {
        System.out.println("Account balance at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                " is " + this.account.getBalance().setScale(2, RoundingMode.CEILING).toString());
    }

/*    private User getUser(String userAccNumber, List<User> bankUsersList) {

        for (User user : bankUsersList) {
            if (user.getAccNumber().equals(userAccNumber)) {
                return user;
            }
        }
        log.error("[] does not exist. New User is created.");
        return new User(userAccNumber, new BigDecimal(0));
    }*/

    public boolean sufficientFounds(String amount) {
        if (account.getBalance().longValue() >= Long.parseLong(amount)) {
            return true;
        } else {
            System.out.println("Insufficient funds to perform this operation.");
            return false;
        }
    }

//
//    private List<String[]> readUsers() {
//        return fileAsLine()
//                .stream()
//                .map(str -> str.split(","))
//                .collect(Collectors.toList());
//    }
//
//    private List<String> fileAsLine() {
//        try {
//            return Files.readAllLines(Paths.get("src/main/resources/static/accounts.csv"));
//        } catch (IOException ex) {
//            log.error(" File path in CASHPOINT is not found");
//            return List.of();
//        }
//    }
}
