package storage;

import models.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentRepository {
    private Map<Integer, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getFacultyNumber(), student);
    }

    public Student findByFacultyNumber(int facultyNumber) {
        return students.get(facultyNumber);
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public boolean exists(int facultyNumber) {
        return students.containsKey(facultyNumber);
    }
    public void clear() {
        students.clear();
    }
}