package models;

import enums.Specialty;
import enums.Status;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String firstName;
    private String lastName;
    private int facultyNumber;
    private int course;
    private Specialty specialty;
    private int group;
    private Status status;
    private Map<Discipline, Double> enrolledDisciplines;

    public Student(String firstName, String lastName, int facultyNumber, int year, Specialty specialty, int group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.facultyNumber = facultyNumber;
        this.course = 1;
        this.specialty = specialty;
        this.group = group;
        this.status = Status.ENROLLED;
        this.enrolledDisciplines = new HashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getFacultyNumber() {
        return facultyNumber;
    }

    public int getYear() {
        return course;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public int getGroup() {
        return group;
    }

    public Status getStatus() {
        return status;
    }

    public Map<Discipline, Double> getEnrolledDisciplines() {
        return enrolledDisciplines;
    }

    public void setYear(int year) {
        this.course = year;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void enrolledDisciplines(Discipline discipline){
        enrolledDisciplines.put(discipline, null);
    }
    public void addGraduate(Discipline discipline, double grade){
        enrolledDisciplines.put( discipline, grade);
    }

    public double calculateAverage() {
        if (enrolledDisciplines.isEmpty()) return 0;
        double sum = 0;
        for (Double grade : enrolledDisciplines.values()) {
            sum += (grade != null) ? grade : 2.0;
        }
        return sum / enrolledDisciplines.size();
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", facultyNumber=" + facultyNumber +
                ", year=" + course +
                ", specialty=" + specialty +
                ", group=" + group +
                ", status=" + status +
                ", enrolledDisciplines=" + enrolledDisciplines +
                '}';
    }
}
