package com.waytoodanny.algorithms.sorting.impl;

import com.waytoodanny.algorithms.sorting.Sorter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class SorterTest {

  private final Sorter<Integer> sut = new ShellSort<>();
  @Parameter
  public Integer[] input;
  @Parameter(1)
  public Integer[] expected;

  @Parameters
  public static Iterable<Object[]> data() {
    return asList(new Object[][]{
        {new Integer[]{8}, new Integer[]{8}},
        {new Integer[]{8, 7}, new Integer[]{7, 8}},
        {new Integer[]{5, 6, 2}, new Integer[]{2, 5, 6}},
        {new Integer[]{12, 34, 54, 2, 3}, new Integer[]{2, 3, 12, 34, 54}},
        {new Integer[]{5, 1, 10, 8, 3, 2, 4, 6, 7, 9}, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}},
    });
  }

  @Test
  public void sort() {
    sut.sort(input);
    assertThat(input).isEqualTo(expected);
  }
}