package com.waytoodanny.algorithms.unionfind.application.percolation;

import com.waytoodanny.coursera.week1.Percolation;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationTest {

  @Test
  public void percolatesWhenThereIsOnePathFromBottomSiteToTopSite() {
    Percolation sut = new Percolation(3);

    sut.open(1, 1);
    sut.open(2, 1);
    sut.open(2, 2);
    sut.open(3, 2);

    assertTrue(sut.percolates());
  }

  @Test
  public void percolatesWhenThereIsFewPathsFromBottomSiteToTopSite() {
    Percolation sut = new Percolation(3);

    sut.open(1, 1);
    sut.open(1, 2);
    sut.open(2, 1);
    sut.open(2, 2);
    sut.open(3, 1);
    sut.open(3, 2);

    assertTrue(sut.percolates());
  }

  @Test
  public void percolatesWhenAllTopAndBottomSitesOpenButNoPathBetweenThem() {
    Percolation sut = new Percolation(3);

    sut.open(1, 1);
    sut.open(1, 2);
    sut.open(1, 3);
    sut.open(3, 1);
    sut.open(3, 2);
    sut.open(3, 3);

    assertFalse(sut.percolates());
  }

  //------------------isFull-----------------

  @Test
  public void isFullWhenSiteInTopRow() {
    Percolation sut = new Percolation(4);

    sut.open(1, 3);
    assertTrue(sut.isFull(1, 3));
  }

  @Test
  public void isFullWhenSiteHasStraightPathToTopRowSite() {
    Percolation sut = new Percolation(3);

    sut.open(1, 2);
    sut.open(2, 2);
    sut.open(3, 2);

    assertTrue(sut.isFull(3, 2));
  }

  @Test
  public void isFullWhenSiteHasIndirectPathToTopRowSite() {
    Percolation sut = new Percolation(3);

    sut.open(1, 1);
    sut.open(1, 2);
    sut.open(1, 3);
    sut.open(2, 3);
    sut.open(3, 3);
    sut.open(3, 2);

    assertTrue(sut.isFull(3, 2));
  }

  @Test
  public void isFullWhenSiteHasNoPathToTopRowSite() {
    Percolation sut = new Percolation(3);

    sut.open(1, 1);
    sut.open(2, 1);
    sut.open(3, 2);

    assertFalse(sut.isFull(3, 2));
  }

  //------------------numberOfOpenSites-----------------

  @Test
  public void numberOfOpenSitesWhenAllClosed() {
    Percolation sut = new Percolation(4);
    assertThat(sut.numberOfOpenSites(), CoreMatchers.equalTo(0));
  }

  @Test
  public void numberOfOpenSitesWhenSomeOpen() {
    Percolation sut = new Percolation(4);

    sut.open(1, 1);
    sut.open(4, 4);

    assertThat(sut.numberOfOpenSites(), CoreMatchers.equalTo(2));
  }

  //------------------isOpen------------------

  @Test
  public void isOpenWhenSiteIsOpen() {
    Percolation sut = new Percolation(4);

    assertFalse(sut.isOpen(1, 1));
    sut.open(1, 1);
    assertTrue(sut.isOpen(1, 1));
  }

  @Test
  public void isOpenWhenSiteIsBlocked() {
    Percolation sut = new Percolation(4);

    assertFalse(sut.isOpen(1, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void isOpenWhenOutOfBounds() {
    Percolation sut = new Percolation(4);
    sut.isOpen(0, 1);
  }

  //------------------Open------------------

  @Test
  public void openWhenWithinBounds() {
    Percolation sut = new Percolation(4);

    sut.open(1, 1);
    sut.open(1, 4);
    sut.open(4, 1);
    sut.open(4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void openWhenOutOfLeftTopBound() {
    Percolation sut = new Percolation(4);
    sut.open(0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void openWhenOutOfRightTopBound() {
    Percolation sut = new Percolation(4);
    sut.open(1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void openWhenOutOfLeftBottomBound() {
    Percolation sut = new Percolation(4);
    sut.open(5, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void openWhenOutOfRightBottomBound() {
    Percolation sut = new Percolation(4);
    sut.open(4, 5);
  }
}