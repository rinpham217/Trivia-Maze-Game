import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;


/**
 * Implementation of a Graph data type using a HashMap of HashSets
 *
 * @author Ilya Kozorezov
 * @version 1.0
 * @since 1.0
 */

public class Graph implements Serializable {
    private final HashMap<Integer, HashSet<Integer>> myGraph;

    /**
     * Creates a new empty graph
     */
    public Graph() {
        myGraph = new HashMap<>();
    }

    /**
     * Adds a new vertex with a unique identifier as an int
     * without any edges attached
     *
     * @param theNumber an integer as the Vertex identifier
     */
    public void addVertex(final int theNumber) {
        if (myGraph.containsKey(theNumber))
            throw new IllegalArgumentException(theNumber + " is already in graph");
        myGraph.put(theNumber, new HashSet<>());
    }

    /**
     * Create a new undirected edge between 2 vertices, if those
     * vertices do not exist, it creates the required
     * vertices and adds an edge between them
     *
     * @param theStart an integer as the first vertex
     * @param theEnd   an integer as the second vertex
     */
    public void addEdge(final int theStart, final int theEnd) {
        if (!myGraph.containsKey(theStart))
            myGraph.put(theStart, new HashSet<>());

        if (!myGraph.containsKey(theEnd))
            myGraph.put(theEnd, new HashSet<>());

        myGraph.get(theStart).add(theEnd);
        myGraph.get(theEnd).add(theStart);
    }

    /**
     * Deletes an edge between 2 vertices
     *
     * @param theStart an integer as the first vertex
     * @param theEnd   an integer as the second vertex
     * @throws IllegalArgumentException if vertices not in graph
     */
    public void removeEdge(final int theStart, final int theEnd) {
        if (!myGraph.containsKey(theStart))
            throw new IllegalArgumentException(theStart + " is not in the graph");

        if (!myGraph.containsKey(theEnd))
            throw new IllegalArgumentException(theEnd + " is not in the graph");

        myGraph.get(theStart).remove(theEnd);
        myGraph.get(theEnd).remove(theStart);
    }

    public boolean hasEdge(final int theSource, final int theEnd) {
        return myGraph.get(theSource).contains(theEnd);
    }

    /**
     * Runs a depth first search of the graph starting from the param that the user inputs
     * will check after if the whole graph has been iterated through
     *
     * @param theSource an integer as the starting point of the search
     * @param theSize   an integer representing the size of the graph
     * @return a boolean value of whether the whole graph has been iterated through
     * @throws IllegalArgumentException if source or size not valid
     */
    public boolean depthFirstSearch(final int theSource, final int theSize) {
        if (theSize <= 0)
            throw new IllegalArgumentException("Size cannot be less than 0");
        if (!myGraph.containsKey(theSource))
            throw new IllegalArgumentException(theSource + " is not in the graph");
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[theSize];


        stack.add(theSource);
        visited[theSource] = true;

        while (!stack.empty()) {
            int search = stack.pop();
            HashSet<Integer> set = myGraph.get(search);

            int[] temp = set.stream().mapToInt(Integer::intValue).toArray();
            for (int j : temp) {
                if (set.contains(j) && !visited[j]) {
                    stack.add(j);
                    if (j == theSize - 1)
                        return true;
                    visited[j] = true;
                }
            }
        }
        return false;

    }

    /**
     * Make a string of the graph as an array implementation
     *
     * @return a String value of the graph
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i : myGraph.keySet()) {
            builder.append(i).append(": ");
            for (int j : myGraph.get(i))
                builder.append(j).append(" ");
            builder.append("\n");
        }
        return (builder.toString());
    }

}
