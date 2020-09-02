package bankomat;

import bankomat.errors.InsufficientFundsException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Account {
    private  String accountNumber;
    private  BigDecimal balance;

//    public Account deposit(BigDecimal amount) {
//        return new Account(accountNumber, balance.add(amount));
//    }

    public BigDecimal withdraw(BigDecimal amount) throws InsufficientFundsException {
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }
        return balance.subtract(amount);
    }

}
