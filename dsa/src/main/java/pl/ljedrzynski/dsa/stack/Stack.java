package pl.ljedrzynski.dsa.stack;

public interface Stack<T> {
    void push(T value);

    T peek();

    T pop();
}
