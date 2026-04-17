package models;

import enums.DisciplineType;

import java.util.Objects;

public class Discipline {
    private String name;
    private DisciplineType disciplineType;
    private int year;

    public Discipline(String name, DisciplineType disciplineType, int year) {
        this.name = name;
        this.disciplineType = disciplineType;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public DisciplineType getDisciplineType() {
        return disciplineType;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Discipline that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "name='" + name + '\'' +
                ", disciplineType=" + disciplineType +
                ", year=" + year +
                '}';
    }
}
