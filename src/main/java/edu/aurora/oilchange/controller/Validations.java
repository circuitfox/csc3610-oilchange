package edu.aurora.oilchange.controller;

/**
 * Utility class for input validations
 */
// so intellij quits whining
@SuppressWarnings("WeakerAccess")
public class Validations {
    private Validations() {}

    public static Validation digits(String input) {
        return new Validation(input, "\\d");
    }

    public static Validation alphanumeric(String input) {
        return new Validation(input, "\\w");
    }

    public static Validation alphabetical(String input) {
        return new Validation(input, "[a-zA-z]");
    }

    /**
     * Combinator for validations. Is given a pattern to match against,
     * stores this pattern as a regex subgroup, and provides methods to expand
     * this pattern into a boolean match.
     */
    static class Validation {
        private String input;
        private String pattern;

        private boolean invert;

        private Validation(String input, String pattern) {
            this(input, pattern, false);
        }

        private Validation(String input, String pattern, boolean invert) {
            this.input = input;
            this.pattern = "(" + pattern + ")";
            this.invert = invert;
        }

        public Validation not() {
            return new Validation(input, pattern, true);
        }

        public boolean one() {
            // ^ = xor (t,t => f, t,f => t, f,t => t, f,f => f)
            return input.matches(pattern) ^ invert;
        }

        public boolean any() {
            pattern = pattern + "*";
            return input.matches(pattern) ^ invert;
        }

        public boolean some() {
            pattern = pattern + "+";
            return input.matches(pattern) ^ invert;
        }

        public boolean repeat(int times) {
            if (times < 1) {
                return false;
            }
            pattern = pattern + "{" + times + "}";
            return input.matches(pattern) ^ invert;
        }

        public boolean range(int from, int to) {
            if (from < 1 || to < from) {
                return false;
            }
            pattern = pattern + "{" + from + "," + to + "}";
            return input.matches(pattern) ^ invert;
        }
    }
}
