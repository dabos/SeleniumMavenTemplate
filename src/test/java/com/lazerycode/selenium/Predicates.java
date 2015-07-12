package com.lazerycode.selenium;

import com.google.common.base.Predicate;

import static com.google.common.base.Predicates.and;

/**
 * @author alexandrubostan
 * @since 10.07.2015
 */
public class Predicates {
    private Predicates() {
    }

    public static Predicate<String> contains(final String text) {
        return new ContainsPredicate(text);
    }

    public static <T> Predicate<T> all(Predicate<T>... predicates) {
        return new AllPredicate<T>(predicates);
    }

    public static <T> Predicate<T> is(final Predicate<T> predicate) {
        return new Predicate<T>() {
            @Override
            public boolean apply(T input) {
                return predicate.apply(input);
            }

            @Override
            public String toString() {
                return " is " + predicate;
            }
        };
    }

    private static class ContainsPredicate implements Predicate<String> {
        private CharSequence text;

        private ContainsPredicate(CharSequence text) {
            this.text = text;
        }

        @Override
        public boolean apply(String input) {
            return input.contains(text);
        }

        @Override
        public String toString() {
            return "contains '" + text + "'";
        }
    }

    private static class AllPredicate<T> implements Predicate<T> {
        private Predicate<T> composedPredicate;

        public AllPredicate(Predicate<T>... predicates) {
            composedPredicate = predicates[0];
            for (int i = 1; i < predicates.length; i++) {
                composedPredicate = new AndPredicate<T>(composedPredicate, predicates[i]);
            }
        }

        @Override
        public boolean apply(T input) {
            return composedPredicate.apply(input);
        }

        @Override
        public String toString() {
            return composedPredicate.toString();
        }
    }

    //Has a better description than the one from Google
    private static class AndPredicate<T> implements Predicate<T> {
        private Predicate<T> predicate1;
        private Predicate<T> predicate2;

        private AndPredicate(Predicate<T> predicate1, Predicate<T> predicate2) {
            this.predicate1 = predicate1;
            this.predicate2 = predicate2;
        }

        @Override
        public boolean apply(T input) {
            return and(predicate1, predicate2).apply(input);
        }

        @Override
        public String toString() {
            return predicate1.toString() + " and " + predicate2.toString();
        }
    }
}
