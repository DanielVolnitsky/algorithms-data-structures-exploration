package com.waytoodanny.datastructures.application.deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Throw an IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
 * Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty.
 * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
 * Throw an UnsupportedOperationException if the client calls the remove() method in the iterator.
 * <p>
 * Performance requirements.
 * Your deque implementation must support each deque operation (including construction)
 * in constant worst-case time. A deque containing n items must use at most 48n + 192 bytes of memory.
 * Additionally, your iterator implementation must support each operation (including construction)
 * in constant worst-case time.
 */
public class Deque<T> implements Iterable<T> {

  private Node<T> head;
  private Node<T> tail;
  private int size;

  public Deque() {

  }

  /**
   * Your main() method must call directly every public constructor and
   * method to help verify that they work as prescribed (e.g., by printing results to standard output).
   */
  public static void main(String[] args) {

  }

  public boolean isEmpty() {
    return head == null;
  }

  public int size() {
    return size;
  }

  public void addFirst(T t) {
    validateIncoming(t);
    if (size == 0) {
      initializeHeadAndTail(t);
    } else {
      Node<T> newHead = new Node<>(t);
      head.next = newHead;
      newHead.prev = head;
      head = newHead;
    }
    size++;
  }

  public void addLast(T t) {
    validateIncoming(t);

    if (size == 0) {
      initializeHeadAndTail(t);
    } else {
      Node<T> newTail = new Node<>(t);
      tail.prev = newTail;
      newTail.next = tail;
      tail = newTail;
    }
    size++;
  }

  public T removeFirst() {
    if (size == 0) {
      throw new NoSuchElementException();
    }

    T item = head.item;
    if (head == tail) {
      head = null;
      tail = null;
    } else {
      head.prev.next = null;
      head = head.prev;
    }
    size--;
    return item;
  }

  public T removeLast() {
    checkRemovalPossibility();

    T item = tail.item;
    if (head == tail) {
      head = null;
      tail = null;
    } else {
      tail.next.prev = null;
      tail = tail.next;
    }
    size--;
    return item;
  }

  public Iterator<T> iterator() {
    return new Iterator<T>() {

      private Node<T> next = tail;

      @Override
      public boolean hasNext() {
        return next != null;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }

        T i = next.item;
        next = next.next;
        return i;
      }
    };
  }

  private void initializeHeadAndTail(T t) {
    tail = new Node<>(t);
    head = tail;
  }


  private void checkRemovalPossibility() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
  }

  private void validateIncoming(T i) {
    if (i == null) {
      throw new IllegalArgumentException();
    }
  }

  private static class Node<T> {
    private T item;
    private Node<T> prev;
    private Node<T> next;

    private Node(T item) {
      this.item = item;
    }
  }
}
