package com.waytoodanny.algorithms.unionfind.application.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.stream.IntStream;

public class Percolation {

  private final int[] sites;
  private final WeightedQuickUnionUF unionFind;
  private final int width;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n < 1) {
      throw new IllegalArgumentException();
    }

    this.width = n;
    this.sites = new int[n * n];
    this.unionFind = new WeightedQuickUnionUF(n * n);
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    validateBounds(row, col);
    sites[asOneDimensionalIndex(row, col)] = 1;
    connectToOpenNeighbours(row, col);
  }

  //is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    validateBounds(row, col);
    return sites[asOneDimensionalIndex(row, col)] == 1;
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    validateBounds(row, col);
    return isFull(asOneDimensionalIndex(row, col));
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return (int) IntStream.of(sites).filter(s -> s == 1).count();
  }

  // Does the system percolate?
  public boolean percolates() {
    int skipToBottomRow = width * width - width;
    return IntStream.range(0, sites.length)
        .skip(skipToBottomRow)
        .parallel()
        .filter(this::isOpen)
        .filter(this::isFull)
        .findAny()
        .isPresent();
  }

  /*private*/ int[] neighboursIndices(int row, int col) {
    int top = isValidBounds(row - 1, col) ? asOneDimensionalIndex(row - 1, col) : -1;
    int right = isValidBounds(row, col + 1) ? asOneDimensionalIndex(row, col + 1) : -1;
    int bottom = isValidBounds(row + 1, col) ? asOneDimensionalIndex(row + 1, col) : -1;
    int left = isValidBounds(row, col - 1) ? asOneDimensionalIndex(row, col - 1) : -1;

    return IntStream.of(top, right, bottom, left).filter(n -> n != -1).toArray();
  }

  /*private*/ int asOneDimensionalIndex(int row, int col) {
    return ((row - 1) * width) + (col - 1);
  }

  private boolean isFull(int oneDimIdx) {
    return IntStream.range(0, width)
        .filter(i -> sites[i] == 1)
        .filter(i -> unionFind.find(oneDimIdx) == unionFind.find(i))
        .findAny()
        .isPresent();
  }

  private void connectToOpenNeighbours(int row, int col) {
    int oneDimIdx = asOneDimensionalIndex(row, col);
    IntStream.of(neighboursIndices(row, col))
        .filter(i -> sites[i] == 1)
        .forEach(i -> unionFind.union(i, oneDimIdx));
  }

  private boolean isOpen(int oneDimIdx) {
    return sites[oneDimIdx] == 1;
  }

  private void validateBounds(int row, int col) {
    if (!isValidBounds(row, col)) {
      throw new IllegalArgumentException();
    }
  }

  private boolean isValidBounds(int row, int col) {
    return row > 0 && row <= width && col > 0 && col <= width;
  }
}