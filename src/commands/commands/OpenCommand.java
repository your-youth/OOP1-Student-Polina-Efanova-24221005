package commands.commands;

import commands.contracts.Command;
import storage.FileManager;

public class OpenCommand implements Command {
    private final FileManager fileManager;
    private final String filePath;

    public OpenCommand(FileManager fileManager, String filePath) {
        this.fileManager = fileManager;
        this.filePath = filePath;
    }

    @Override
    public String execute() {
        try {
            fileManager.open(filePath);
            return "Successfully opened " + new java.io.File(filePath).getName();
        } catch (java.io.IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}