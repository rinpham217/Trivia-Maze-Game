import java.io.Serializable;

/**
 * Represents the maze that the user will be able to play through
 * implemented using the graph class
 *
 * @author Ilya Kozorezov
 * @version 1.0
 * @see Graph
 * @since 1.0
 */

public class Maze implements Serializable {

    private final int SIZE;
    private final Graph myMaze;

    /**
     * Creates a new maze with all required rooms linked based on size
     *
     * @param theSize an integer as the size of the maze.
     *                Must be greater than 8 and a perfect square
     * @throws IllegalArgumentException If size is not valid
     */
    public Maze(final int theSize) throws IllegalArgumentException {
        if (theSize < 9 || (int) Math.sqrt(theSize) != Math.sqrt(theSize)) {
            throw new IllegalArgumentException("Please have the size be bigger than 8 and a perfect square \n" +
                    "(example inputs: 9, 16, 25, 36, ...)");
        }
        SIZE = theSize;
        myMaze = new Graph();
        createMaze(theSize);
    }

    /**
     * Creates the maze with all required rooms linked
     *
     * @param theSize as the size of the maze
     */
    private void createMaze(final int theSize) {
        int base = (int) Math.sqrt(theSize);

        for (int i = 0; i < theSize; i++) {
            if (i + base < theSize) myMaze.addEdge(i, i + base);
            if (i - base >= 0) myMaze.addEdge(i, i - base);
            for (int j = i + 1; j < base + i - 1; j++) {
                if (j - base >= 0) myMaze.addEdge(j, j - base);
                if (j - 1 >= 0) myMaze.addEdge(j, j - 1);
                if (j + 1 < theSize) myMaze.addEdge(j, j + 1);
                if (j + base < theSize) myMaze.addEdge(j, j + base);
            }
            i += base - 1;
            if (i + base < theSize) myMaze.addEdge(i, i + base);
            if (i - base >= 0) myMaze.addEdge(i, i - base);


        }
    }

    /**
     * unlinks 2 rooms of the maze
     *
     * @param theFrom  an integer as the starting room of the door that should be closed
     * @param theWhere an integer as the ending room of the door that should be closed
     * @return a boolean if there is a way to get from the current door to the finish
     */
    public boolean closeDoor(final int theFrom, final int theWhere) {
        myMaze.removeEdge(theFrom, theWhere);
        return myMaze.depthFirstSearch(theFrom, SIZE);
    }

    /**
     * see if a room has a connection to another room
     *
     * @param theSource an integer as the first room
     * @param theWhere  an integer as the second room
     * @return a bool if the first and second room are connected
     */
    public boolean canGoto(final int theSource, final int theWhere) {
        return myMaze.hasEdge(theSource, theWhere);
    }

    /**
     * Creates a string of the maze as an array implementation of the graph
     *
     * @return String as the maze
     */
    @Override
    public String toString() {
        return myMaze.toString();
    }
}
