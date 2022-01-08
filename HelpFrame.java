import javax.swing.*;
import java.awt.*;

public class HelpFrame extends JFrame {

    public HelpFrame(){
        super("Help");
        this.add(helpPanel());

        this.setSize(750, 500);
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);
    }

    private JPanel helpPanel(){
        JPanel panel = new JPanel();
        String text = """
                Welcome to Python Trivia Maze!
                Here you can test your python skills by answering questions.
                If you are on a streak, you will be able to do a python challenge to increase your score!
                Your goal is to go through the maze and try to make it to the bottom right corner

                Select:
                New Game to start a new game
                Load Game to continue playing from the last saved game

                You will start in the top right corner and be able to move in directions RIGHT, LEFT, UP, and DOWN
                After selecting a direction, you will be asked a question and options
                If you answer correctly, the player will move to that new position and the room will be unlocked
                If you answer incorrectly, the door to the room will be locked

                Press Save to save the game
                Press Exit to quit the game.

                In Settings
                Select Mute to mute audio,
                Select Stack Overflow to enable cheats""";

        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        textArea.setForeground(Color.white);
        textArea.setOpaque(false);
        textArea.setEditable(false);

        panel.add(textArea);
        panel.setBackground(Color.black);

        return panel;



    }
}
