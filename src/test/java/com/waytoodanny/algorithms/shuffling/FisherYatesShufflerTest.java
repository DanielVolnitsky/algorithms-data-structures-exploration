package com.waytoodanny.algorithms.shuffling;

import org.junit.Test;

import java.util.Arrays;

public class FisherYatesShufflerTest {

  @Test
  public void shuffleWhenOneItem() {
    FisherYatesShuffler<String> sut = new FisherYatesShuffler<>();
    String[] items = {"a"};

    System.out.println(Arrays.toString(items));
    sut.shuffle(items);
    System.out.println(Arrays.toString(items));
  }

  @Test
  public void shuffleWhenTwoItems() {
    FisherYatesShuffler<String> sut = new FisherYatesShuffler<>();
    String[] items = {"a", "b"};

    System.out.println(Arrays.toString(items));
    sut.shuffle(items);
    System.out.println(Arrays.toString(items));
  }

  @Test
  public void shuffleWhenFewItems() {
    FisherYatesShuffler<String> sut = new FisherYatesShuffler<>();
    String[] items = {"a", "b", "c", "d", "e", "f"};

    System.out.println(Arrays.toString(items));
    sut.shuffle(items);
    System.out.println(Arrays.toString(items));
  }
}