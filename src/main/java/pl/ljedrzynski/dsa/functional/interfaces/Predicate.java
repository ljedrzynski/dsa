package pl.ljedrzynski.dsa.functional.interfaces;

@FunctionalInterface
public interface Predicate<T> {

    boolean test(T value);
}
