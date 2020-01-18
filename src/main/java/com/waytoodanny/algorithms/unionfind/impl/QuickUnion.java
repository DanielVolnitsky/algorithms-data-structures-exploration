package com.waytoodanny.algorithms.unionfind.impl;

import com.waytoodanny.algorithms.unionfind.UnionFind;

import java.util.stream.IntStream;

/**
 * Defects:
 * - Trees can get tall;
 * - Find is too expensive (could be N array accesses).
 */
public class QuickUnion implements UnionFind {

  /**
   * Interpretation: id[i] is parent of i.
   * Root of i is id[id[id[...id[i]...]]]
   */
  private final int[] id;

  public QuickUnion(int n) {
    this.id = IntStream.range(0, n).toArray();
  }

  public QuickUnion(int[] id) {
    this.id = id;
  }

  /**
   * To merge components containing p and q,
   * set the id of p's root to the id of q's root.
   */
  @Override
  public void union(int p, int q) {
    id[root(q)] = root(p);
  }

  /**
   * Check if p and q have the same root.
   */
  @Override
  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  private int root(int i) {
    return i == id[i] ? i : root(id[i]);
  }
}
