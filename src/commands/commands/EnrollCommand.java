package commands.commands;

import commands.contracts.Command;
import enums.Specialty;
import models.Student;
import storage.StudentRepository;

public class EnrollCommand implements Command {
    private final StudentRepository repository;
    private final int fn;
    private final String specialty;
    private final int group;
    private final String firstName;
    private final String lastName;

    public EnrollCommand(StudentRepository repository, int fn, String specialty,
                         int group, String firstName, String lastName) {
        this.repository = repository;
        this.fn = fn;
        this.specialty = specialty;
        this.group = group;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String execute() {
        if (String.valueOf(fn).length() != 8)
            return "Invalid faculty number: must be 8 digits.";

        if (!firstName.matches("[a-zA-Z]{2,}"))
            return "Invalid first name: only letters, min 2 characters.";

        if (!lastName.matches("[a-zA-Z]{2,}"))
            return "Invalid last name: only letters, min 2 characters.";

        if (group <= 0)
            return "Invalid group: must be positive.";
        if (repository.exists(fn)) return "Student " + fn + " already exists.";
        Specialty spec;
        try { spec = Specialty.valueOf(specialty.toUpperCase()); }
        catch (IllegalArgumentException e) { return "Unknown specialty: " + specialty; }
        Student student = new Student(firstName, lastName, fn, 1, spec, group);
        repository.addStudent(student);
        return "Student " + firstName + " " + lastName + " enrolled with FN " + fn + ".";
    }
}