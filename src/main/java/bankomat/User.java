package bankomat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public final class User {
    private final String login;
    private final String accPassword;
    private final String accNumber;

    private BigDecimal accBalance;

    public User(String login, String accPassword, String accNumber) {
        this.login = login;
        this.accPassword = accPassword;
        this.accNumber = accNumber;
    }
}
