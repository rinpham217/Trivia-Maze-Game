/**
 * Enum of all possible Directions for the maze (Right,Left,Up,Down)
 *
 * @author Ilya Kozorezov
 * @version 1.0
 * @since 1.0
 */


public enum Directions {
    UP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3);

    /**
     * Gives value to the enum
     */
    private final int myValue;

    Directions(final int theValue) {
        myValue = theValue;
    }

    public int getValue() {
        return myValue;
    }
}
