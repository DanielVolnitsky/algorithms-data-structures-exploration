package com.waytoodanny.coursera.week2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {

  private Node<T> head;
  private Node<T> tail;
  private int size;

  public Deque() {

  }

  public static void main(String[] args) {
    Deque<String> d = new Deque<>();

    StdOut.println(d.isEmpty());
    StdOut.println(d.size());

    d.addFirst("a");
    d.addLast("b");
    d.addLast("c");
    d.addFirst("d");

    StdOut.println(d.removeFirst());
    StdOut.println(d.removeLast());

    Iterator<String> it = d.iterator();
    while (it.hasNext()) {
      StdOut.println(it.next());
    }
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
