package commands.invoker;

import commands.contracts.Command;

public class CommandProcessor {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public String process() {
        return command.execute();
    }
}