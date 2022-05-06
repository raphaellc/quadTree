package br.edu.unisinos.est1.quadtree;

public class QuadTreeTest {
	//size of 2D plane
	public static int width = 400;
	public static int height = 400;

	public static void main(String[] args) {
		/*
		 * StdDraw Settings
		 */
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
		QuadTree<Integer, Integer> quad = new QuadTree<>();
		quad.insert(7, 6, 76);
		quad.insert(5, 6, 56);
		quad.insert(1, 1, 11);
		quad.insert(5, 5, 55);
		quad.insert(2, 7, 27);
		quad.insert(3, 3, 33);
		quad.show(width, height);
		Interval2D<Integer> rect = new Interval2D<>(new Interval<>(1, 8), new Interval<>(4, 8));
		quad.query2D(rect);
	}

}
