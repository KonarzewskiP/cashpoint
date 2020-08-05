package bankomat.importing;


import bankomat.Account;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class AccountImporter {
    private final String filePath;

    public AccountImporter(String filePath) {
        this.filePath = filePath;
    }


    private List<String> fileAsLine(){
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException ex) {
            throw new RuntimeException("File path is not found", ex);
        }
    }

    public List<Account> getAccounts() {
        return fileAsLine()
                .stream()
                .map(str -> str.split(","))
                .map(this::toAccount)
                .collect(Collectors.toList());
    }

    private Account toAccount(String[] parts){
        String accountNumber = parts[0];
        BigDecimal balance = new BigDecimal(parts[1].substring(1));
        return new Account(accountNumber, balance);
    }
}
