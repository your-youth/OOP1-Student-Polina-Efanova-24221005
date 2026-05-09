package commands.commands;

import commands.contracts.Command;
import enums.Specialty;
import models.Student;
import storage.StudentRepository;

public class PrintAllCommand implements Command {
    private final StudentRepository repository;
    private final String specialtyStr;
    private final int year;

    public PrintAllCommand(StudentRepository repository, String specialtyStr, int year) {
        this.repository = repository;
        this.specialtyStr = specialtyStr;
        this.year = year;
    }

    @Override
    public String execute() {
        Specialty specialty;
        try { specialty = Specialty.valueOf(specialtyStr.toUpperCase()); }
        catch (IllegalArgumentException e) { return "Unknown specialty: " + specialtyStr; }

        StringBuilder sb = new StringBuilder("Students in " + specialtyStr + ", Year " + year + ":\n");
        boolean found = false;
        for (Student s : repository.getAllStudents()) {
            if (s.getSpecialty() == specialty && s.getYear() == year) {
                sb.append(String.format("  %d | %s %s | %s%n",
                        s.getFacultyNumber(), s.getFirstName(), s.getLastName(), s.getStatus()));
                found = true;
            }
        }
        if (!found) return "No students found in " + specialtyStr + " year " + year + ".";
        return sb.toString();
    }
}