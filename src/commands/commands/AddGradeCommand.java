package commands.commands;

import commands.contracts.Command;
import enums.Status;
import models.Discipline;
import models.Student;

public class AddGradeCommand implements Command {
    private final Student student;
    private final String course;
    private final double grade;

    public AddGradeCommand(Student student, String course, double grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    @Override
    public String execute() {
        if (student.getStatus() == Status.INTERRUPTED) return "Student is interrupted.";
        if (grade < 2.0 || grade > 6.0) return "Grade must be between 2.0 and 6.0.";

        Discipline disc = student.getEnrolledDisciplines().keySet().stream()
                .filter(d -> d.getName().equalsIgnoreCase(course))
                .findFirst().orElse(null);

        if (disc == null) return "Student is not enrolled in '" + course + "'.";
        student.addGraduate(disc, grade);
        return "Grade " + grade + " added for '" + course + "'.";
    }
}
