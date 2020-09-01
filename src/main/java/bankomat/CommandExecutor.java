package bankomat;

import bankomat.commands.Command;
import bankomat.commands.Command2;
import lombok.extern.slf4j.Slf4j;

import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Deque;

@Slf4j
public class CommandExecutor {
    private final Account account;

    public CommandExecutor(Account account) {
        this.account = account;
    }

    private final Deque<Command> commandHistory = new ArrayDeque<>(); //TODO check if it can hold more than 16 commands

    public void execute(Command command) {
        command.execute();
        commandHistory.offerLast(command);
        System.out.println(command.description());
    }


    public void undo() {
        if (!commandHistory.isEmpty()) {
            Command previousCommand = commandHistory.pollLast();
            previousCommand.undo();
            System.out.println("Undo: " + previousCommand);
        } else {
            System.out.println("There is no action to undo.");
        }
    }

    public void printHistory() {
        commandHistory.
                forEach(command ->
                        System.out.println("[" + command + "] " + command.toString()));
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
