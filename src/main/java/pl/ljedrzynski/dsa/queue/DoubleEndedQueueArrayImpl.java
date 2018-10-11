package pl.ljedrzynski.dsa.queue;

import pl.ljedrzynski.dsa.exceptions.NoSpaceException;

import java.util.Arrays;

public class DoubleEndedQueueArrayImpl<T> extends QueueImplArray<T> implements DoubleEndedQueue<T> {
    public DoubleEndedQueueArrayImpl(int size) {
        super(size);
    }

    @Override
    public void inject(T value) {
        enqueue(value);
    }

    @Override
    public T eject() {
        if (length <= 0) {
            return null;
        }
        end = (end + array.length - 1) % array.length;
        //FIXME?
        T value = array[end];
        length--;
        return value;
    }

    @Override
    public T peekLast() {
        if (length <= 0) {
            return null;
        }
        return array[(end + array.length - 1) % array.length];
    }

    @Override
    public void push(T value) {
        if (length >= array.length) {
            throw new NoSpaceException("No space in the queue");
        }
        start = (start + array.length - 1) % array.length;
        array[start] = value;
        length++;
    }

    public void print() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public T pop() {
        return dequeue();
    }
}
