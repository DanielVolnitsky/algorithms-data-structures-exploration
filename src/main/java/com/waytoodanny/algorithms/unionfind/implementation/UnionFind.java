package com.waytoodanny.algorithms.unionfind.implementation;

public interface UnionFind {

  /**
   * add connection between p and q
   */
  void union(int p, int q);

  /**
   * are p and q in the same component?
   */
  boolean connected(int p, int q);
}
