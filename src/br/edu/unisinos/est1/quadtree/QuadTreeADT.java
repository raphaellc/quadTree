package br.edu.unisinos.est1.quadtree;

public interface QuadTreeADT<K extends Comparable<K>, V> {
	   public void clear();
	   public boolean isEmpty();
	   public void insert(K x, K y, V value);
	   public void query2D(Interval2D<K> rect);
	   public void show(double w, double h);
	}
