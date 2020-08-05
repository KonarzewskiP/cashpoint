package bankomat;

import bankomat.commands.DepositCommand;
import bankomat.commands.TransferCommand;
import bankomat.commands.WithdrawCommand;
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
        String[] userInput;

        System.out.println("Login successful.");
        while (true) {

            System.out.print("> ");
            userInput = sc.nextLine().split(" ");
            Transaction transaction = new Transaction(account);
            if (userInput[0].equalsIgnoreCase(Menu.EXIT.toString())) {
                System.out.println("Bye");
                System.exit(0);
            } else if (userInput[0].equalsIgnoreCase(Menu.BALANCE.toString())) {
                System.out.println(account.getBalance()); // TODO: check if its working properly. It may show wrong balance
                transaction.printBalance();
            } else if (userInput[0].equalsIgnoreCase(Menu.LOGOUT.toString())) {
                System.out.println("Logged out.");
                break;
            } else if (userInput[0].equalsIgnoreCase(Menu.HISTORY.toString())) {
                transaction.printHistory();
            } else if (userInput[0].equalsIgnoreCase(Menu.UNDO.toString())) {
                transaction.undo();
            } else if (userInput[0].equalsIgnoreCase(Menu.WITHDRAW.toString())) {
                if (checkIfInputAmountIsValid(userInput[1])) {
                    if (transaction.sufficientFounds(userInput[1])) {
                        transaction.execute(new WithdrawCommand(userInput[1]));
                    }
                }
            } else if (userInput[0].equalsIgnoreCase(Menu.DEPOSIT.toString())) {
                if (checkIfInputAmountIsValid(userInput[1])) {
                    transaction.execute(new DepositCommand(userInput[1]));
                }
//            } else if (userInput[0].equalsIgnoreCase(Menu.TRANSFER.toString())) {
//                if (checkIfInputAmountIsValid(userInput[1])) {
//                    if (bankUsers.includeAccountNumber(userInput[2])) {
//                        if (transaction.sufficientFounds(userInput[1])) {
//                            transaction.execute(new TransferCommand(userInput[1], bankUsers.getUser(userInput[2])));
//                        }
//                    }
//                }
            } else if (userInput[0].equalsIgnoreCase(Menu.HELP.toString())) {
                printMenu();
            } else {
                System.out.println("\"" + userInput[0] + "\" command not recognize, try again.");
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
}

