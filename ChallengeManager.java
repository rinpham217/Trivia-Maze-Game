import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * Manages the challenges that the user can do by writing python code
 *
 * @author Ilya Kozorezov
 * @version 1.0
 * @since 1.0
 */
public class ChallengeManager {
    private boolean myPassedChallenge;

    private String myName;
    private String myAnswer;
    private String myChallenge;
    private String myParams;

    /**
     * Constructor, sets the next challenge
     */
    public ChallengeManager() {
        nextChallenge();
    }

    /**
     * Pull a new random challenge from the database
     */
    public void nextChallenge() {
        myPassedChallenge = false;
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:sqlite:myquestions.db");
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM challenges ORDER BY RANDOM() LIMIT 1");
            myName = rs.getString("Name");
            myAnswer = rs.getString("Answer");
            myChallenge = rs.getString("Challenge");
            myParams = rs.getString("Params");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * Run the python script in the python file that the player wrote in
     *
     * @return if the results of the function the user ran is correct
     */
    public boolean runScript() {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "python_challenges/CheckChallenge.py", myName);
            Process p = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String result = in.readLine();
            myPassedChallenge = Objects.equals(result, myAnswer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPassedChallenge;
    }

    /**
     * @return the current challenge the user is on as a String array
     */
    public String[] getChallenge() {
        return new String[]{myName, myAnswer, myChallenge, myParams};
    }

}
