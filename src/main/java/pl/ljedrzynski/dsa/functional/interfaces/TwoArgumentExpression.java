package pl.ljedrzynski.dsa.functional.interfaces;

@FunctionalInterface
public interface TwoArgumentExpression<A, B, R> {

    R compute(A lhs, B rhs);
}
