package com.waytoodanny.algorithms.sorting;

public interface Sorter<T extends Comparable<T>> {
  void sort(T[] items);
}
