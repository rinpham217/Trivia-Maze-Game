import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;

/**
 * Main Menu Screen
 *
 * @author Ilya Kozorezov
 * @author Rin Pham
 * @version 1.0
 * @since 1.0
 */

public class MainScreen extends JPanel {
    private final int myMazeSize;

    private RoomPanel myRoomPanel;

    private static JLabel myStreakLabel;
    private static final int STREAK_COMPLETE = 5;
    private JButton myChallengeButton;

    private static JLabel myScoreLabel;

    private static final JButton[] myAnswerButtons = new JButton[4];
    private static final JLabel[] myOptions = new JLabel[4];
    private static Question myCurrentQuestion;

    private static final JButton[] myMoveButtons = new JButton[4];
    private static final Directions[] myDirections = new Directions[]{Directions.RIGHT, Directions.LEFT, Directions.UP, Directions.DOWN};
    private static Directions myAttemptMove;

    private static boolean myAnsweredQuestion = true;

    private final int WINNER_POS;

    private static JTextArea questionText;

    /**
     * Constructor
     *
     * @param theSize the size of the maze
     */
    public MainScreen(final int theSize) {
        super();
        myMazeSize = theSize;
        createState();

        WINNER_POS = (theSize + 3) * (theSize + 3) - 1;
        this.setLayout(null);

    }


    /**
     * put new question on the GUI
     */
    private void updateQuestion() {
        if (!myAnsweredQuestion)
            return;

        myCurrentQuestion = Controller.getQuestion();
        questionText.setText(myCurrentQuestion.getMyQuestion());
        for (int i = 0; i < 4; i++) {
            myOptions[i].setText(myCurrentQuestion.getMyOptions()[i]);
            myOptions[i].setForeground(Color.yellow);
            if (Controller.cheatsAllowed() && myCurrentQuestion.getMyAnswer() == i)
                myOptions[i].setForeground(Color.green);

            myAnswerButtons[i].setVisible(myCurrentQuestion.getMyOptions()[i] != null && !myCurrentQuestion.getMyOptions()[i].equals(""));
        }
        myAnsweredQuestion = false;
    }

    /**
     * Remove question from GUI
     */
    private void clearQuestion() {
        questionText.setText("");
        for (int i = 0; i < 4; i++) {
            myOptions[i].setText("");
            myMoveButtons[i].setVisible(Controller.canPlayerGo(myDirections[i]));
        }
        myAnsweredQuestion = true;
    }

    /**
     * Only allow movement where player can move on maze
     */
    private void limitMovement() {
        for (int i = 0; i < 4; i++) {
            myMoveButtons[i].setVisible(Controller.canPlayerGo(myDirections[i]));
        }
    }

    /**
     * See if game was won
     *
     * @return a boolean if the game has been won
     */
    private boolean isGameWon() {
        if (Controller.getPlayerPos() != WINNER_POS)
            return false;

        JOptionPane.showMessageDialog(this, "You have won Python Trivia Maze with a score of " + Controller.getScore() + "!", "WINNER", JOptionPane.PLAIN_MESSAGE);
        Controller.quitGame();
        MainFrame.goTo(new MainMenu());
        return true;
    }

    /**
     * See if answer is correct
     *
     * @param theChoice as the users choice
     */
    private void checkAnswer(final int theChoice) {
        clearQuestion();
        if (myCurrentQuestion.getMyAnswer() == theChoice) {
            myRoomPanel.updatePlayerPos(myAttemptMove);
            myScoreLabel.setText("Score: " + Controller.updateScore(100));
            myStreakLabel.setText("Streak: " + Controller.updateStreak());
            if (Controller.getStreak() >= STREAK_COMPLETE) {
                myChallengeButton.setVisible(true);
            }
            if (isGameWon())
                return;
        } else {
            if (isGameLost())
                return;
            myRoomPanel.lockRoom(myAttemptMove);
            myScoreLabel.setText("Score: " + Controller.updateScore(-100));
            myStreakLabel.setText("Streak: " + Controller.resetStreak());
        }
        allowPress(myAnswerButtons, false);
        allowPress(myMoveButtons, true);
        limitMovement();

    }

    /**
     * See if game is lost
     *
     * @return a boolean if the game has been lost
     */
    private boolean isGameLost() {
        if (!Controller.answeredWrong(myAttemptMove)) {
            JOptionPane.showMessageDialog(this, "You Have Lost!\n You will be directed to the main menu", "Game Lost", JOptionPane.WARNING_MESSAGE);
            Controller.quitGame();
            MainFrame.goTo(new MainMenu());
            return true;
        }
        return false;
    }

    /**
     * Move player
     *
     * @param theDir the direction to move the player
     */
    private void movePlayer(final Directions theDir) {

        if (Controller.isRoomUnlocked(theDir)) {
            myRoomPanel.updatePlayerPos((theDir));
            limitMovement();
        } else {
            allowPress(myMoveButtons, false);
            allowPress(myAnswerButtons, true);
            updateQuestion();
            myAttemptMove = theDir;
        }


    }

    /**
     * Allow buttons to be pressed
     *
     * @param theButtons the buttons to be allowed or denied
     * @param areEnabled if the buttons should be enabled or not
     */
    public void allowPress(final JButton[] theButtons, final boolean areEnabled) {
        for (JButton button : theButtons) {
            button.setEnabled(areEnabled);
        }
    }

    /**
     * Create the GUI
     */
    private void createState() {
        myRoomPanel = new RoomPanel(myMazeSize);
        myRoomPanel.setBounds(10, 10, 400, 400);
        this.add(myRoomPanel);

        myMoveButtons[0] = Components.createNewButton("Right", 420, 100, e -> movePlayer(Directions.RIGHT));
        this.add(myMoveButtons[0]);

        myMoveButtons[1] = Components.createNewButton("Left", 420, 150, e -> movePlayer(Directions.LEFT));
        this.add(myMoveButtons[1]);

        myMoveButtons[2] = Components.createNewButton("Up", 420, 200, e -> movePlayer(Directions.UP));
        this.add(myMoveButtons[2]);

        myMoveButtons[3] = Components.createNewButton("Down", 420, 250, e -> movePlayer(Directions.DOWN));
        this.add(myMoveButtons[3]);

        myChallengeButton = Components.createNewButton("Challenge", 420, 300, e -> {
            new PythonChallengeFrame();
            myChallengeButton.setVisible(false);
            myStreakLabel.setText("Streak: " + Controller.resetStreak());

        });
        this.add(myChallengeButton);
        myChallengeButton.setVisible(false);

        limitMovement();


        JButton setting = Components.createNewButton("Setting", 600, 400, e -> new Settings());
        this.add(setting);


        JButton save = Components.createNewButton("Save", 705, 400, e -> Controller.saveGame());
        this.add(save);

        JButton exit = Components.createNewButton("Exit", 810, 400, e -> {
            Controller.quitGame();
            MainFrame.goTo(new MainMenu());
            myAnsweredQuestion = true;

        });
        this.add(exit);

        myStreakLabel = Components.createLabel(200, 410, 150, 50);
        myStreakLabel.setText("Streak: " + Controller.getStreak());
        this.add(myStreakLabel);

        myScoreLabel = Components.createLabel(10, 410, 150, 50);
        myScoreLabel.setText("Score: " + Controller.getScore());
        this.add(myScoreLabel);


        myAnswerButtons[0] = Components.createAnswerButton("A", 525, 150, e -> checkAnswer(0));
        this.add(myAnswerButtons[0]);

        myAnswerButtons[1] = Components.createAnswerButton("B", 525, 200, e -> checkAnswer(1));
        this.add(myAnswerButtons[1]);

        myAnswerButtons[2] = Components.createAnswerButton("C", 525, 250, e -> checkAnswer(2));
        this.add(myAnswerButtons[2]);

        myAnswerButtons[3] = Components.createAnswerButton("D", 525, 300, e -> checkAnswer(3));
        this.add(myAnswerButtons[3]);

        allowPress(myAnswerButtons, false);

        myOptions[0] = Components.createAnswerLabel(605, 120, 400, 100);
        this.add(myOptions[0]);

        myOptions[1] = Components.createAnswerLabel(605, 170, 400, 100);
        this.add(myOptions[1]);

        myOptions[2] = Components.createAnswerLabel(605, 220, 400, 100);
        this.add(myOptions[2]);

        myOptions[3] = Components.createAnswerLabel(605, 270, 400, 100);
        this.add(myOptions[3]);

        questionText = Components.createTextArea(525, 20, 370, 120);
        this.add(questionText);

        JLabel myBackground = Components.createBackground("static/images/Background_2.jpg", 1000, 500);
        this.add(myBackground);


    }


}
