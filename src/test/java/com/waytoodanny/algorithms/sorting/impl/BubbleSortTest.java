package com.waytoodanny.algorithms.sorting.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class BubbleSortTest {

  private final BubbleSort<Integer> sut = new BubbleSort<>();
  @Parameter
  public Integer[] input;
  @Parameter(1)
  public Integer[] expected;

  @Parameterized.Parameters(name = "{index}: fib[{0}]={1}")
  public static Iterable<Object[]> data() {
    return asList(new Object[][]{
        {new Integer[]{8}, new Integer[]{8}},
        {new Integer[]{8, 7}, new Integer[]{7, 8}},
        {new Integer[]{5, 1, 10, 8, 3, 2, 4, 6, 7, 9}, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}},
    });
  }

  @Test
  public void sort() {
    sut.sort(input);
    assertThat(input).isEqualTo(expected);
  }
}