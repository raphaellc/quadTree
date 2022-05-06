package br.edu.unisinos.est1.quadtree;

public class Interval<K extends Comparable<K>> {
	  private final K min; 
	  private final K max; 

	  public Interval(K min, K max) {
	    this.min = min;
	    this.max = max;
	  }

	  public K min() { return min; }
	  public K max() { return max; }

	  public boolean contains(K x) {
	    return !less(x, min) && !less(max, x);
	  }

	  private boolean less(K x, K y) { return x.compareTo(y) < 0; }
	}
