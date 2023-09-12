package functionalenum.example1;

public enum BasicStringOperation implements StringOperation {

    TRIM("Removing leading and trailing spaces.") {
        @Override
        public String apply(String input) {
            return input.trim();
        }
    },

    TO_UPPER("Changing all characters into upper case.") {
        @Override
        public String apply(String input) {
            return input.toUpperCase();
        }
    },

    REVERSE("Reversing the given string.") {
        @Override
        public String apply(String input) {
            return new StringBuilder(input).reverse().toString();
        }
    };

    private final String description;

    BasicStringOperation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
