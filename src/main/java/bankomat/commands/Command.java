package bankomat.commands;

import bankomat.errors.InsufficientFundsException;
import bankomat.errors.NoSuchAccountException;

public interface Command {

 void execute() throws InsufficientFundsException, NoSuchAccountException;

void undo() throws InsufficientFundsException, NoSuchAccountException;

String description();
}
