package commands.commands;

import commands.contracts.Command;
import models.Student;

public class PrintCommand implements Command {
    private final Student student;

    public PrintCommand(Student student) {
        this.student = student;
    }

    @Override
    public String execute() {
        return String.format(
                "Name: %s %s | FN: %d | Specialty: %s | Year: %d | Group: %d | Status: %s | Avg: %.2f",
                student.getFirstName(), student.getLastName(),
                student.getFacultyNumber(), student.getSpecialty(),
                student.getYear(), student.getGroup(),
                student.getStatus(), student.calculateAverage());
    }
}
