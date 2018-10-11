package pl.ljedrzynski.dsa.functional;

@FunctionalInterface
public interface OneArgumentExpression<S, R> {

    R compute(S value);
}
