package commands.commands;

import commands.contracts.Command;
import storage.FileManager;

public class CloseCommand implements Command {
    private final FileManager fileManager;

    public CloseCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public String execute() {
        String name = fileManager.getCurrentFileName();
        fileManager.close();
        return "Successfully closed " + name;
    }
}