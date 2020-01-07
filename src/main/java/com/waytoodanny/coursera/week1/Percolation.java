package com.waytoodanny.coursera.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.stream.IntStream;

public class Percolation {

  private static final int ADDITIONAL_SITES_NUMBER = 2;

  private final int singleDimensionSize;
  private final int[] sites;
  private final WeightedQuickUnionUF unionFind;
  private final int lastOpenTopSiteIndex;
  private final int lastOpenBottomSiteIndex;

  public Percolation(int n) {
    if (n < 1) {
      throw new IllegalArgumentException();
    }

    this.singleDimensionSize = n;
    this.sites = new int[n * n];
    this.unionFind = new WeightedQuickUnionUF(n * n + ADDITIONAL_SITES_NUMBER);
    this.lastOpenTopSiteIndex = n * n;
    this.lastOpenBottomSiteIndex = n * n + 1;
  }

  public void open(int row, int col) {
    validateBounds(row, col);
    sites[asOneDimensionalIndex(row, col)] = 1;
    connectToOpenNeighbours(row, col);
  }

  public boolean isOpen(int row, int col) {
    validateBounds(row, col);
    return sites[asOneDimensionalIndex(row, col)] == 1;
  }

  public boolean isFull(int row, int col) {
    validateBounds(row, col);
    return unionFind.find(lastOpenTopSiteIndex) == unionFind.find(asOneDimensionalIndex(row, col));
  }

  public boolean percolates() {
    return unionFind.find(lastOpenTopSiteIndex) == unionFind.find(lastOpenBottomSiteIndex);
  }

  public int numberOfOpenSites() {
    return (int) IntStream.of(sites).filter(s -> s == 1).count();
  }

  private int asOneDimensionalIndex(int row, int col) {
    return ((row - 1) * singleDimensionSize) + (col - 1);
  }

  private void connectToOpenNeighbours(int row, int col) {
    int index = asOneDimensionalIndex(row, col);

    tryUnionWithRightNeighbour(row, col, index);
    tryUnionWithLeftNeighbour(row, col, index);
    tryUnionWithBottomNeighbour(row, col, index);
    tryUnionWithTopNeighbour(row, col, index);
  }

  private void tryUnionWithTopNeighbour(int row, int col, int target) {
    if (row > 1) {
      tryUnion(row - 1, col, target);
    } else {
      unionFind.union(target, lastOpenTopSiteIndex);
    }
  }

  private void tryUnionWithBottomNeighbour(int row, int col, int target) {
    if (row < singleDimensionSize) {
      tryUnion(row + 1, col, target);
    } else {
      unionFind.union(target, lastOpenBottomSiteIndex);
    }
  }

  private void tryUnionWithLeftNeighbour(int row, int col, int target) {
    if (col > 1) {
      tryUnion(row, col - 1, target);
    }
  }

  private void tryUnionWithRightNeighbour(int row, int col, int target) {
    if (col < singleDimensionSize) {
      tryUnion(row, col + 1, target);
    }
  }

  private void tryUnion(int row, int col, int target) {
    if (isOpen(row, col)) {
      unionFind.union(asOneDimensionalIndex(row, col), target);
    }
  }

  private void validateBounds(int row, int col) {
    if (!boundsValid(row, col)) {
      throw new IllegalArgumentException();
    }
  }

  private boolean boundsValid(int row, int col) {
    return row > 0 && row <= singleDimensionSize && col > 0 && col <= singleDimensionSize;
  }
}