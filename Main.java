public class Main {
    public static void main(String[] args) {


        Subject sj1 = new Subject("Matematika");
        Subject sj2 = new Subject("Anglu");
        Subject sj3 = new Subject("Lietuviu");

        Student s1 = new Student("Jonas", "Jonaitis");
        Student s2 = new Student("Algis", "Algaitis");


        s1.getSubjects().sort(null);

        System.out.println(s1.toString());
        for (Subject temp:s1.getSubjects()) {
            System.out.println(temp.toString());
        }

        System.out.println(s2.toString());
        for (Subject temp:s2.getSubjects()) {
            System.out.println(temp.toString());
        }
    }
}