
/**
 * Controls flow of information and inputs from Model to View
 *
 * @author Ilya Kozorezov
 * @author Rin Pham
 * @version 1.0
 * @since 1.0
 */

public class Controller {
    private static final QuestionManager myQuestionManager = new QuestionManager();
    private static final ChallengeManager myChallengeManager = new ChallengeManager();

    private static boolean myCheats = false;

    /**
     * Starts a new game
     *
     * @param theName       the name as a string for the player
     * @param theDifficulty the difficulty of the game for the player
     */
    public static void startNewGame(final String theName, final Difficulty theDifficulty) {
        GameState.newGame(theName, theDifficulty);
        MainFrame.closeFrame();

        MainFrame.goTo(new MainScreen(theDifficulty.getValue()));
    }

    /**
     * Increment the streak by 1
     *
     * @return the new streak value
     */
    public static int updateStreak() {
        return Player.getPlayer().updateStreak();
    }

    /**
     * set streak to 0
     *
     * @return the new streak value
     */
    public static int resetStreak() {
        return Player.getPlayer().resetStreak();
    }

    /**
     * Update the score based on the amount
     *
     * @param theAmount the amount to be added to the score
     * @return the new updated score
     */
    public static int updateScore(final int theAmount) {
        return Player.getPlayer().updateScore(theAmount);
    }

    /**
     * @return the current score
     */
    public static int getScore() {
        return Player.getPlayer().getScore();
    }

    /**
     * @return the current streak
     */
    public static int getStreak() {
        return Player.getPlayer().getStreak();
    }

    /**
     * set the current player position
     *
     * @param thePos where the player should be
     */
    public static void setPlayerPos(final int thePos) {
        Player.getPlayer().setPlayerPosition(thePos);
    }

    /**
     * add the first question for a new game
     */
    public static void newGameQuestion() {
        myQuestionManager.newGameQuestion();
    }

    /**
     * Loads the saved game
     */
    public static void loadGame() {
        GameState.loadGame();
        MainFrame.closeFrame();
        MainFrame.goTo(new MainScreen(Player.getPlayer().getLevel().getValue()));
    }

    /**
     * See if door is unlocked in a certain direction
     *
     * @param theDirection where to look
     * @return a boolean if that door is unlocked
     */
    public static boolean isRoomUnlocked(final Directions theDirection) {
        return Player.getPlayer().getRoomsUnlocked().contains(Player.getPlayer().attemptMove(theDirection));
    }

    /**
     * @return get an array of unlocked rooms
     */
    public static int[] getRoomsUnlocked() {
        return Player.getPlayer().getRoomsUnlocked().stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Move the player in the direction
     *
     * @param theDirection where the player should move
     */
    public static void movePlayer(final Directions theDirection) {
        Player.getPlayer().movePlayer(theDirection);
    }

    /**
     * return if a player can go in a certain direction
     *
     * @param theDirection the direction
     * @return a boolean if a player can do in that direction
     */
    public static boolean canPlayerGo(final Directions theDirection) {
        return Player.getPlayer().attemptMove(theDirection) != Player.getPlayer().getPlayerPosition();
    }

    /**
     * Quit the current game
     */
    public static void quitGame() {
        MainFrame.closeFrame();
        resetQuestions();
        GameState.endGame();
    }

    /**
     * Save the current game
     */
    public static void saveGame() {
        GameState.saveGame();
    }


    /**
     * Get player position
     */
    public static int getPlayerPos() {
        return Player.getPlayer().getPlayerPosition();
    }

    /**
     * Returns a question from the database
     *
     * @return a Question object
     */

    public static Question getQuestion() {
        return myQuestionManager.getNextQuestion();
    }

    /**
     * Close door if answered question incorrectly
     *
     * @param theDir the direction to close the door
     * @return if closing that door ended the game
     */
    public static boolean answeredWrong(final Directions theDir) {
        return Player.getPlayer().closeDoor(theDir);
    }

    /**
     * Randomly restocks the questions
     */
    public static void resetQuestions() {
        myQuestionManager.reStackQuestions();
    }


    /**
     * Returns the current challenge as a String array
     *
     * @return a String array as the challenge
     */
    public static String[] getChallenge() {
        myChallengeManager.nextChallenge();
        return myChallengeManager.getChallenge();
    }

    /**
     * Returns the result of the challenge after the python file is run
     *
     * @return boolean if the challenge was a success
     */
    public static boolean getChallengeResult() {
        return myChallengeManager.runScript();
    }

    /**
     * @return if cheats are allowed
     */
    public static boolean cheatsAllowed() {
        return myCheats;
    }

    /**
     * select whether cheats are allowed or not
     *
     * @param theAllowed if cheats should be allowed
     */
    public static void allowCheats(final boolean theAllowed) {
        myCheats = theAllowed;
    }

    /**
     * Play music
     */
    public static void playMusicBackground() {
        new Settings().playMusic();
    }

}
