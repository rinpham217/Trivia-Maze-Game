import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * The frame where the important components of the game will be (Main Menu and Game Screen)
 *
 * @author Ilya Kozorezov
 * @version 1.0
 * @since 1.0
 */

public class MainFrame {
    private static JFrame myFrame;

    /**
     * Creates a new frame
     */
    public MainFrame() {
        goTo(new MainMenu());
    }

    public static void closeFrame() {
        myFrame.dispose();
    }

    public static void goTo(final JPanel thePanel) {
        myFrame = new JFrame();
        myFrame.setTitle("Trivia Maze Game");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1000, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        myFrame.setLocation(dim.width / 2 - myFrame.getSize().width / 2, dim.height / 2 - myFrame.getSize().height / 2);
        myFrame.add(thePanel);
        myFrame.setVisible(true);
        myFrame.setResizable(false);
    }
}
