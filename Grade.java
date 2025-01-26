import java.util.ArrayList;

public class Grade {
    private ArrayList<Integer> grades = new ArrayList<>();

    public Grade(ArrayList<Integer> grades) {
        this.grades = grades;
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Integer> grades) {
        this.grades = grades;
    }
}
