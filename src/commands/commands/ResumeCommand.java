package commands.commands;

import commands.contracts.Command;
import enums.Status;
import models.Student;

public class ResumeCommand implements Command {
    private final Student student;

    public ResumeCommand(Student student) {
        this.student = student;
    }

    @Override
    public String execute() {
        if (student.getStatus() != Status.INTERRUPTED) return "Student is not interrupted.";
        student.setStatus(Status.RECORDED);
        return "Student " + student.getFacultyNumber() + " rights restored.";
    }
}