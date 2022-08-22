import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for Trivia Maze Game
 *
 * @author Ilya Kozorezov
 * @author Rin Pham
 * @version 1.0
 * @see Maze
 * @see Graph
 * @since 1.0
 */

class TestClass {

    /**
     * Add a new vertex on empty graph
     *
     * @result Graph will have a new vertex and only 1 vertex
     */
    @Test
    void testAddVertex_on_BlankGraph() {
        Graph graph = new Graph();
        graph.addVertex(1);
        assertEquals(graph.toString(), "1: \n");
    }

    /**
     * Add a new vertex to an existing graph
     *
     * @result Graph will have 2 vertices that are unlinked
     */
    @Test
    void testAddVertex_on_UsedGraph() {
        Graph graph = new Graph();
        graph.addVertex(1);
        graph.addVertex(2);
        assertEquals(graph.toString(), """
                1:\s
                2:\s
                """);
    }

    /**
     * Attempt to create a new vertex on an existing vertex
     *
     * @result throws IllegalArgumentException because the vertex already exists
     */
    @Test
    void testAddVertex_duplicateVertexes() {
        Graph graph = new Graph();
        graph.addVertex(1);
        assertThrows(IllegalArgumentException.class, () -> graph.addVertex(1));
    }

    /**
     * Create a new edge between 2 nonexistent vertices
     *
     * @result Creates 2 new vertices and links them with an edge
     */
    @Test
    void testAddEdge_on_BlankGraph() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        assertEquals(graph.toString(), """
                1: 2\s
                2: 1\s
                """);
    }

    /**
     * Create a new edge between 2 existent vertices
     *
     * @result Creates a new edge between 2 existent vertices
     */
    @Test
    void testAddEdge_on_UsedGraph() {
        Graph graph = new Graph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        assertEquals(graph.toString(), """
                1: 2\s
                2: 1\s
                """);
    }

    /**
     * Try to duplicate an existing edge
     *
     * @result Nothing should change
     */
    @Test
    void testAddEdge_duplicateEdges() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 2);
        assertEquals(graph.toString(), """
                1: 2\s
                2: 1\s
                """);
    }

    /**
     * Remove the edge between 2 vertices
     *
     * @result removes the edge between 2 vertices
     */
    @Test
    void testRemoveEdge() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.removeEdge(1, 2);
        assertEquals(graph.toString(), """
                1:\s
                2:\s
                """);
    }

    /**
     * Attempt to remove an edge that does not exist
     *
     * @result throws IllegalArgumentException because edge does not exist
     */
    @Test
    void testRemoveEdge_thatDoesNotExist() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        assertThrows(IllegalArgumentException.class, () -> graph.removeEdge(3, 2));
    }

    /**
     * Iterate through a connected graph using a depth first search
     *
     * @result true because the whole graph is iterated through
     */
    @Test
    void testDepthFirstSearch_onConnectedGraph() {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        assertTrue(graph.depthFirstSearch(0, 4));
    }

    /**
     * Iterated through an unconnected graph using depth first search
     *
     * @result false because the graph is unconnected
     */
    @Test
    void testDepthFirstSearch_onUnconnectedGraph() {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        assertFalse(graph.depthFirstSearch(0, 4));
    }

    /**
     * Attempt to iterate through a graph with input of size being invalid
     *
     * @result throws IllegalArgumentException because size is not valid
     */
    @Test
    void testDepthFirstSearch_IllegalSize() {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        assertThrows(IllegalArgumentException.class, () -> graph.depthFirstSearch(0, -1));
    }

    /**
     * Attempt to iterate through a graph starting from a source not in the graph
     *
     * @result throws IllegalArgumentException because the source is not in the graph
     */
    @Test
    void testDepthFirstSearch_IllegalSource() {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        assertThrows(IllegalArgumentException.class, () -> graph.depthFirstSearch(2, 2));
    }

    /**
     * See if vertex 0 has an edge to vertex 1
     *
     * @result true
     */

    @Test
    void testHasEdge_HasEdge() {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        assertTrue(graph.hasEdge(0, 1));
    }

    /**
     * See if vertex 0 has an edge to vertex 1
     *
     * @result false
     */

    @Test
    void testHasEdge_NoEdge() {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        assertFalse(graph.hasEdge(0, 2));
    }

    /**
     * Create a new Maze with size 16
     *
     * @result Creates a new maze with the size 16
     */
    @Test
    void testCreate4x4_Maze() {
        Maze maze = new Maze(16);
        assertEquals(maze.toString(), """
                0: 1 4\s
                1: 0 2 5\s
                2: 1 3 6\s
                3: 2 7\s
                4: 0 5 8\s
                5: 1 4 6 9\s
                6: 2 5 7 10\s
                7: 3 6 11\s
                8: 4 9 12\s
                9: 5 8 10 13\s
                10: 6 9 11 14\s
                11: 7 10 15\s
                12: 8 13\s
                13: 9 12 14\s
                14: 10 13 15\s
                15: 11 14\s
                """);
    }

    /**
     * Create a new Maze with size 25
     *
     * @result Creates a new maze with the size 25
     */
    @Test
    void testCreate5x5_Maze() {
        Maze maze = new Maze(25);
        assertEquals(maze.toString(), """
                0: 1 5\s
                1: 0 2 6\s
                2: 1 3 7\s
                3: 2 4 8\s
                4: 3 9\s
                5: 0 6 10\s
                6: 1 5 7 11\s
                7: 2 6 8 12\s
                8: 3 7 9 13\s
                9: 4 8 14\s
                10: 5 11 15\s
                11: 16 6 10 12\s
                12: 17 7 11 13\s
                13: 18 8 12 14\s
                14: 19 9 13\s
                15: 16 20 10\s
                16: 17 21 11 15\s
                17: 16 18 22 12\s
                18: 17 19 23 13\s
                19: 18 24 14\s
                20: 21 15\s
                21: 16 20 22\s
                22: 17 21 23\s
                23: 18 22 24\s
                24: 19 23\s
                """);
    }

    /**
     * Create a new Maze with size 36
     *
     * @result Creates a new maze with the size 36
     */
    @Test
    void testCreate6x6_Maze() {
        Maze maze = new Maze(36);
        assertEquals(maze.toString(), """
                0: 1 6\s
                1: 0 2 7\s
                2: 1 3 8\s
                3: 2 4 9\s
                4: 3 5 10\s
                5: 4 11\s
                6: 0 7 12\s
                7: 1 6 8 13\s
                8: 2 7 9 14\s
                9: 3 8 10 15\s
                10: 16 4 9 11\s
                11: 17 5 10\s
                12: 18 6 13\s
                13: 19 7 12 14\s
                14: 20 8 13 15\s
                15: 16 21 9 14\s
                16: 17 22 10 15\s
                17: 16 23 11\s
                18: 19 24 12\s
                19: 18 20 25 13\s
                20: 19 21 26 14\s
                21: 20 22 27 15\s
                22: 16 21 23 28\s
                23: 17 22 29\s
                24: 18 25 30\s
                25: 19 24 26 31\s
                26: 32 20 25 27\s
                27: 33 21 26 28\s
                28: 34 22 27 29\s
                29: 35 23 28\s
                30: 24 31\s
                31: 32 25 30\s
                32: 33 26 31\s
                33: 32 34 27\s
                34: 33 35 28\s
                35: 34 29\s
                """);
    }

    /**
     * Attempt to create a maze with size that is less than 8
     *
     * @result throws IllegalArgumentException because size is invalid
     */
    @Test
    void testCreateMaze_IllegalSmallSize() {
        assertThrows(IllegalArgumentException.class, () -> new Maze(4));
    }

    /**
     * Attempt to create a maze with a size that is not a perfect square
     *
     * @result throws IllegalArgumentException because size is invalid
     */
    @Test
    void testCreateMaze_IllegalNotSquare() {
        assertThrows(IllegalArgumentException.class, () -> new Maze(10));
    }

    /**
     * Close a door between 2 rooms on the maze
     *
     * @result Have the door be closed between the 2 rooms
     */
    @Test
    void testCloseDoor() {
        Maze maze = new Maze(9);
        maze.closeDoor(1, 2);
        assertEquals(maze.toString(), """
                0: 1 3\s
                1: 0 4\s
                2: 5\s
                3: 0 4 6\s
                4: 1 3 5 7\s
                5: 2 4 8\s
                6: 3 7\s
                7: 4 6 8\s
                8: 5 7\s
                """);
    }

    /**
     * Close a door between 2 rooms that is already closed
     *
     * @result Nothing since the door is already closed
     */
    @Test
    void testCloseDoor_AlreadyClosed() {
        Maze maze = new Maze(9);
        maze.closeDoor(1, 2);
        maze.closeDoor(1, 2);
        assertEquals(maze.toString(), """
                0: 1 3\s
                1: 0 4\s
                2: 5\s
                3: 0 4 6\s
                4: 1 3 5 7\s
                5: 2 4 8\s
                6: 3 7\s
                7: 4 6 8\s
                8: 5 7\s
                """);
    }

    /**
     * Close a door but the maze still can be finished
     *
     * @result true because the maze is still possible to complete
     */
    @Test
    void testCloseDoor_mazeStillConnected() {
        Maze maze = new Maze(9);
        assertTrue(maze.closeDoor(0, 1));
    }

    /**
     * Close doors so that the maze is not possible to finish
     *
     * @result false because the maze is not possible to complete
     */
    @Test
    void testCloseDoor_mazeNowUnconnected() {
        Maze maze = new Maze(9);
        maze.closeDoor(0, 3);
        assertFalse(maze.closeDoor(0, 1));
    }

    /**
     * Close a door that does not exist
     *
     * @result Nothing because the door does not exist
     */
    @Test
    void testCloseDoor_nonExistingDoor() {
        Maze maze = new Maze(9);
        assertTrue(maze.closeDoor(0, 8));
        assertEquals(maze.toString(), """
                0: 1 3\s
                1: 0 2 4\s
                2: 1 5\s
                3: 0 4 6\s
                4: 1 3 5 7\s
                5: 2 4 8\s
                6: 3 7\s
                7: 4 6 8\s
                8: 5 7\s
                """);

    }

    @Test
    void getLevel() {
        Player.createPlayer("Player1", Difficulty.Hard);
        assertEquals(Player.getPlayer().getLevel(), Difficulty.Hard);
        Player.deletePlayer();
    }

    @Test
    void getName() {
        Player.createPlayer("Player2", Difficulty.Hard);
        assertEquals("Player2", Player.getPlayer().getName());
        Player.deletePlayer();
    }

    @Test
    void getPlayerPosition() {
        Player.createPlayer("player3", Difficulty.Hard);
        assertEquals(0, Player.getPlayer().getPlayerPosition());
        Player.deletePlayer();
    }

    /**
     * Move Player.getPlayer() right 1 position
     *
     * @result Player.getPlayer() moves from room 0 to 1
     */
    @Test
    void testMovePlayer_Right_valid() {
        Player.createPlayer("Bill", Difficulty.Test);
        Player.getPlayer().movePlayer(Directions.RIGHT);
        assertEquals(Player.getPlayer().getPlayerPosition(), 1);
        Player.deletePlayer();
    }

    /**
     * Move Player.getPlayer() right 1 position when there is no position on the right
     *
     * @result Player.getPlayer() stays in the same room
     */
    @Test
    void testMovePlayer_Right_invalid() {
        Player.createPlayer("Bill", Difficulty.Test);
        Player.getPlayer().setPlayerPosition(2);
        Player.getPlayer().movePlayer(Directions.RIGHT);
        assertEquals(Player.getPlayer().getPlayerPosition(), 2);
        Player.deletePlayer();
    }

    /**
     * Move Player.getPlayer() left 1 position
     *
     * @result Player.getPlayer() moves from room 1 to room 0
     */
    @Test
    void testMovePlayer_Left_valid() {
        Player.createPlayer("Bill", Difficulty.Test);
        Player.getPlayer().setPlayerPosition(1);
        Player.getPlayer().movePlayer(Directions.LEFT);
        assertEquals(Player.getPlayer().getPlayerPosition(), 0);
        Player.deletePlayer();
    }

    /**
     * Move Player.getPlayer() left 1 position when there is no position on the right
     *
     * @result Player.getPlayer() stays in the same room
     */
    @Test
    void testMovePlayer_Left_invalid() {
        Player.createPlayer("Bill", Difficulty.Test);
        Player.getPlayer().movePlayer(Directions.LEFT);
        assertEquals(Player.getPlayer().getPlayerPosition(), 0);
        Player.deletePlayer();
    }

    /**
     * Move Player.getPlayer() up 1 position
     *
     * @result Player.getPlayer() moves from room 3 to room 0
     */
    @Test
    void testMovePlayer_Up_valid() {
        Player.createPlayer("Bill", Difficulty.Test);
        Player.getPlayer().setPlayerPosition(3);
        Player.getPlayer().movePlayer(Directions.UP);
        assertEquals(Player.getPlayer().getPlayerPosition(), 0);
        Player.deletePlayer();
    }

    /**
     * Move Player.getPlayer() up 1 position when there is no position on the right
     *
     * @result Player.getPlayer() stays in the same room
     */
    @Test
    void testMovePlayer_Up_invalid() {
        Player.createPlayer("Bill", Difficulty.Test);
        Player.getPlayer().movePlayer(Directions.UP);
        assertEquals(Player.getPlayer().getPlayerPosition(), 0);
        Player.deletePlayer();

    }

    /**
     * Move Player.getPlayer() down 1 position
     *
     * @result Player.getPlayer() moves from room 0 to 3
     */
    @Test
    void testMovePlayer_Down_valid() {
        Player.createPlayer("Bill", Difficulty.Test);
        Player.getPlayer().movePlayer(Directions.DOWN);
        assertEquals(Player.getPlayer().getPlayerPosition(), 3);
        Player.deletePlayer();
    }

    /**
     * Move Player.getPlayer() right 1 position when there is no position on the right
     *
     * @result Player.getPlayer() stays in the same room
     */
    @Test
    void testMovePlayer_Down_invalid() {
        Player.createPlayer("Bill", Difficulty.Test);
        Player.getPlayer().setPlayerPosition(6);
        Player.getPlayer().movePlayer(Directions.DOWN);
        assertEquals(Player.getPlayer().getPlayerPosition(), 6);
        Player.deletePlayer();
    }

    /**
     * Create an instance of the player
     *
     * @result creates a new player
     */
    @Test
    void testPlayer_createPlayer() {
        Player.createPlayer("Bill", Difficulty.Test);
        assertEquals(Player.getPlayer().getLevel(), Difficulty.Test);
        Player.deletePlayer();
    }

    /**
     * Create an instance of player and get it
     *
     * @result get the instance of the player
     */
    @Test
    void testPlayer_getPlayer() {
        Player.createPlayer("Bill", Difficulty.Test);
        assertEquals(Player.getPlayer().getLevel(), Difficulty.Test);
        Player.deletePlayer();
    }

    /**
     * Delete the instance of the player
     *
     * @result player will be null
     */
    @Test
    void testPlayer_deletePlayer() {
        Player.createPlayer("Bill", Difficulty.Test);
        Player.deletePlayer();
        assertNull(Player.getPlayer());
    }

    /**
     * Creates a new game with a clean player
     */
    @Test
    void testNewGame() {
        Player.createPlayer("Bill", Difficulty.Test);
        Player.getPlayer().setPlayerPosition(4);
        GameState.saveGame();
        GameState.newGame("Bill", Difficulty.Easy);
        assertEquals(Player.getPlayer().getLevel(), Difficulty.Easy);
        Player.deletePlayer();
    }

    /**
     * Save and load the game
     *
     * @result saves and loads the player
     */
    @Test
    void testSave_and_LoadGame() {
        Player.createPlayer("Bill", Difficulty.Test);
        Player.getPlayer().setPlayerPosition(4);
        GameState.saveGame();
        GameState.endGame();
        assertNull(Player.getPlayer());
        GameState.loadGame();
        assertEquals(Player.getPlayer().getLevel(), Difficulty.Test);
        Player.deletePlayer();

    }


}