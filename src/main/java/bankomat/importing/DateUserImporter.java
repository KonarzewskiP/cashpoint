package bankomat.importing;

import bankomat.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DateUserImporter {
    private final String filePath;

    public DateUserImporter(String filePath) {
        this.filePath = filePath;
    }

    private List<String> fileAsLine(){
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException ex) {
            throw new RuntimeException("File path is not found", ex);
        }
    }

    public Set<User> getUsers() {
        return fileAsLine()
                .stream()
                .map(str -> str.split(","))
                .map(this::toUser)
                .collect(Collectors.toSet());
    }

    private User toUser(String[] parts){
        String login = parts[0];
        String password = parts[1];
        String accountNumber = parts[2];
        return new User(login, password, accountNumber);
    }
}
