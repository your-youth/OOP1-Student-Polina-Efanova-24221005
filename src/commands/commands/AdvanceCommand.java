package commands.commands;

import commands.contracts.Command;
import enums.DisciplineType;
import enums.Status;
import models.Discipline;
import models.Student;
import utils.ProgramCurriculum;

import java.util.List;

public class AdvanceCommand implements Command {
    private final Student student;

    public AdvanceCommand(Student student) {
        this.student = student;
    }

    @Override
    public String execute() {
        if (student.getStatus() == Status.INTERRUPTED) return "Student is interrupted.";
        if (student.getStatus() == Status.COMPLETED)   return "Student has graduated.";

        List<Discipline> compulsory = ProgramCurriculum
                .getDisciplinesByYear(student.getSpecialty(), student.getYear())
                .stream().filter(d -> d.getDisciplineType() == DisciplineType.COMPULSORY).toList();

        int fails = 0;
        for (Discipline d : compulsory) {
            Double grade = student.getEnrolledDisciplines().get(d);
            if (grade == null || grade < 3.0) fails++;
        }
        if (fails > 2) return "Cannot advance: " + fails + " failed compulsory subjects (max 2).";

        student.setYear(student.getYear() + 1);
        return "Student " + student.getFacultyNumber() + " advanced to year " + student.getYear() + ".";
    }
}