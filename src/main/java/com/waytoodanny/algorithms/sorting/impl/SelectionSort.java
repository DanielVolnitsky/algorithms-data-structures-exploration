package com.waytoodanny.algorithms.sorting.impl;

import com.waytoodanny.algorithms.sorting.Sorter;

/**
 * The selection sort algorithm sorts an array by repeatedly finding the minimum element
 * (considering ascending order) from unsorted part and putting it at the beginning.
 * <p>
 * N^2 / 2 compares and N exchanges
 */
public class SelectionSort<T extends Comparable<T>> implements Sorter<T> {

  @Override
  public void sort(T[] items) {
    int end = items.length - 1;
    for (int i = 0; i < end; i++) {
      int minIndex = i;
      for (int j = i + 1; j <= end; j++) {
        if (items[j].compareTo(items[minIndex]) < 0) {
          minIndex = j;
        }
      }
      T current = items[i];
      items[i] = items[minIndex];
      items[minIndex] = current;
    }
  }
}
