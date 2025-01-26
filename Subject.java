import java.util.ArrayList;

public class Subject implements Comparable<Subject> {
    private String name;
    private ArrayList<Grade> grades = new ArrayList<>();

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        String output = name;
        return output;
    }

    @Override
    public int compareTo(Subject o) {
        return this.name.compareTo(o.name);
    }
}
