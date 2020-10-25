package bankomat;

import bankomat.commands.CommandFactory;
import bankomat.errors.UnknownCommandException;
import bankomat.importing.AccountImporter;

import java.util.List;
import java.util.Scanner;

public class ATM {
    private final static List<Account> ACCOUNTS = new AccountImporter("src/main/resources/static/accounts.csv").getAccounts();
    private final Account account;

    public ATM(User user) {
        this.account = getAccount(user);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        CommandExecutor commandExecutor = new CommandExecutor(account);
        CommandFactory commandFactory = new CommandFactory(account);

        System.out.println("Login successful.");
        while (true) {

            System.out.print("> ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase(Menu.EXIT.toString())) {
                System.out.println("Bye");
                System.exit(0);
            } else if (input.equalsIgnoreCase(Menu.BALANCE.toString())) {
                System.out.println(account.getBalance());
                commandExecutor.printBalance();
            } else if (input.equalsIgnoreCase(Menu.LOGOUT.toString())) {
                System.out.println("Logged out.");
                break;
            } else if (input.equalsIgnoreCase(Menu.HISTORY.toString())) {
                commandExecutor.printHistory();
            } else if (input.equalsIgnoreCase(Menu.UNDO.toString())) {
                commandExecutor.undo();
            } else if (input.equalsIgnoreCase(Menu.HELP.toString())) {
                printMenu();
            } else {
                try {
                    commandExecutor.execute(commandFactory.fromUserInput(input));
                } catch (UnknownCommandException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private Account getAccount(User user) {
        return ACCOUNTS.stream()
                .filter(acc -> acc.getAccountNumber().equals(user.getAccNumber()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    public static void printMenu() {
        System.out.println("\"help\" - prints this message\n" +
                "\"exit\" - exit the program\n" +
                "\"logout\" - log out of current session\n" +
                "\"history\" - prints current session's history\n" +
                "\"undo\" - undo last operation\n" +
                "\"withdraw <amount>\" - withdraw \n" +
                "\"deposit <amount>\" - deposit \n" +
                "\"balance\" - check current account balance\n" +
                "\"transfer <amount> <target account #>\" - transfer money from your account to other");
    }

    public static List<Account> getAllAccount() {
        return ACCOUNTS;
    }
}