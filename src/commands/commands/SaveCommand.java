package commands.commands;

import commands.contracts.Command;
import storage.FileManager;

public class SaveCommand implements Command {
    private final FileManager fileManager;

    public SaveCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public String execute() {
        try {
            fileManager.save();
            return "Successfully saved " + fileManager.getCurrentFileName();
        } catch (java.io.IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}