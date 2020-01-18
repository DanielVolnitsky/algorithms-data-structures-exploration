package com.waytoodanny.algorithms.unionfind.impl;

import com.waytoodanny.algorithms.unionfind.UnionFind;

import java.util.stream.IntStream;

/**
 * Union is too expensive.
 * It takes N^2 array accesses to process a sequence of N union commands on N objects.
 */
public class QuickFind implements UnionFind {

  private final int[] id;

  public QuickFind(int n) {
    this.id = IntStream.range(0, n).toArray();
  }

  public QuickFind(int[] id) {
    this.id = id;
  }

  @Override
  public void union(int p, int q) {
    int qid = id[q];
    for (int i = 0; i < id.length; i++) {
      if (id[i] == qid) {
        id[i] = id[p];
      }
    }
  }

  @Override
  public boolean connected(int p, int q) {
    return id[p] == id[q];
  }
}
