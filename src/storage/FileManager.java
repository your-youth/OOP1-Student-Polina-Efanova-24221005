package storage;


import models.Student;

import java.io.*;

public class FileManager {

    private final StudentRepository repository;
    private final DataSerializer serializer;
    private String currentFilePath;

    public FileManager(StudentRepository repository) {
        this.repository = repository;
        this.serializer = new DataSerializer();
        this.currentFilePath = null;
    }

    public boolean isOpen() {
        return currentFilePath != null;
    }

    public String getCurrentFileName() {
        if (currentFilePath == null) return null;
        return new File(currentFilePath).getName();
    }

    public void open(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("File not found. Created new empty file: " + file.getName());
        } else {
            loadFromFile(file);
        }
        currentFilePath = filePath;
    }

    public void close() {
        repository.clear();
        currentFilePath = null;
    }

    public void save() throws IOException {
        if (currentFilePath == null) throw new IllegalStateException("No file is open.");
        writeToFile(currentFilePath);
    }

    public void saveAs(String filePath) throws IOException {
        writeToFile(filePath);
    }

    private void loadFromFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        String[] blocks = sb.toString().split("(?<=END\n)");
        for (String block : blocks) {
            block = block.trim();
            if (block.isEmpty()) continue;
            try {
                repository.addStudent(serializer.deserialize(block));
            } catch (IllegalArgumentException e) {
                System.err.println("Warning: skipped malformed block: " + e.getMessage());
            }
        }
    }

    private void writeToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : repository.getAllStudents()) {
                writer.write(serializer.serialize(student));
                writer.newLine();
            }
        }
    }
}