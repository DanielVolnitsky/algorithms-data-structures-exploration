package com.waytoodanny.algorithms.sorting.impl;

import com.waytoodanny.algorithms.sorting.Sorter;

/**
 * ~ 1/4 N^2 compares and ~ 1/4 N^2 exchanges on average
 */
public class InsertionSort<T extends Comparable<T>> implements Sorter<T> {

  @Override
  public void sort(T[] items) {
    for (int i = 1; i < items.length; i++) {
      T curr = items[i];
      for (int j = i - 1; j >= 0; j--) {
        T prev = items[j];
        if (prev.compareTo(curr) > 0) {
          items[j] = curr;
          items[j + 1] = prev;
        } else {
          break;
        }
      }
    }
  }
}
