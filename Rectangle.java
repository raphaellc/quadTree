import org.w3c.dom.css.Rect;

public class Rectangle {
    private double x;
    private double y;
    private double w;
    private double h;

    //constructor
    public Rectangle(double xCoordinate, double yCoordinate, double halfwidth, double halfheight){
        this.x = xCoordinate;
        this.y = yCoordinate;
        this.w = halfwidth;
        this.h = halfheight;
    }

    //getter
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getW() {
        return w;
    }
    public double getH() {
        return h;
    }

    public boolean contains(Point p){
        //check upper limit
        if (p.getY() >= (this.y + this.h)) return false;

        //check lower limit
        if (p.getY() <= (this.y - this.h)) return false;

        //check limit left
        if (p.getX() <= (this.x - this.w)) return false;

        //check limit right
        if (p.getX() >= (this.x + this.w)) return false;

        return true;
    }

    public boolean intersects(Rectangle otherRect){
        double otherUpperLimit = otherRect.getY() + otherRect.getH();
        double otherLowerLimit = otherRect.getY() - otherRect.getH();
        double otherLeftLimit = otherRect.getX() - otherRect.getW();
        double otherRightLimit = otherRect.getX() + otherRect.getW();

        double thisUpperLimit = this.y + this.h;
        double thisLowerLimit = this.y - this.h;
        double thisLeftLimit = this.x - this.w;
        double thisRightLimit = this.x + this.w;

        boolean bool = !(otherLeftLimit > thisRightLimit ||
                otherRightLimit < thisLeftLimit ||
                otherLowerLimit > thisUpperLimit ||
                otherUpperLimit < thisLowerLimit
        );

        return bool;
    }
}
