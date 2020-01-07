package com.waytoodanny.datastructures.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue,
 * except that the item removed is chosen uniformly at random among items in the data structure.
 */
public class RandomizedQueue<T> implements Iterable<T> {

  private static final int INITIAL_CAPACITY = 2;

  private T[] items;
  private int capacity;
  private int size;

  public RandomizedQueue() {
    this.items = (T[]) new Object[INITIAL_CAPACITY];
    this.capacity = INITIAL_CAPACITY;
  }

  public static void main(String[] args) {
    RandomizedQueue<String> rq = new RandomizedQueue<>();

    StdOut.println(rq.isEmpty());
    StdOut.println(rq.size());

    rq.enqueue("a");
    rq.enqueue("b");
    rq.enqueue("c");

    StdOut.println(rq.dequeue());
    StdOut.println(rq.sample());

    Iterator<String> it = rq.iterator();
    while (it.hasNext()) {
      StdOut.println(it.next());
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void enqueue(T t) {
    validateIncoming(t);

    if (size == capacity) {
      widenStorage();
    }

    items[size++] = t;
  }

  public T dequeue() {
    checkItemsPresence();

    int randomIndex = StdRandom.uniform(size);
    T item = items[randomIndex];
    items[randomIndex] = items[size - 1];
    items[--size] = null;
    return item;
  }

  public T sample() {
    checkItemsPresence();
    return items[StdRandom.uniform(size)];
  }

  public Iterator<T> iterator() {

    return new Iterator<T>() {

      private int next;
      private int copySize = size;
      private T[] copy = (T[]) new Object[copySize];

      {
        System.arraycopy(items, 0, copy, 0, size);
        StdRandom.shuffle(copy);
      }

      @Override
      public boolean hasNext() {
        return next < copy.length;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return copy[next++];
      }
    };
  }

  private void widenStorage() {
    int newCapacity = capacity * 2;
    T[] newItems = (T[]) new Object[newCapacity];
    System.arraycopy(items, 0, newItems, 0, items.length);
    items = newItems;
    capacity = newCapacity;
  }

  private void checkItemsPresence() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
  }

  private void validateIncoming(T i) {
    if (i == null) {
      throw new IllegalArgumentException();
    }
  }
}
