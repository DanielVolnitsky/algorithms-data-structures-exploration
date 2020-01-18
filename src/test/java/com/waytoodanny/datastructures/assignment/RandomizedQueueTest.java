package com.waytoodanny.datastructures.assignment;

import com.waytoodanny.coursera.week2.RandomizedQueue;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RandomizedQueueTest {

  @Test
  public void isEmptyWhenJustCreated() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    assertTrue(sut.isEmpty());
  }

  @Test
  public void isEmptyWhenAddedOneItem() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    sut.enqueue("a");

    assertFalse(sut.isEmpty());
  }

  @Test
  public void isEmptyWhenAddedAndDeletedSameAmount() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    sut.enqueue("a");
    sut.dequeue();

    assertTrue(sut.isEmpty());
  }

  @Test
  public void sizeWhenJustCreated() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    assertThat(sut.size()).isEqualTo(0);
  }

  @Test
  public void sizeWhenEnqueueOneItem() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    sut.enqueue("a");
    assertThat(sut.size()).isEqualTo(1);
  }

  @Test
  public void sizeWhenEnqueueAndRemove() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    sut.enqueue("a");
    sut.dequeue();
    assertThat(sut.size()).isEqualTo(0);
  }

  @Test
  public void sizeWhenEnqueueMoreThanRemove() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    sut.enqueue("a");
    sut.enqueue("b");
    sut.dequeue();
    assertThat(sut.size()).isEqualTo(1);
  }

  @Test
  public void enqueueOneItem() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    sut.enqueue("a");

    assertThat(sut.dequeue()).isEqualTo("a");
  }

  @Test
  public void enqueueFewItems() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    sut.enqueue("a");
    sut.enqueue("b");

    assertThat(asList(sut.dequeue(), sut.dequeue()))
        .containsExactlyInAnyOrder("a", "b");
  }

  @Test(expected = IllegalArgumentException.class)
  public void enqueueWhenItemIsNull() {
    new RandomizedQueue<>().enqueue(null);
  }

  @Test
  public void enqueueWhenInitialCapacityOverflown() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    sut.enqueue("a");
    sut.enqueue("b");
    sut.enqueue("c");

    assertThat(sut.size()).isEqualTo(3);
    assertThat(asList(sut.dequeue(), sut.dequeue(), sut.dequeue()))
        .containsExactlyInAnyOrder("a", "b", "c");
  }

  @Test
  public void dequeue() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
  }

  @Test(expected = NoSuchElementException.class)
  public void dequeueWhenJustInitialized() {
    new RandomizedQueue<>().dequeue();
  }

  @Test(expected = NoSuchElementException.class)
  public void dequeueWhenNoItemsLeft() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    sut.enqueue("a");
    sut.dequeue();
    sut.dequeue();
  }

  @Test(expected = NoSuchElementException.class)
  public void sampleWhenJustInitialized() {
    new RandomizedQueue<>().sample();
  }

  @Test
  public void sampleWhenAddedOneItem() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    sut.enqueue("a");

    assertThat(sut.sample()).isEqualTo("a");
  }

  @Test
  public void sampleWhenAddedFewItems() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    sut.enqueue("a");
    sut.enqueue("b");
    sut.enqueue("c");

    assertThat(asList(sut.sample(), sut.sample(), sut.sample()))
        .containsAnyOf("a", "b", "c");
  }

  @Test
  public void iterator() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();
    sut.enqueue("a");
    sut.enqueue("b");
    sut.enqueue("c");

    Iterator<String> it = sut.iterator();
    List<String> result = asList(it.next(), it.next(), it.next());

    assertThat(result)
        .containsExactlyInAnyOrder("a", "b", "c");
  }

  @Test(expected = NoSuchElementException.class)
  public void iteratorWhenNoNext() {
    RandomizedQueue<String> sut = new RandomizedQueue<>();

    sut.enqueue("a");

    Iterator<String> it = sut.iterator();
    it.next();
    it.next();
  }
}