package bankomat;

import bankomat.errors.InvalidUserOrPasswordException;
import bankomat.importing.DateUserImporter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public final class Login {
    private static final Set<User> USERS = new DateUserImporter("src/main/resources/static/users.csv").getUsers();

    private Login() {
    }

    public static User login() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello, this is an ATM.");

        while (true) {
            System.out.println("Type: \"login <acc_number> <password>\" to log in.");
            System.out.println("Type: \"exit\" to terminate.");

            String[] login = sc.nextLine().trim().split(" ");
            boolean optionFromMenu = checkIfCommandInputIsCorrect(login[0]);
            //TODO check what empty string will return
            if (login[0].equalsIgnoreCase(Menu.LOGIN.toString()) && login.length >= 3) {
                try {
                    return getUser(login);
                } catch (InvalidUserOrPasswordException e) {
                    System.out.println(e.getMessage());
                }
            } else if (login[0].equalsIgnoreCase("exit")) {
                System.out.println("Bye");
                System.exit(0);
            } else if (optionFromMenu) {
                System.out.println("You need to log in first.");
            } else {
                System.out.println("command \"" + login[0] + "\" not recognize, try again. !");
            }
        }
    }

    private static boolean checkIfCommandInputIsCorrect(String commandUserInput) {
        return Arrays.stream(Menu.values())
                .anyMatch(value -> commandUserInput.equalsIgnoreCase(value.toString()));
    }

    private static User getUser(String[] login) throws InvalidUserOrPasswordException {
        return getUser(login[1], login[2])
                .orElseThrow(() -> new InvalidUserOrPasswordException("Invalid user name or password."));
    }

    private static Optional<User> getUser(String username, String password) {
        return USERS.stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(username))
                .filter(user -> user.getAccPassword().equalsIgnoreCase(password))
                .findFirst();
    }
}



    
