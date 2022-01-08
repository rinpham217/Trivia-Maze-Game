import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

/**
 * Main Menu Screen
 *
 * @author Ilya Kozorezov
 * @version 1.0
 * @since 1.0
 */
public class MainMenu extends JPanel {
    private JButton myNewGame;
    private JButton myLoadGame;
    private JButton mySettings;
    private final JTextArea myName = new JTextArea("Name");
    private JComboBox<String> myDifficulty;
    private JButton mySubmit;
    private JButton myBack;
    private static final String myBackgroundMain = "static/images/main_menu_background.jpg";

    /**
     * Constructor for Creating the Main Menu
     */
    public MainMenu() {
        super();
        createTitlePage();
        this.add(Components.createBackground(myBackgroundMain, 1000, 500));
        this.setLayout(null);

    }

    /**
     * Creates the Title Page with all the components of the Main Menu Screen
     */
    private void createTitlePage() {
        this.add(Components.createTitleLabel(200, "Python Trivia Game"));
        myNewGame = Components.createMenuButton("New Game", 100, 150, e -> switchScreens(false));
        this.add(myNewGame);
        myLoadGame = Components.createMenuButton("Load Game", 100, 210, e -> Controller.loadGame());
        File temp = new File("savedGame");
        if (!temp.exists())
            myLoadGame.setEnabled(false);
        this.add(myLoadGame);
        mySettings = Components.createMenuButton("Settings", 100, 270, e -> new Settings());
        this.add(mySettings);
        createNewGameScreen();

        this.add(Components.createMenuButton("Help", 100,330, e -> new HelpFrame()));

    }

    /**
     * The Screen that will display if user wants to create a new game
     */
    private void createNewGameScreen() {
        myName.setBounds(100, 150, 200, 50);
        myName.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        myName.setVisible(false);
        this.add(myName);

        myDifficulty = new JComboBox<>(new String[]{"Easy", "Medium", "Hard"});
        myDifficulty.setBounds(100, 210, 200, 50);
        myDifficulty.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        myDifficulty.setBackground(Color.WHITE);
        myDifficulty.setVisible(false);
        this.add(myDifficulty);

        mySubmit = Components.createMenuButton("Submit", 100, 270, e -> startGame());
        mySubmit.setVisible(false);
        this.add(mySubmit);

        myBack = Components.createMenuButton("Back", 100, 330, e -> switchScreens(true));
        myBack.setVisible(false);
        this.add(myBack);
    }

    /**
     * Switches between main menu and new game screen
     *
     * @param theScreen as if the screen is the main menu
     */
    private void switchScreens(final boolean theScreen) {
        myNewGame.setVisible(theScreen);
        myLoadGame.setVisible(theScreen);
        mySettings.setVisible(theScreen);
        myName.setVisible(!theScreen);
        myDifficulty.setVisible(!theScreen);
        mySubmit.setVisible(!theScreen);
        myBack.setVisible(!theScreen);
    }

    /**
     * Starts a new game based on what the user inputted into new game screen
     */
    private void startGame() {
        Controller.newGameQuestion();
        Controller.startNewGame(myName.getText(), Difficulty.valueOf(myDifficulty.getSelectedIndex() + 1));
    }
}
