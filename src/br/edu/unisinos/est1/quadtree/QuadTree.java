package br.edu.unisinos.est1.quadtree;

public class QuadTree<K extends Comparable<K>, V> implements QuadTreeADT<K, V> {
	private Node root;

	private class Node {
		private K x, y;
		private Node NW, NE, SE, SW;
		private V value;

		public Node(K x, K y, V value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	public void insert(K x, K y, V value) {
		root = insert(root, x, y, value);
	}

	private Node insert(Node node, K x, K y, V value) {
		if (node == null)
			return new Node(x, y, value);
		else if (less(x, node.x) && !less(y, node.y))
			node.NW = insert(node.NW, x, y, value);
		else if (less(x, node.x) && less(y, node.y))
			node.SW = insert(node.SW, x, y, value);
		else if (!less(x, node.x) && !less(y, node.y))
			node.NE = insert(node.NE, x, y, value);
		else if (!less(x, node.x) && less(y, node.y))
			node.SE = insert(node.SE, x, y, value);

		return node;
	}

	private boolean less(K k1, K k2) {
		return k1.compareTo(k2) < 0;
	}

	public void query2D(Interval2D<K> rect) {
		query2D(root, rect);
	}

	private void query2D(Node node, Interval2D<K> rect) {
		if (node == null)
			return;

		K xMin = rect.intervalX.min();
		K yMin = rect.intervalY.min();
		K xMax = rect.intervalX.max();
		K yMax = rect.intervalY.max();
		if (rect.contains(node.x, node.y))
			System.out.println("    (" + node.x + ", " + node.y + ") " + node.value);
		if (less(xMin, node.x) && !less(yMax, node.y)) query2D(node.NW, rect);
		if (less(xMin, node.x) && less(yMin, node.y)) query2D(node.SW, rect);
		if (!less(xMax, node.x) && !less(yMax, node.y)) query2D(node.NE, rect);
		if (!less(xMax, node.x) && less(yMin, node.y)) query2D(node.SE, rect);
	}

	public void show(double w, double h) {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.setPenRadius(0.005);
		//StdDraw.rectangle(w/2, h/2, w/2, h/2);
		show(root, w / 2, h / 2, w / 2, h / 2);
		return;
	}

	private void show(Node node, double center_x, double center_y, double w, double h) {
		double halfwidth = w / 2;
		double halfheight = h / 2;
		if (node == null)
			return;
		StdDraw.rectangle(center_x, center_y, w, h);
		if (node.NW != null)
			show(node.NW, center_x - halfwidth, center_y + halfheight, halfwidth, halfheight);
		if (node.NE != null)
			show(node.NE, center_x + halfwidth, center_y + halfheight, halfwidth, halfheight);
		if (node.SW != null)
			show(node.SW, center_x - halfwidth, center_y - halfheight, halfwidth, halfheight);
		if (node.SE != null)
			show(node.SE, center_x + halfwidth, center_y - halfheight, halfwidth, halfheight);
		return;
	}
}