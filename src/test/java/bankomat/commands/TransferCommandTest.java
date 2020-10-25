package bankomat.commands;

import bankomat.ATM;
import bankomat.Account;
import bankomat.errors.InsufficientFundsException;
import bankomat.errors.NoSuchAccountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(PowerMockRunner.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(PowerMockRunner.class)
@PrepareForTest(ATM.class)
class TransferCommandTest {

    private Account acc;

    private final BigDecimal ACC_ONE_BALANCE = BigDecimal.valueOf(5000);
    private final BigDecimal ACC_TWO_BALANCE = BigDecimal.valueOf(10000);
    private final BigDecimal ACC_ONE_BALANCE_AFTER_TRANSFER = BigDecimal.valueOf(4500);
    private final BigDecimal ACC_TWO_BALANCE_AFTER_TRANSFER = BigDecimal.valueOf(10500);

    private final BigDecimal ACC_ONE_BALANCE_AFTER_UNDO = BigDecimal.valueOf(5500);
    private final BigDecimal ACC_TWO_BALANCE_AFTER_UNDO = BigDecimal.valueOf(9500);

    private final String ACC_NUMBER_TO_TRANSFER = "456";

    @Mock
    private ATM atm;

    @BeforeEach
    public void beforeEach() {
        acc = new Account("123", ACC_ONE_BALANCE);
        PowerMockito.mockStatic(ATM.class);
        BDDMockito.given(ATM.getAllAccount())
                .willReturn(List.of(
                        new Account("123", ACC_ONE_BALANCE),
                        new Account("456", ACC_TWO_BALANCE)
                ));

    }

    @Test
    @DisplayName("should transfer money from account to another account")
    public void test2() throws InsufficientFundsException, NoSuchAccountException {

        //given
        Command command = new TransferCommand(acc, BigDecimal.valueOf(500), ACC_NUMBER_TO_TRANSFER);
        //when
        command.execute();
        //then
        BigDecimal balanceAfterTransaction = acc.getBalance();
        assertEquals(ACC_ONE_BALANCE_AFTER_TRANSFER, balanceAfterTransaction);
    }

//    @Test
//    @DisplayName("should undo transfer money")
//    public void test2() throws InsufficientFundsException, NoSuchAccountException {
//        //given
//        Command command = new WithdrawCommand(acc, BigDecimal.valueOf(500));
//        //when
//        command.undo();
//        //then
//        BigDecimal balanceAfterTransaction = acc.getBalance();
//        assertEquals(ACC_BALANCE_AFTER_UNDO, balanceAfterTransaction);
//    }
}