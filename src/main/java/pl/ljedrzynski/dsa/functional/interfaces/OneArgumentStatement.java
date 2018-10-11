package pl.ljedrzynski.dsa.functional.interfaces;

@FunctionalInterface
public interface OneArgumentStatement<T> {

    void doSomething(T argument);
}
