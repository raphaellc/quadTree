import java.awt.*;
import java.util.ArrayList;

public class Main {
    /**
     * Interface
     */
    //threshold of quadtree for points per quadrant
    public static int capacity = 1;

    //size of 2D plane
    public static int width = 400;
    public static int height = 400;

    //num of random points put into quadtree
    public static int num = 500;
    public static Color c1 = StdDraw.LIGHT_GRAY;

    //searchBoundary
    public static double searchX = 200;
    public static double searchY = 200;
    public static double searchWidth = 120;
    public static double searchHeight = 50;
    public static Color c2 = StdDraw.GREEN;


    //------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        /*
         * StdDraw Settings
         */
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        /*
         * QuadTree Settings
         */
        Rectangle boundary = new Rectangle(width / 2, height / 2, width / 2, height / 2);
        QuadTree quadTree = new QuadTree(boundary);

        /*
         * Point Input
         */
        //random 2d Points to QuadTree
        for (int i = 0; i < num; i++) {
            Point p = new Point(Math.random() * width, Math.random() * height);
            System.out.println(p);
            quadTree.insert(p);
        }

        /*
         * Visualization
         */
        quadTree.show();

        //search rectangle
        StdDraw.setPenColor(c2);
        StdDraw.setPenRadius(0.005);
        Rectangle searchBoundary = new Rectangle(searchX, searchY, searchWidth, searchHeight);
        StdDraw.rectangle(searchX, searchY, searchWidth, searchHeight);

        ArrayList<Point> searchResult = quadTree.search(searchBoundary);
        for (Point point : searchResult) {
            point.show(c2);
        }
    }
}

