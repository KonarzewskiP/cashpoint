package bankomat;

public final class Login {

    public static String login(String username, String password) {
        DateUserImporter dateUserImporter = new DateUserImporter("src/main/resources/static/users.csv");
        for (String[] str : dateUserImporter.readUser()) {
            if (username.equals(str[0]) && password.equals(str[1])) {
                return str[2];
            }
        }
        return "userNotFound";
    }
}
