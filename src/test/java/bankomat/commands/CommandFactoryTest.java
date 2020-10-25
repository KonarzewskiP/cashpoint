package bankomat.commands;

import bankomat.Account;
import bankomat.errors.UnknownCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    private CommandFactory commandFactory;
    @BeforeEach
    public void beforeEach(){
        commandFactory = new CommandFactory(new Account("123",BigDecimal.valueOf(1000)));
    }

    @Test
    @DisplayName(" should recognize deposit command")
    void test1() throws UnknownCommandException {
        //given
        String input = "deposit 500";
        //when
        Command command = commandFactory.fromUserInput(input);
        // then
        assertTrue(command instanceof DepositCommand);
    }

    @Test
    @DisplayName(" should recognize withdraw command")
    void test2() throws UnknownCommandException {
        //given
        String input = "withdraw 500";
        //when
        Command command = commandFactory.fromUserInput(input);
        // then
        assertTrue(command instanceof WithdrawCommand);
    }

    @Test
    @DisplayName(" should recognize transfer command")
    void test3() throws UnknownCommandException {
        //given
        String input = "transfer 500 4588asd";
        //when
        Command command = commandFactory.fromUserInput(input);
        // then
        assertTrue(command instanceof TransferCommand);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "withdraw",
            "deposit",
            "transfer",
            "transfer 500",
            "random command",
    })
    @DisplayName("should throw unknown command exception")
    void test4(String input) {
        // expect
        assertThrows(UnknownCommandException.class, () -> commandFactory.fromUserInput(input));
    }
}