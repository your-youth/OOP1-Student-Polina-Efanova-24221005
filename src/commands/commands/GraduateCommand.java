package commands.commands;

import commands.contracts.Command;
import enums.Status;
import models.Discipline;
import models.Student;

import java.util.Map;

public class GraduateCommand implements Command {
    private final Student student;

    public GraduateCommand(Student student) {
        this.student = student;
    }

    @Override
    public String execute() {
        if (student.getStatus() == Status.INTERRUPTED) return "Student is interrupted.";
        for (Map.Entry<Discipline, Double> e : student.getEnrolledDisciplines().entrySet()) {
            if (e.getValue() == null || e.getValue() < 3.0)
                return "Cannot graduate: '" + e.getKey().getName() + "' not passed.";
        }
        student.setStatus(Status.COMPLETED);
        return "Student " + student.getFacultyNumber() + " has graduated!";
    }
}