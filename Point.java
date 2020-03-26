import java.awt.*;

public class Point {
    private double x,y;

    //setter
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    //getter
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    //print
    public String toString(){
        return String.format("Point: %f | %f", this.x, this.y);
    }

    //show
    public void show(Color color){
        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(this.x, this.y);
    }
}
