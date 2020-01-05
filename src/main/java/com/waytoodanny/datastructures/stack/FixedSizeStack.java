package com.waytoodanny.datastructures.stack;

public class FixedSizeStack<T> implements Stack<T> {

  private final Object[] array;
  private int topElementIndex;

  public FixedSizeStack(int size) {
    this.array = new Object[size];
  }

  public void push(T item) {
    if (topElementIndex > array.length - 1) {
      throw new RuntimeException();
    }
    array[topElementIndex++] = item;
  }

  public T pop() {
    if (topElementIndex == 0) {
      throw new RuntimeException();
    }
    Object item = array[--topElementIndex];
    array[topElementIndex] = null;
    return (T) item;
  }
}
