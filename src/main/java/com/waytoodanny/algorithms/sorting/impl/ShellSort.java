package com.waytoodanny.algorithms.sorting.impl;

import com.waytoodanny.algorithms.sorting.Sorter;

public class ShellSort<T extends Comparable<T>> implements Sorter<T> {

  @Override
  public void sort(T[] items) {
    int gap = gap(items.length);
    while (gap > 0) {
      for (int i = gap; i < items.length; i++) {
        T temp = items[i];
        int j;
        for (j = i; j >= gap && items[j - gap].compareTo(temp) > 0; j -= gap) {
          items[j] = items[j - gap];
        }
        items[j] = temp;
      }
      gap--;
    }
  }

  private int gap(int size) {
    return size / 2;
  }
}
