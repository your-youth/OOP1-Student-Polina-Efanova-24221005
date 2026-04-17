package utils;

import enums.DisciplineType;
import enums.Specialty;
import models.Discipline;
import java.util.ArrayList;
import java.time.Year;
import java.util.*;

public class ProgramCurriculum {

    private static final Map<Specialty, List<Discipline>> curriculum = new HashMap<>();
    static {
        curriculum.put(Specialty.SOFTWARE_AND_INTERNET_TECHNOLOGIES, Arrays.asList(
                //1 course
                new Discipline("Web Design", DisciplineType.COMPULSORY, 1),
                new Discipline("Programming Fundamentals", DisciplineType.COMPULSORY, 1),
                new Discipline("Digital Logic", DisciplineType.COMPULSORY, 1),
                // 2 course
                new Discipline("Data Base", DisciplineType.COMPULSORY, 2),
                new Discipline("Practical Training", DisciplineType.ELECTIVE, 2),
                new Discipline("OOP", DisciplineType.COMPULSORY, 2),
                //3 course
                new Discipline("Internet Technologies", DisciplineType.COMPULSORY, 3),
                new Discipline("Interface Design", DisciplineType.ELECTIVE, 3),
                new Discipline("Software Projects Management", DisciplineType.COMPULSORY, 3)

        ));
        curriculum.put(Specialty.ARTIFICIAL_INTELLIGENCE, Arrays.asList(
                //1 course
                new Discipline("Mathematics", DisciplineType.COMPULSORY, 1),
                new Discipline("Discrete Structures", DisciplineType.COMPULSORY, 1),
                new Discipline("Probability Theory for Computer Science", DisciplineType.ELECTIVE, 1),
                //2 course
                new Discipline("Computer Organization", DisciplineType.COMPULSORY, 2),
                new Discipline("Digital Signal Processing", DisciplineType.COMPULSORY, 2),
                new Discipline("Fundamentals of Artificial Intelligence", DisciplineType.COMPULSORY, 2),
                //3 course
                new Discipline("Machine Learning", DisciplineType.COMPULSORY, 3),
                new Discipline("Neural Networks", DisciplineType.ELECTIVE, 3)
        ));
        curriculum.put(Specialty.NAVIGATION, Arrays.asList(
                //1 course
                new Discipline("English Language - specialized", DisciplineType.ELECTIVE, 1),
                new Discipline("Seamanship", DisciplineType.COMPULSORY, 1),
                new Discipline("Electrical Engineering and Electronics", DisciplineType.COMPULSORY, 1),
                //2 course
                new Discipline("Basics of Navigation", DisciplineType.COMPULSORY, 2),
                new Discipline("Specialized Sport Activities", DisciplineType.ELECTIVE, 2),
                new Discipline("Collision Regulations", DisciplineType.COMPULSORY, 2),
                //3 course
                new Discipline("Ship Power Plants", DisciplineType.COMPULSORY, 3),
                new Discipline("Bridge Equipment and Ship Systems", DisciplineType.COMPULSORY, 3),
                new Discipline("Ships Operation and Cargo Handling", DisciplineType.COMPULSORY, 3)
        ));
    }

        public static List<Discipline> getDisciplines(Specialty specialty){
            return curriculum.getOrDefault(specialty, new ArrayList<>());
        }
        public static List<Discipline> getDisciplinesByYear(Specialty specialty, int year){
        List<Discipline> result = new ArrayList<>();
        for (Discipline d : getDisciplines(specialty)){
            if (d.getYear() == year) {
                result.add(d);
            }
        }
        return result;

    }

}
