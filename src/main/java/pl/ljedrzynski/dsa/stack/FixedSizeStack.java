package pl.ljedrzynski.dsa.stack;

import pl.ljedrzynski.dsa.exceptions.NoSpaceException;

public class FixedSizeStack<T> implements Stack<T> {
    private int top = -1;
     private T[] array;

    public FixedSizeStack(int size) {
        array = (T[]) new Object[size];
    }

    @Override
    public void push(T value) {
        if (top == array.length - 1) {
            throw new NoSpaceException("");
        }
        top++;
        array[top] = value;
    }

    @Override
    public T peek() {
        if (top == -1) {
            return null;
        }
        return array[top];
    }

    @Override
    public T pop() {
        if (top == -1) {
            return null;
        }
        top--;
        return array[top + 1];
    }
}
