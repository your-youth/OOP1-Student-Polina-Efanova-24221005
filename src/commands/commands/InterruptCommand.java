package commands.commands;

import commands.contracts.Command;
import enums.Status;
import models.Student;

public class InterruptCommand implements Command {
    private final Student student;

    public InterruptCommand(Student student) {
        this.student = student;
    }

    @Override
    public String execute() {
        if (student.getStatus() == Status.INTERRUPTED) return "Student is already interrupted.";
        student.setStatus(Status.INTERRUPTED);
        return "Student " + student.getFacultyNumber() + " marked as interrupted.";
    }
}