package bankomat.commands;

public interface Command2 {
    void execute();
    void undo();
    String description();

}
