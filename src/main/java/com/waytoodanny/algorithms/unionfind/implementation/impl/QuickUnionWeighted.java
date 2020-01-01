package com.waytoodanny.algorithms.unionfind.implementation.impl;

import com.waytoodanny.algorithms.unionfind.implementation.UnionFind;

/**
 * Modify quick-union to avoid tall trees.
 * Keep track of size of each tree (number of objects).
 * Balance by linking root of smaller tree to root of larger tree
 * <p>
 * easy to improve further
 */
public class QuickUnionWeighted implements UnionFind {

  /**
   * Interpretation: id[i] is parent of i.
   * Root of i is id[id[id[...id[i]...]]]
   */
  private final int[] id;
  private final int[] sz;

  public QuickUnionWeighted(int[] id) {
    this.id = id;
    this.sz = new int[id.length];
    initializeSize();
  }

  private void initializeSize() {
    //TODO
  }

  /**
   * To merge components containing p and q,
   * set the id of p's root to the id of q's root.
   * +
   * Link root of smaller tree to root of larger tree.
   */
  @Override
  public void union(int p, int q) {
    int i = root(p);
    int j = root(q);

    if (i == j) {
      return;
    }

    if (sz[i] < sz[j]) {
      id[i] = j;
      sz[j] += sz[i];
    } else {
      id[j] = i;
      sz[i] += sz[j];
    }
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
