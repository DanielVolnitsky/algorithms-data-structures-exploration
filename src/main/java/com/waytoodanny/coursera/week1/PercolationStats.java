package com.waytoodanny.coursera.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private static final double CONFIDENCE_95 = 1.96;

  private final int trials;
  private final double[] thresholds;

  private Double cachedMean;
  private Double cachedStddev;

  public PercolationStats(int n, int t) {
    validateInParams(n, t);

    this.trials = t;
    this.thresholds = new double[t];

    for (int i = 0; i < trials; i++) {
      thresholds[i] = percolationThreshold(n);
    }
  }

  public static void main(String[] args) {
    PercolationStats ps = new PercolationStats(100, 100);

    StdOut.printf("mean = %f\n", ps.mean());
    StdOut.printf("stddev = %f\n", ps.stddev());
    StdOut.printf("95%% confidence interval = [%f, %f]", ps.confidenceLo(), ps.confidenceHi());
  }

  private double percolationThreshold(int size) {
    Percolation p = new Percolation(size);
    int openSitesCount = 0;

    do {
      int i, j;
      do {
        i = StdRandom.uniform(size) + 1;
        j = StdRandom.uniform(size) + 1;
      } while (p.isOpen(i, j));
      openSitesCount++;
      p.open(i, j);
    } while (!p.percolates());

    return openSitesCount / (Math.pow(size, 2));
  }

  public double mean() {
    if (cachedMean != null) {
      return cachedMean;
    }
    cachedMean = StdStats.mean(thresholds);
    return cachedMean;
  }

  public double stddev() {
    if (cachedStddev != null) {
      return cachedStddev;
    }
    cachedStddev = StdStats.stddev(thresholds);
    return cachedStddev;
  }

  public double confidenceLo() {
    return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(trials);
  }

  public double confidenceHi() {
    return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(trials);
  }

  private void validateInParams(int n, int trials) {
    if (n < 1 || trials < 1) {
      throw new IllegalArgumentException();
    }
  }
}
