package br.edu.unisinos.est1.quadtree;

public class Interval2D<K extends Comparable<K>> { 
    public final Interval<K> intervalX;   
    public final Interval<K> intervalY;   
   
    public Interval2D(Interval<K> intervalX, Interval<K> intervalY) {
        this.intervalX = intervalX;
        this.intervalY = intervalY;
    }

    public boolean contains(K x, K y) { return intervalX.contains(x) && intervalY.contains(y); }
}
