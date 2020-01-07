package com.waytoodanny.coursera.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;

public class Permutation {
  public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);

    RandomizedQueue<String> rq = new RandomizedQueue<>();
    Arrays.stream(StdIn.readAllStrings()).forEach(rq::enqueue);
    Iterator<String> it = rq.iterator();

    for (int i = 0; i < k; i++) {
      StdOut.println(it.next());
    }
  }
}
