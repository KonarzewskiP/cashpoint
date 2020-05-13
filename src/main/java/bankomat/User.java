package bankomat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@Builder
public final class User {
    private final String accNumber;
//    private final String accPassword;
    private BigDecimal accBalance;


public void printBalance(){
    System.out.println("Your balance at "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                        " is " + accBalance.setScale(2, RoundingMode.CEILING).toString());
}

}
