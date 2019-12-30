package com.waytoodanny.algorithms.unionfind.application.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.stream.IntStream;

/**
 * performs a series of computational experiments
 */
public class PercolationStats {

  private final int trials;
  private final double[] thresholds;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    validateInParams(n, trials);

    this.trials = trials;
    this.thresholds = new double[trials];

    IntStream.range(0, trials)
        .parallel()
        .forEach(i -> thresholds[i] = percolationThreshold(n));
  }

  // test client (see below)
  public static void main(String[] args) {
    int size = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);

    PercolationStats ps = new PercolationStats(size, trials);

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

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(thresholds);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(thresholds);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - 1.96 * stddev() / Math.sqrt(trials);
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + 1.96 * stddev() / Math.sqrt(trials);
  }

  private void validateInParams(int n, int trials) {
    if (n < 1 || trials < 1) {
      throw new IllegalArgumentException();
    }
  }
}
