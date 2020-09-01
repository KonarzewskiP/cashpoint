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
                System.out.println(account.getBalance()); // TODO: check if its working properly. It may show wrong balance
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
                }
            }
        }
    }

    private static boolean checkIfInputAmountIsValid(String amountToCheck) {
        try {
            if (amountToCheck.charAt(0) == '-') {
                throw new NumberFormatException();
            }
            Double.parseDouble(amountToCheck);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("\"" + amountToCheck + "\" is not a valid number.");
        }
        return false;
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



/*    public void run() {
        Scanner sc = new Scanner(System.in);
        String input;

        System.out.println("Login successful.");
        while (true) {

            System.out.print("> ");
            input = sc.nextLine();
            Transaction transaction = new Transaction(account);
            if (input.equalsIgnoreCase(Menu.EXIT.toString())) {
                System.out.println("Bye");
                System.exit(0);
            } else if (input.equalsIgnoreCase(Menu.BALANCE.toString())) {
                System.out.println(account.getBalance()); // TODO: check if its working properly. It may show wrong balance
                transaction.printBalance();
            } else if (input.equalsIgnoreCase(Menu.LOGOUT.toString())) {
                System.out.println("Logged out.");
                break;
            } else if (input.equalsIgnoreCase(Menu.HISTORY.toString())) {
                transaction.printHistory();
            } else if (input.equalsIgnoreCase(Menu.UNDO.toString())) {
                transaction.undo();
            } else if (input.equalsIgnoreCase(Menu.WITHDRAW.toString())) {
                if (checkIfInputAmountIsValid(input[1])) {
                    if (transaction.sufficientFounds(input[1])) {
                        transaction.execute(new WithdrawCommand(input[1]));
                    }
                }
            } else if (input.equalsIgnoreCase(Menu.DEPOSIT.toString())) {
                if (checkIfInputAmountIsValid(input[1])) {
                    transaction.execute(new DepositCommand(input[1]));
                }
//            } else if (input[0].equalsIgnoreCase(Menu.TRANSFER.toString())) {
//                if (checkIfInputAmountIsValid(input[1])) {
//                    if (bankUsers.includeAccountNumber(input[2])) {
//                        if (transaction.sufficientFounds(input[1])) {
//                            transaction.execute(new TransferCommand(input[1], bankUsers.getUser(input[2])));
//                        }
//                    }
//                }
            } else if (input.equalsIgnoreCase(Menu.HELP.toString())) {
                printMenu();
            } else {
                System.out.println("\"" + input + "\" command not recognize, try again.");
            }
        }
    }*/