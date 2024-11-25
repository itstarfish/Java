public class Main {
    public static void main(String[] args) {
        // Create MyPoint objects
        MyPoint p1 = new MyPoint(0, 0);
        MyPoint p2 = new MyPoint(3, 4);

        // Create MyLine objects using MyPoint objects
        MyLine line1 = new MyLine(p1, p2);
        System.out.println("Line1: " + line1);
        System.out.println("Length of Line1: " + line1.getLength());
        System.out.println("Gradient of Line1: " + line1.getGradient());

        // Create MyLine objects using coordinates
        MyLine line2 = new MyLine(1, 1, 4, 5);
        System.out.println("Line2: " + line2);
        System.out.println("Length of Line2: " + line2.getLength());
        System.out.println("Gradient of Line2: " + line2.getGradient());

        // Test setters and getters
        line1.setBegin(new MyPoint(2, 2));
        line1.setEnd(new MyPoint(5, 6));
        System.out.println("Updated Line1: " + line1);
        System.out.println("Updated Length of Line1: " + line1.getLength());
        System.out.println("Updated Gradient of Line1: " + line1.getGradient());
    }
}