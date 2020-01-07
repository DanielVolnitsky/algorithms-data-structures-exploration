package com.waytoodanny.algorithms.shuffling;

import edu.princeton.cs.algs4.StdRandom;

/**
 * The Fisher–Yates shuffle is an algorithm for generating a random permutation
 * of a finite sequence—in plain terms, the algorithm shuffles the sequence.
 * <p>
 * The modern version of the Fisher–Yates shuffle,
 * designed for computer use, was introduced by Richard Durstenfeld in 1964ю
 * <p>
 * Whereas a naive computer implementation of Fisher and Yates' method would spend
 * needless time counting the remaining numbers in step 3 above,
 * Durstenfeld's solution is to move the "struck" numbers to the end of the list
 * by swapping them with the last unstruck number at each iteration.
 * This reduces the algorithm's time complexity to O(n), compared to O(n^2) for the naive implementation.
 */
public class FisherYatesShuffler<T> implements Shuffler<T> {

  @Override
  public void shuffle(T[] items) {
    for (int last = items.length; last > 0; last--) {
      int randomIdx = StdRandom.uniform(last);
      T randomItem = items[randomIdx];
      items[randomIdx] = items[last - 1];
      items[last - 1] = randomItem;
    }
  }
}
