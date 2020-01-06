package com.waytoodanny.datastructures.assignment.deque;

import com.waytoodanny.datastructures.assignment.Deque;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DequeTest {

  @Test
  public void isEmptyWhenJustCreated() {
    Deque<String> sut = new Deque<>();
    assertTrue(sut.isEmpty());
  }

  @Test
  public void isEmptyWhenAddedAndRemovedSameElements() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");
    sut.removeFirst();
    assertTrue(sut.isEmpty());
  }

  @Test
  public void isEmptyWhenAddedItem() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");
    assertFalse(sut.isEmpty());
  }

  @Test
  public void isEmptyWhenAddedMoreThanRemoved() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");
    sut.addFirst("b");
    sut.removeFirst();
    assertFalse(sut.isEmpty());
  }

  @Test
  public void sizeWhenJustInitialized() {
    Deque<String> sut = new Deque<>();
    assertThat(sut.size()).isEqualTo(0);
  }

  @Test
  public void sizeWhenAddedItem() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");
    assertThat(sut.size()).isEqualTo(1);
  }

  @Test
  public void sizeWhenAddedAndRemovedSameItem() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");
    sut.removeFirst();
    assertThat(sut.size()).isEqualTo(0);
  }

  @Test
  public void sizeWhenAddedMoreThanRemoved() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");
    sut.addFirst("b");
    sut.removeFirst();
    assertThat(sut.size()).isEqualTo(1);
  }

  @Test
  public void addFirstWhenAddingFirstItem() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");

    String result = sut.removeFirst();
    assertThat(result).isEqualTo("a");
  }

  @Test
  public void addFirstWhenAddingFewItems() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");
    sut.addFirst("b");

    String firstRemoved = sut.removeFirst();
    String secondRemoved = sut.removeFirst();

    assertThat(firstRemoved).isEqualTo("b");
    assertThat(secondRemoved).isEqualTo("a");
  }

  @Test(expected = IllegalArgumentException.class)
  public void addFirstWhenAddingNull() {
    new Deque<>().addFirst(null);
  }

  @Test
  public void addLastWhenAddingFirstItem() {
    Deque<String> sut = new Deque<>();
    sut.addLast("a");

    String result = sut.removeLast();
    assertThat(result).isEqualTo("a");
  }

  @Test
  public void addLastWhenAddingFewItems() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");
    sut.addFirst("b");

    String firstRemoved = sut.removeLast();
    String secondRemoved = sut.removeLast();

    assertThat(firstRemoved).isEqualTo("a");
    assertThat(secondRemoved).isEqualTo("b");
  }

  @Test(expected = IllegalArgumentException.class)
  public void addLastWhenAddingNull() {
    new Deque<>().addLast(null);
  }

  @Test
  public void removeFirstWhenAddedFirstBefore() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");

    String first = sut.removeFirst();
    assertThat(first).isEqualTo("a");
  }

  @Test
  public void removeFirstWhenAddedFewItemsBefore() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");
    sut.addFirst("b");

    String firstRemoved = sut.removeFirst();
    String secondRemoved = sut.removeFirst();

    assertThat(firstRemoved).isEqualTo("b");
    assertThat(secondRemoved).isEqualTo("a");
  }

  @Test(expected = NoSuchElementException.class)
  public void removeFirstWhenIsEmpty() {
    new Deque<>().removeFirst();
  }

  @Test
  public void removeLastWhenContainsOneItem() {
    Deque<String> sut = new Deque<>();
    sut.addLast("a");

    String result = sut.removeLast();
    assertThat(result).isEqualTo("a");
  }

  @Test
  public void removeLastWhenContainsFewItems() {
    Deque<String> sut = new Deque<>();
    sut.addLast("a");
    sut.addLast("b");

    String firstRemoved = sut.removeLast();
    String secondRemoved = sut.removeLast();

    assertThat(firstRemoved).isEqualTo("b");
    assertThat(secondRemoved).isEqualTo("a");
  }

  @Test(expected = NoSuchElementException.class)
  public void removeLastWhenNoItemsPresent() {
    new Deque<>().removeLast();
  }

  @Test
  public void iteratorWhenContainsOneItem() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");

    Iterator<String> iterator = sut.iterator();
    String result = iterator.next();
    assertThat(result).isEqualTo("a");
  }

  @Test
  public void iteratorWhenContainsFewItems() {
    Deque<String> sut = new Deque<>();
    sut.addFirst("a");
    sut.addFirst("b");

    Iterator<String> iterator = sut.iterator();
    String firstResult = iterator.next();
    String secondResult = iterator.next();

    assertThat(firstResult).isEqualTo("a");
    assertThat(secondResult).isEqualTo("b");
  }

  @Test(expected = NoSuchElementException.class)
  public void iteratorWhenContainsNoItems() {
    new Deque<>().iterator().next();
  }
}