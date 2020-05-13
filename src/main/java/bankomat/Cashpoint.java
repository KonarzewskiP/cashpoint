package bankomat;

import bankomat.commands.Command;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class Cashpoint {
    private final User user;

    public Cashpoint(String userAcc, List<User> bankUsersList) {
        this.user = getUser(userAcc, bankUsersList);
    }

    private Deque<Command> undoStack = new LinkedList<>();

    public void execute(Command command) {
        command.execute(user);
        undoStack.offerLast(command);
        System.out.println(command);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command previousCommand = undoStack.pollLast();
            previousCommand.undo();
            System.out.println("Undo: " + previousCommand);
        } else {
            System.out.println("There is no action to undo.");
        }
    }

    public void printHistory() {
        for (Command command : undoStack) {
            System.out.println("[" + command.getTimeOfTransaction() + "] " + command.toString());
        }
    }

    public void printBalance() {
        System.out.println("Account balance at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                " is " + this.user.getAccBalance().setScale(2, RoundingMode.CEILING).toString());
    }

    private User getUser(String userAccNumber, List<User> bankUsersList) {

        for (User user : bankUsersList) {
            if (user.getAccNumber().equals(userAccNumber)) {
                return user;
            }
        }
        log.error("[] does not exist. New User is created.");
        return new User(userAccNumber, new BigDecimal(0));
    }

    public boolean sufficientFounds(String amount) {
        if (user.getAccBalance().longValue() >= Long.parseLong(amount)){
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
