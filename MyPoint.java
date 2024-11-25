public class MyPoint {
    private int x;
    private int y;

    public MyPoint(){
    }

    public MyPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[] getXY(){
        int[] a = new int[] {x,y};
        return a;
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "("+x+","+y+")";
    }

    public double distance(int x, int y){
        return Math.sqrt((x-this.x)*(x-this.x)+(y-this.y)*(y-this.y));
    }

    public double distance(MyPoint another){
        return this.distance(another.getX(), another.getY());
    }

    public double distance(){
        return distance(0,0);
    }
}
