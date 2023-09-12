package functionalenum.example2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Application {

    private Application() { }

    private static final Map<ImmutableOperation, Operator> OPERATION_MAP = new HashMap<>();

    static {
        OPERATION_MAP.put(ImmutableOperation.TO_LOWER, String::toLowerCase);
        OPERATION_MAP.put(ImmutableOperation.INVERT_CASE, input -> new StringBuilder(input).reverse().toString());
        OPERATION_MAP.put(ImmutableOperation.REMOVE_WHITESPACES, input -> input.replaceAll("\\s", ""));
    }

    public static final BiFunction<ImmutableOperation, String, String> immutableOperation =
            (operation, input) -> OPERATION_MAP.get(operation).apply(input);
}
