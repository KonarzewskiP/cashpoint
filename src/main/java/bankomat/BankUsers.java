package bankomat;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BankUsers {
    private final List<User> listOfBankUsers;
    private final List<String[]> usersFromFile;

    public BankUsers(List<String[]> usersFileAsLines){
        this.usersFromFile = usersFileAsLines;
        this.listOfBankUsers = createUsersListFromFile(usersFileAsLines);
    }

    public List<User> getListOfBankUsers() {
        return listOfBankUsers;
    }

    private List<User> createUsersListFromFile(List<String[]> usersFileAsLines){
        return usersFileAsLines
                .stream()
                .map(this::createSingleUserFromString)
                .collect(Collectors.toList());
    }

    private User createSingleUserFromString(String[] userData){
        return User.builder()
                .accBalance(new BigDecimal(userData[1].substring(1)))
                .accNumber(userData[0])
                .build();
    }

    public boolean includeAccountNumber(String accNumberToCheck){
        for (User user : listOfBankUsers) {
            if (user.getAccNumber().equals(accNumberToCheck)){
                return true;
            }
        }
        return false;
    }

    public User getUser(String userAccountNumber) {
        for (User user : listOfBankUsers) {
            if (user.getAccNumber().equals(userAccountNumber)){
                return user;
            }
        }
        return null;
    }
}
