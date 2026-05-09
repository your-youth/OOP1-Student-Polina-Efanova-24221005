package commands.commands;

import commands.contracts.Command;
import models.Discipline;
import models.Student;
import storage.StudentRepository;

import java.util.*;

public class ProtocolCommand implements Command {
    private final StudentRepository repository;
    private final String course;

    public ProtocolCommand(StudentRepository repository, String course) {
        this.repository = repository;
        this.course = course;
    }

    @Override
    public String execute() {
        Map<String, List<Student>> groups = new TreeMap<>();
        for (Student s : repository.getAllStudents()) {
            s.getEnrolledDisciplines().keySet().stream()
                    .filter(d -> d.getName().equalsIgnoreCase(course))
                    .findFirst()
                    .ifPresent(d -> groups
                            .computeIfAbsent(s.getSpecialty() + " | Year " + s.getYear(),
                                    k -> new ArrayList<>()).add(s));
        }
        if (groups.isEmpty()) return "No students enrolled in '" + course + "'.";

        StringBuilder sb = new StringBuilder("=== Protocol: " + course + " ===\n");
        for (Map.Entry<String, List<Student>> entry : groups.entrySet()) {
            sb.append("\n  ").append(entry.getKey()).append("\n");
            entry.getValue().stream()
                    .sorted(Comparator.comparingInt(Student::getFacultyNumber))
                    .forEach(s -> {
                        Double g = s.getEnrolledDisciplines().entrySet().stream()
                                .filter(e -> e.getKey().getName().equalsIgnoreCase(course))
                                .map(Map.Entry::getValue).findFirst().orElse(null);
                        sb.append(String.format("  %d | %s %s | %s%n",
                                s.getFacultyNumber(), s.getFirstName(), s.getLastName(),
                                g == null ? "—" : String.format("%.2f", g)));
                    });
        }
        return sb.toString();
    }
}