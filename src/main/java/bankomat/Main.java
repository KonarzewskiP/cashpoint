package bankomat;

import bankomat.commands.DepositCommand;
import bankomat.commands.TransferCommand;
import bankomat.commands.WithdrawCommand;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Scanner;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateUserImporter dateUserImporter = new DateUserImporter("src/main/resources/static/accounts.csv");
        BankUsers bankUsers = new BankUsers(dateUserImporter.readUser());

        boolean loginFlag = true;
        boolean cashpointFlag = true;

        System.out.println("Hello, this is an ATM.");
        while (loginFlag) {
            System.out.println("Type: \"login <acc_number> <password>\" to log in.");
            System.out.println("Type: \"exit\" to terminate.");

            String[] login = scanner.nextLine().split(" ");
            boolean optionFromMenu = checkIfCommandInputIsCorrect(login[0]);

            if (login.length == 3 && (login[0].equalsIgnoreCase("exit")
                    || login[0].equalsIgnoreCase("login"))) {
                if (login[0].equalsIgnoreCase("exit")) {
                    loginFlag = false;
                } else {
                    String userAccNumber = Login.login(login[1], login[2]);
                    if (!userAccNumber.equals("userNotFound")) {
                        System.out.println("Login successful.");

                        Cashpoint cashpoint = new Cashpoint(userAccNumber, bankUsers.getListOfBankUsers());
                        do {

                            login = scanner.nextLine().split(" ");
                            login[0] = login[0].toLowerCase();
                            switch (login[0]) {
                                case "exit":
                                    cashpointFlag = false;
                                    loginFlag = false;
                                    break;
                                case "balance":
                                    cashpoint.printBalance();
                                    break;
                                case "logout":
                                    cashpointFlag = false;
                                    System.out.println("Logged out.");
                                    break;
                                case "history":
                                    cashpoint.printHistory();
                                    break;
                                case "undo":
                                    cashpoint.undo();
                                    break;
                                case "withdraw":
                                    if (checkIfInputAmountIsValid(login[1])) {
                                        if (cashpoint.sufficientFounds(login[1])) {
                                            cashpoint.execute(new WithdrawCommand(login[1]));
                                        }
                                    }
                                    break;
                                case "deposit":
                                    if (checkIfInputAmountIsValid(login[1])) {
                                        cashpoint.execute(new DepositCommand(login[1]));
                                    }
                                    break;
                                case "transfer":
                                    if (checkIfInputAmountIsValid(login[1])) {
                                        if (bankUsers.includeAccountNumber(login[2])) {
                                            if (cashpoint.sufficientFounds(login[1])) {
                                                cashpoint.execute(new TransferCommand(login[1], bankUsers.getUser(login[2])));
                                            }
                                        }
                                    }
                                    break;
                                case "help":
                                    printMenu();
                                    break;
                                default:
                                    System.out.println("\"" + login[0] + "\" command not recognize, try again.");
                            }

                        } while (cashpointFlag);
                        cashpointFlag = true;
                    } else {
                        log.info("Wrong login or password!");
                    }
                }
            } else if (optionFromMenu) {
                if (login[0].equalsIgnoreCase("exit")) {
                    loginFlag = false;
                } else if (login[0].equalsIgnoreCase("login")) {
                    System.out.println("You need to type: login <login> <password>");
                } else {
                    System.out.println("You need to login first.");
                }
            } else {
                System.out.println("command \"" + login[0] + "\" not recognize, try again. !");
            }
        }
        System.out.println("Bye!");
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

    private static boolean checkIfCommandInputIsCorrect(String commandUserInput) {
        return Arrays.stream(Menu.values())
                .anyMatch(value -> commandUserInput.equalsIgnoreCase(value.toString()));
    }

    public static void printMenu() {
        System.out.println("help - prints this message\n" +
                "exit - exit the program\n" +
                "logout - log out of current session\n" +
                "history - prints current session's history\n" +
                "undo - undo last operaton\n" +
                "withdraw - withdraw <amount>\n" +
                "deposit - deposit <amount>\n" +
                "balance - check curent account balance\n" +
                "transfer - transfer <amount> <target account number> ");
    }
}
//    skitchen0,RPTHvOhprmkb,DK4666476382688080
//            dbonnett1,yYtXWsJN,EE920481338099138901
//    emayho2,ovZUgni,AD0977037305U8LZZDHCWRVI
//            dbendle3,j0wUcumEt0,FR0569304442285XRFKYDPUDF95
//    bpresnall4,5wkm92,MU84VBJN7375279443420830314WDK
