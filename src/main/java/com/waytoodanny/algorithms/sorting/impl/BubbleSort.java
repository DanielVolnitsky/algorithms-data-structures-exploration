package com.waytoodanny.algorithms.sorting.impl;

import com.waytoodanny.algorithms.sorting.Sorter;

public class BubbleSort<T extends Comparable<T>> implements Sorter<T> {

  @Override
  public void sort(T[] items) {
    int end = items.length - 1;
    for (int i = 0; i <= end; end--) {
      for (int j = 0; j < end; j++) {
        if (items[j].compareTo(items[j + 1]) > 0) {
          T current = items[j];
          items[j] = items[j + 1];
          items[j + 1] = current;
        }
      }
    }
  }
}
