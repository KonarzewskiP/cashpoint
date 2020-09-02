package bankomat.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CommandExecution {
    private final Command command;
    private final LocalDateTime executionTime;
}
