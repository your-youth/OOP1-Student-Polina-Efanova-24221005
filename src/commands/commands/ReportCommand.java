package commands.commands;

import commands.contracts.Command;
import models.Discipline;
import models.Student;

import java.util.Map;

public class ReportCommand implements Command {
    private final Student student;

    public ReportCommand(Student student) {
        this.student = student;
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Transcript: ").append(student.getFirstName())
                .append(" ").append(student.getLastName())
                .append(" (FN: ").append(student.getFacultyNumber()).append(") ===\n");
        for (Map.Entry<Discipline, Double> e : student.getEnrolledDisciplines().entrySet()) {
            String grade = e.getValue() == null ? "— (counts as 2.0)" : String.format("%.2f", e.getValue());
            sb.append(String.format("  %-35s %s%n", e.getKey().getName(), grade));
        }
        sb.append(String.format("  Average: %.2f%n", student.calculateAverage()));
        return sb.toString();
    }
}