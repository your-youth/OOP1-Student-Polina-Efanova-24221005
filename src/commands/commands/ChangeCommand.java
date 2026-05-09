package commands.commands;

import commands.contracts.Command;
import enums.Specialty;
import enums.Status;
import models.Student;

public class ChangeCommand implements Command {
    private final Student student;
    private final String option;
    private final String value;

    public ChangeCommand(Student student, String option, String value) {
        this.student = student;
        this.option = option;
        this.value = value;
    }

    @Override
    public String execute() {
        if (student.getStatus() == Status.INTERRUPTED) return "Student is interrupted.";
        return switch (option.toLowerCase()) {
            case "group" -> {
                try { student.setGroup(Integer.parseInt(value)); yield "Group changed to " + value + "."; }
                catch (NumberFormatException e) { yield "Group must be an integer."; }
            }
            case "year" -> {
                try {
                    int y = Integer.parseInt(value);
                    if (y != student.getYear() + 1) yield "Can only advance to next year.";
                    student.setYear(y);
                    yield "Year changed to " + y + ".";
                } catch (NumberFormatException e) { yield "Year must be an integer."; }
            }
            case "program" -> {
                try {
                    Specialty spec = Specialty.valueOf(value.toUpperCase());
                    student.setSpecialty(spec);
                    yield "Program changed to " + value + ".";
                } catch (IllegalArgumentException e) { yield "Unknown specialty: " + value; }
            }
            default -> "Unknown option: " + option + ". Use: program, group, year.";
        };
    }
}