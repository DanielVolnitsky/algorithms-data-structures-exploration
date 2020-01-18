package com.waytoodanny.coursera.week3;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BruteCollinearPointsTest {

  @Test
  public void segments() {
    BruteCollinearPoints sut = sut(
        new Point(0, 0),
        new Point(1, 1),
        new Point(2, 2),
        new Point(3, 3),

        new Point(2, 1),
        new Point(2, 3),
        new Point(2, 5),

        new Point(3, 1),
        new Point(5, 1));

    assertThat(sut.numberOfSegments()).isEqualTo(3);
  }

  @Test
  public void segments2() {
    BruteCollinearPoints sut = sut(
        new Point(0, 0),
        new Point(1, 1),
        new Point(2, 2),
        new Point(3, 3)
    );

    assertThat(sut.numberOfSegments()).isEqualTo(1);
  }

  @Test(expected = NullPointerException.class)
  public void constructorWhenPointsNull() {
    sut(null);
  }

  @Test(expected = NullPointerException.class)
  public void constructorWhenPointsContainsNullElement() {
    sut(new Point(1, 1), null, new Point(1, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorWhenPointsContainsDuplicates() {
    sut(new Point(1, 1), new Point(1, 2), new Point(1, 1));
  }

  private BruteCollinearPoints sut(Point... p) {
    return new BruteCollinearPoints(p);
  }
}