import java.util.HashMap;
import java.util.Map;

/**
 * Enum for all the difficulty types (Easy, Medium, Hard)
 *
 * @author Ilya Kozorezov
 * @version 1.0
 * @since 1.0
 */

public enum Difficulty {
    Test(0),
    Easy(1),
    Medium(2),
    Hard(3);

    private final int value;

    /**
     * Links a value to the enum value
     *
     * @param theValue passed to the enum value
     */

    Difficulty(final int theValue) {
        value = theValue;
    }

    public int getValue() {
        return value;
    }

    /**
     * Creates a map with the int number value for each enum
     */
    private static final Map<Integer, Difficulty> map = new HashMap<>();

    static {
        for (Difficulty difficulty : Difficulty.values()) {
            map.put(difficulty.value, difficulty);
        }
    }

    /**
     * @param theIndex the int value of the enum
     * @return the enum at the int value
     */
    public static Difficulty valueOf(final int theIndex) {
        return map.get(theIndex);
    }
}
