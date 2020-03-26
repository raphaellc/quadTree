import java.util.ArrayList;

public class QuadTree {
    private Rectangle boundary;
    private double x;
    private double y;
    private double w;
    private double h;

    private int capacity = Main.capacity;
    private ArrayList<Point> points = new ArrayList<>();

    private QuadTree northwest;
    private QuadTree northeast;
    private QuadTree southwest;
    private QuadTree southeast;

    private boolean divided = false;

    //constuctor
    public QuadTree(Rectangle boundary) {
        this.boundary = boundary;

        this.x = boundary.getX();
        this.y = boundary.getY();
        this.w = boundary.getW();
        this.h = boundary.getH();
    }

    //insert point --> true if insertion successful
    // (this is needed so a point doesn't land in 2 Quadrants at the same time)
    public boolean insert(Point p) {

        //check if point is in this quadtrees boundaries
        if (!this.boundary.contains(p)) {
            return false;
        }

        //check if this quadtree has room for this point
        if (points.size() < this.capacity) {
            this.points.add(p);
            return true;

            //send to children
        } else {
            //check if quadtree has children
            if (!this.divided) {
                this.subdivide();
            }

            //distribute
            if (this.northwest.insert(p)) return true;
            else if (this.northeast.insert(p)) return true;
            else if (this.southwest.insert(p)) return true;
            else if(this.southeast.insert(p)) return true;
            else return false;
        }
    }

    public void subdivide() {
        double halfwidth = this.w / 2;
        double halfheight = this.h / 2;

        this.northwest = new QuadTree(new Rectangle(this.x - halfwidth, this.y + halfheight, halfwidth, halfheight));
        this.northeast = new QuadTree(new Rectangle(this.x + halfwidth, this.y + halfheight, halfwidth, halfheight));
        this.southwest = new QuadTree(new Rectangle(this.x - halfwidth, this.y - halfheight, halfwidth, halfheight));
        this.southeast = new QuadTree(new Rectangle(this.x + halfwidth, this.y - halfheight, halfwidth, halfheight));

        this.divided = true;
    }


    public ArrayList<Point> search(Rectangle searchBoundary){
        ArrayList<Point> found = new ArrayList<Point>();

        //check if searchBoundary and this.boundary have any coordinates in common
        if (!this.boundary.intersects(searchBoundary)) {
            return found; //empty

        }else {
            for (Point p : this.points) {
                if (searchBoundary.contains(p)) {
                    found.add(p);
                }
            }

            if (this.divided) {
                //all returned points from children
                ArrayList<Point> nw = northwest.search(searchBoundary);
                ArrayList<Point> ne = northeast.search(searchBoundary);
                ArrayList<Point> sw = southwest.search(searchBoundary);
                ArrayList<Point> se = southeast.search(searchBoundary);

                //saving everything in found
                for (Point p : nw) found.add(p);
                for (Point p : ne) found.add(p);
                for (Point p : sw) found.add(p);
                for (Point p : se) found.add(p);
            }
            return found;
        }
    }


    //visualization
    public void show() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.002);

        double x = this.boundary.getX();
        double y = this.boundary.getY();
        double w = this.boundary.getW();
        double h = this.boundary.getH();

        StdDraw.rectangle(x, y, w, h);

        if (this.divided) {
            this.northwest.show();
            this.northeast.show();
            this.southwest.show();
            this.southeast.show();
        }

        //Show points
        for (Point point : points) {
            point.show(Main.c1);
        }
    }
}