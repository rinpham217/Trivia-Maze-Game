import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Manage the state of the game by using serialization
 *
 * @author Ilya Kozorezov
 * @version 1.0
 * @since 1.0
 */

public class GameState {

    /**
     * Saves the current state of the game using Serialization
     */
    public static void saveGame() {
        try {
            FileOutputStream file = new FileOutputStream("savedGame");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(Player.getPlayer());

            out.close();
            file.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * Loads the game you have on file through Deserialization
     */
    public static void loadGame() {
        try {
            FileInputStream file = new FileInputStream("savedGame");
            ObjectInputStream in = new ObjectInputStream(file);

            Player.loadPlayer(in.readObject());

            in.close();
            file.close();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes old game and creates a new one based on the parameters
     *
     * @param theName       The name of the player
     * @param theDifficulty the Difficulty of the game
     */
    public static void newGame(final String theName, final Difficulty theDifficulty) {
        Player.deletePlayer();
        Player.createPlayer(theName, theDifficulty);
    }

    /**
     * Deletes the current player
     */
    public static void endGame() {
        Player.deletePlayer();
    }

}
