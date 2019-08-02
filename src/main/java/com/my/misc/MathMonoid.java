package com.my.misc;

import java.util.function.BiFunction;

public class MathMonoid<T> {

    private BiFunction<T, T, T> associativeFunc;

    private T identityEl;

    public MathMonoid(BiFunction<T, T, T> associativeFunc, T identityEl) {
        this.associativeFunc = associativeFunc;
        this.identityEl = identityEl;
    }

    public static MathMonoid<Integer> INT_SUM = new MathMonoid<>
            (Integer::sum, 0);

    public static MathMonoid<Integer> INT_MAX = new MathMonoid<>
            (Math::max, Integer.MIN_VALUE);

    public static MathMonoid<Integer> INT_MIN = new MathMonoid<>
            (Math::min, Integer.MAX_VALUE);

    public static MathMonoid<Value> INT_MAX_VALUE = new MathMonoid<>
            (Value::max, new Value(Integer.MIN_VALUE, -1));

    public BiFunction<T, T, T> getAssociativeFunc() {
        return associativeFunc;
    }

    public void setAssociativeFunc(BiFunction<T, T, T> associativeFunc) {
        this.associativeFunc = associativeFunc;
    }

    public T getIdentityEl() {
        return identityEl;
    }

    public void setIdentityEl(T identityEl) {
        this.identityEl = identityEl;
    }

    public T apply(T t, T u) {
        return associativeFunc.apply(t, u);
    }

    private static class Value {
        private int value;
        private int index;

        public Value(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public static Value max(Value one, Value two) {
            if (one.value > two.value) {
                return one;
            } else {
                return two;
            }
        }
    }
}
