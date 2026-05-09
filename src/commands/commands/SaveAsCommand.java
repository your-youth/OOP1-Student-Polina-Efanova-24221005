package commands.commands;

import commands.contracts.Command;
import storage.FileManager;

public class SaveAsCommand implements Command {
    private final FileManager fileManager;
    private final String filePath;

    public SaveAsCommand(FileManager fileManager, String filePath) {
        this.fileManager = fileManager;
        this.filePath = filePath;
    }

    @Override
    public String execute() {
        try {
            fileManager.saveAs(filePath);
            return "Successfully saved " + new java.io.File(filePath).getName();
        } catch (java.io.IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}