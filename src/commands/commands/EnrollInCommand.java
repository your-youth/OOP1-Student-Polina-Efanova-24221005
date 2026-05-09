package commands.commands;

import commands.contracts.Command;
import enums.Status;
import models.Discipline;
import models.Student;
import utils.ProgramCurriculum;

import java.util.List;

public class EnrollInCommand implements Command {
    private final Student student;
    private final String course;

    public EnrollInCommand(Student student, String course) {
        this.student = student;
        this.course = course;
    }

    @Override
    public String execute() {
        if (student.getStatus() == Status.INTERRUPTED) return "Student is interrupted.";

        List<Discipline> available = ProgramCurriculum
                .getDisciplinesByYear(student.getSpecialty(), student.getYear());
        Discipline match = available.stream()
                .filter(d -> d.getName().equalsIgnoreCase(course))
                .findFirst().orElse(null);

        if (match == null) return "Discipline '" + course + "' not found in year " + student.getYear() + ".";
        if (student.getEnrolledDisciplines().containsKey(match)) return "Already enrolled in '" + course + "'.";

        student.setRecordedDisciplines(match);
        return "Student " + student.getFacultyNumber() + " enrolled in '" + match.getName() + "'.";
    }
}