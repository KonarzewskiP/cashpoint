package bankomat;

import bankomat.commands.Command;
import bankomat.commands.CommandExecution;
import bankomat.errors.InsufficientFundsException;
import bankomat.errors.NoSuchAccountException;
import lombok.extern.slf4j.Slf4j;

import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EmptyStackException;
import java.util.Stack;

@Slf4j
public class CommandExecutor {
    private final Account account;

    public CommandExecutor(Account account) {
        this.account = account;
    }

    private final Stack<CommandExecution> commandHistory = new Stack<>();

    public void execute(Command command) {
        try {
            command.execute();
            commandHistory.push(new CommandExecution(command, LocalDateTime.now()));
            System.out.println(command.description());
        } catch (InsufficientFundsException e) {
            System.out.println("Not enough founds to perform this operation");
        } catch (NoSuchAccountException e) {
            System.out.println("Account does not exist.");
        }

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
        }catch (NoSuchAccountException e){
            System.out.println("Account does not exist.");
        }
    }

    public void printHistory() {
        commandHistory.
                forEach(commandExecution ->
                        System.out.println("[" + commandExecution.getExecutionTime() + "] " + commandExecution.getCommand().description()));
    }


    public void printBalance() {
        System.out.println("Account balance at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                " is " + this.account.getBalance().setScale(2, RoundingMode.CEILING).toString());
    }

}
