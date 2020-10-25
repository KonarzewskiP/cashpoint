package bankomat.commands;

import bankomat.Account;
import bankomat.errors.InsufficientFundsException;
import bankomat.errors.NoSuchAccountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DepositCommandTest {

    private Account acc;

    private final BigDecimal ACC_BALANCE = BigDecimal.valueOf(5000);
    private final BigDecimal ACC_BALANCE_AFTER_DEPOSIT = BigDecimal.valueOf(5500);
    private final BigDecimal ACC_BALANCE_AFTER_UNDO = BigDecimal.valueOf(4500);

    @BeforeEach
    public void beforeEach() {
        acc = new Account("123",ACC_BALANCE);
    }

    @Test
    @DisplayName("should deposit money into account")
    public void test1() throws InsufficientFundsException, NoSuchAccountException {
        //given
        Command command = new DepositCommand(BigDecimal.valueOf(500), acc);
        //when
        command.execute();
        //then
        BigDecimal balanceAfterTransaction = acc.getBalance();
        assertEquals(ACC_BALANCE_AFTER_DEPOSIT, balanceAfterTransaction);
    }

    @Test
    @DisplayName("should undo deposit money")
    public void test2() throws InsufficientFundsException, NoSuchAccountException {
        //given
        Command command = new DepositCommand(BigDecimal.valueOf(500), acc);
        //when
        command.undo();
        //then
        BigDecimal balanceAfterTransaction = acc.getBalance();
        assertEquals(ACC_BALANCE_AFTER_UNDO, balanceAfterTransaction);
    }
}