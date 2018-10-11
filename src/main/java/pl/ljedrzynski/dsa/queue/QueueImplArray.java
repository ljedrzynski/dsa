package pl.ljedrzynski.dsa.queue;

import pl.ljedrzynski.dsa.exceptions.NoSpaceException;

public class QueueImplArray<T> implements Queue<T> {
    protected T[] array;
    protected int start = 0;
    protected int end = 0;
    protected int length = 0;

    public QueueImplArray(int size) {
        array = (T[]) new Object[size];
    }

    @Override
    public void enqueue(T value) {
        if (length >= array.length) {
            throw new NoSpaceException("");
        }
        array[end] = value;
        end = (end + 1) % array.length;
        length++;
    }

    @Override
    public T dequeue() {
        if (length <= 0) {
            return null;
        }
        T value = array[start];
        start = (start + 1) % array.length;
        length--;
        return value;
    }

    @Override
    public T peek() {
        if (length <= 0) {
            return null;
        }
        return array[start];
    }

    public static void main(String[] args) {
        Queue<String> stackOfString = new QueueImplArray<String>(10);
        stackOfString.enqueue("pierszy");
        stackOfString.enqueue("drugi");
        stackOfString.enqueue("trzeci");
        stackOfString.enqueue("czwarty");
        stackOfString.enqueue("pionty");
        stackOfString.enqueue("czusty");
        stackOfString.enqueue("siudmy");
        stackOfString.enqueue("osmy");
        stackOfString.enqueue("dziewionty");
        stackOfString.enqueue("dziesiontyn");
        System.out.print(stackOfString.peek());
        System.out.print(stackOfString.dequeue());
        System.out.print(stackOfString.dequeue());
        System.out.print(stackOfString.dequeue());
        System.out.print(stackOfString.peek());
        System.out.print(stackOfString.dequeue());
        stackOfString.enqueue("jedenastyn");
    }
}
