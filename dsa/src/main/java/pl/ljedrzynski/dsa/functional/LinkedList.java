package pl.ljedrzynski.dsa.functional;

import pl.ljedrzynski.dsa.exceptions.NoValueException;
import pl.ljedrzynski.dsa.functional.interfaces.OneArgumentStatement;
import pl.ljedrzynski.dsa.functional.interfaces.TwoArgumentExpression;

public class LinkedList<T> {
    private T head;
    private LinkedList<T> tail;

    private LinkedList() {
    }

    public LinkedList(T head, LinkedList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public T head() {
        return head;
    }

    public LinkedList<T> tail() {
        return tail;
    }

    public LinkedList<T> add(T value) {
        return new LinkedList<>(value, this);
    }

    public void forEach(OneArgumentStatement<T> processor) {
        processor.doSomething(head());
        tail().forEach(processor);
    }

    public <R> LinkedList<R> map(OneArgumentExpression<T, R> transformer) {
        return new LinkedList<>(transformer.compute(head()), tail().map(transformer));
    }

    public <R> LinkedList<R> flatMap(OneArgumentExpression<T, R> transformer) {
        return new LinkedList<>(transformer.compute(head()), tail().map(transformer));
    }

    public <R> R foldLeft(R initialValue, TwoArgumentExpression<R, T, R> computer) {
        R newInitialValue = computer.compute(initialValue, head());
        return tail.foldLeft(newInitialValue, computer);
    }

    public <R> R foldRight(TwoArgumentExpression<T, R, R> computer, R initialValue) {
        R computedValue = tail().foldRight(computer, initialValue);
        return computer.compute(head(), computedValue);
    }

    public LinkedList<T> filter(OneArgumentExpression<T, Boolean> predicate) {
        if (predicate.compute(head())) {
            return new LinkedList<>(head(), tail().filter(predicate));
        } else {
            return tail().filter(predicate);
        }
    }

    public LinkedList<T> append(LinkedList<T> rhs) {
        return this.foldRight((x, l) -> l.add(x), rhs);
    }

    public static <E> LinkedList<E> emptyList() {
        return new EmptyList<>();
    }

    public static class EmptyList<T> extends LinkedList<T> {

        @Override
        public T head() {
            throw new NoValueException("head() invoked on empty list!");
        }

        @Override
        public LinkedList<T> tail() {
            return new EmptyList<>();
        }

        @Override
        public LinkedList<T> add(T value) {
            return super.add(value);
        }

        @Override
        public void forEach(OneArgumentStatement<T> processor) {
        }

        @Override
        public <R> LinkedList<R> map(OneArgumentExpression<T, R> transformer) {
            return LinkedList.emptyList();
        }

        @Override
        public <R> R foldLeft(R initialValue, TwoArgumentExpression<R, T, R> computer) {
            return initialValue;
        }

        @Override
        public <R> R foldRight(TwoArgumentExpression<T, R, R> computer, R initialValue) {
            return initialValue;
        }

        @Override
        public LinkedList<T> filter(OneArgumentExpression<T, Boolean> predicate) {
            return LinkedList.emptyList();
        }

        @Override
        public <R> LinkedList<R> flatMap(OneArgumentExpression<T, R> transformer) {
            return new LinkedList<>(transformer.compute(head()), tail().map(transformer));
        }

    }

    private static LinkedList<Integer> ofRange(int start, int end,
                                               LinkedList<Integer> tailList) {
        if (start >= end) {
            return tailList;
        } else {
            return ofRange(start + 1, end, tailList).add(start);
        }
    }
}
