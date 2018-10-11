package pl.ljedrzynski.dsa.queue;

import pl.ljedrzynski.dsa.LinkedList;

public class QueueImplLinkedList<T> implements Queue<T> {
    private LinkedList<T> list = new LinkedList<>();

    @Override
    public void enqueue(T value) {
        list.appendLast(value);
    }

    @Override
    public T dequeue() {
        if (list.getLength() == 0) {
            return null;
        }
        T value = list.getFirst();
        list.removeFirst();
        return value;
    }

    @Override
    public T peek() {
        if (list.getLength() == 0) {
            return null;
        }
        return list.getFirst();
    }
}
