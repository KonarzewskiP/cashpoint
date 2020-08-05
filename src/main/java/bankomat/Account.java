package bankomat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
//@EqualsAndHashCode
public class Account {
    private  String accountNumber;
    private  BigDecimal balance;
}
