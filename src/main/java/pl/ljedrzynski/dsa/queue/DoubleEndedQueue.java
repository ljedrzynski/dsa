package pl.ljedrzynski.dsa.queue;

import pl.ljedrzynski.dsa.stack.Stack;

public interface DoubleEndedQueue<T> extends Stack<T> {

    void inject(T value);

    T eject();

    T peekLast();
}
