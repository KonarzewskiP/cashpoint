package bankomat;

public enum Menu {
    HELP("help"),
    EXIT("exit"),
    LOGOUT("logout"),
    HISTORY("history"),
    UNDO("undo"),
    WITHDRAW("withdraw"),
    DEPOSIT("deposit"),
    BALANCE("balance"),
    TRANSFER("transfer"),
    LOGIN("login");

    Menu(String instruction) {
    }
}
