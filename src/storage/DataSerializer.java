package storage;


import enums.DisciplineType;
import enums.Specialty;
import enums.Status;
import models.Discipline;
import models.Student;

public class DataSerializer {

    public String serialize(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append("STUDENT\n");
        sb.append("firstName=").append(student.getFirstName()).append("\n");
        sb.append("lastName=").append(student.getLastName()).append("\n");
        sb.append("facultyNumber=").append(student.getFacultyNumber()).append("\n");
        sb.append("course=").append(student.getYear()).append("\n");
        sb.append("specialty=").append(student.getSpecialty().name()).append("\n");
        sb.append("group=").append(student.getGroup()).append("\n");
        sb.append("status=").append(student.getStatus().name()).append("\n");
        sb.append("DISCIPLINES\n");
        for (java.util.Map.Entry<Discipline, Double> entry : student.getEnrolledDisciplines().entrySet()) {
            Discipline d = entry.getKey();
            String grade = (entry.getValue() == null) ? "null" : String.valueOf(entry.getValue());
            sb.append(d.getName()).append("|")
                    .append(d.getDisciplineType().name()).append("|")
                    .append(d.getYear()).append("|")
                    .append(grade).append("\n");
        }
        sb.append("END\n");
        return sb.toString();
    }

    public Student deserialize(String block) {
        String[] lines = block.split("\n");
        if (lines.length < 2 || !lines[0].equals("STUDENT")) {
            throw new IllegalArgumentException("Invalid student block.");
        }

        String firstName = null, lastName = null;
        int facultyNumber = -1, course = 1, group = 1;
        Specialty specialty = null;
        Status status = Status.RECORDED;

        int i = 1;
        while (i < lines.length && !lines[i].equals("DISCIPLINES")) {
            String[] kv = lines[i].split("=", 2);
            if (kv.length == 2) {
                switch (kv[0]) {
                    case "firstName"     -> firstName = kv[1];
                    case "lastName"      -> lastName = kv[1];
                    case "facultyNumber" -> facultyNumber = Integer.parseInt(kv[1]);
                    case "course"        -> course = Integer.parseInt(kv[1]);
                    case "specialty"     -> specialty = Specialty.valueOf(kv[1]);
                    case "group"         -> group = Integer.parseInt(kv[1]);
                    case "status"        -> status = Status.valueOf(kv[1]);
                }
            }
            i++;
        }

        if (firstName == null || specialty == null || facultyNumber == -1) {
            throw new IllegalArgumentException("Missing required fields.");
        }

        Student student = new Student(firstName, lastName, facultyNumber, course, specialty, group);
        student.setYear(course);
        student.setStatus(status);

        i++; // skip DISCIPLINES line
        while (i < lines.length && !lines[i].equals("END")) {
            String[] parts = lines[i].split("\\|", 4);
            if (parts.length == 4) {
                Discipline d = new Discipline(parts[0], DisciplineType.valueOf(parts[1]), Integer.parseInt(parts[2]));
                student.setRecordedDisciplines(d);
                if (!parts[3].equals("null")) {
                    student.addGraduate(d, Double.parseDouble(parts[3]));
                }
            }
            i++;
        }
        return student;
    }
}
