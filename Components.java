import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

/**
 * Creates components to be used in java swing files
 *
 * @author Ilya Kozorezov
 * @author Rin Pham
 * @version 1.0
 * @since 1.0
 */

public class Components extends JComponent{

    /**
     *  Creates a new Label based on the parameters.
     * @param theX : x position
     * @param theY : y position
     * @param theWidth the width of the label
     * @param theHeight: the height of the label
     * @return a label.
     */
    static JLabel createLabel(final int theX, final int theY, final int theWidth, final int theHeight) {
        JLabel label = new JLabel();
        label.setBounds(theX,theY,theWidth,theHeight);
        label.setFocusable(true);
        label.setFont(new Font(Font.DIALOG,  Font.BOLD, 25));
        label.setForeground(Color.YELLOW);
        label.setBackground(Color.BLUE);
        return label;
    }


    /**
     * Creates a new textarea based on the parameters.
     * @param theX : x position
     * @param theY: y position
     * @param theWidth : the width of the text area
     * @param theHeight: the height of the text area.
     * @return a text area object.
     */
    static JTextArea createTextArea(final int theX, final int theY, final int theWidth, final int theHeight) {
        JTextArea textArea = new JTextArea();
        textArea.setBounds(theX, theY, theWidth, theHeight);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.CYAN);
        textArea.setForeground(Color.BLUE);
        textArea.setFont(new Font("MV Boli", Font.BOLD,25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);
        return  textArea;
    }
    /**
     * Creates a new Button based on the parameters
     *
     * @param theName as the text of the button
     * @param theX the x position of the button
     * @param theY the y position of the button
     * @param theAction what the button is supposed to do
     *
     * @return a new JButton
     */
    static JButton createNewButton(final String theName, final int theX, final int theY, final ActionListener theAction){
        JButton button = new JButton(theName);
        button.setBounds(theX,theY,90,42);
        button.setFocusable(false);
        button.addActionListener(theAction);
        button.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));
        button.setForeground(Color.YELLOW);
        button.setBackground(Color.BLUE);
        button.setBorder(BorderFactory.createEtchedBorder());
        return button;
    }
    /**
     * Creates a new Button based on the parameters
     *
     * @param theName as the text of the button
     * @param theX the x position of the button
     * @param theY the y position of the button
     * @param theAction what the button is supposed to do
     *
     * @return a new JButton
     */
    static JButton createAnswerButton(final String theName, final int theX, final int theY, final ActionListener theAction) {
        JButton answer = new JButton(theName);
        answer.setBounds(theX,theY,75,42);
        answer.setFocusable(false);
        answer.addActionListener(theAction);
        answer.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));
        answer.setForeground(Color.blue);
        answer.setBackground(Color.cyan);
        answer.setBorder(BorderFactory.createEtchedBorder());
        return answer;
    }
    /**
     *  Creates a new Label based on the parameters.
     * @param theX : x position
     * @param theY : y position
     * @param theWidth the width of the label
     * @param theHeight: the height of the label
     * @return a label.
     */

    static JLabel createAnswerLabel(final int theX, final int theY, final int theWidth, final int theHeight) {
        JLabel answerLabel = new JLabel();
        answerLabel.setBounds(theX,theY,theWidth,theHeight);
        answerLabel.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));
        answerLabel.setForeground(Color.YELLOW);
        answerLabel.setBackground(Color.BLUE);

        return answerLabel;

    }

    /**
     * Creates the Background for the title page\
     *
     * @return A JLabel as the background
     */
    public static JLabel createBackground(final String theLocation, final int theWidth, final int theHeight){
        JLabel background = new JLabel();
        ImageIcon imageIcon = new ImageIcon(theLocation);
        Image image = imageIcon.getImage().getScaledInstance(theWidth,theHeight, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        background.setIcon(imageIcon);
        background.setBounds(0,0,theWidth, theHeight);
        return background;
    }

    /**
     * Creates the Title for the Title Page
     *
     * @return a JLabel for the Title
     */
    static JLabel createTitleLabel(final int theX, final String theTitle){
        JLabel label = new JLabel(theTitle);
        label.setFont(new Font(Font.DIALOG,  Font.BOLD, 60));
        label.setBounds(theX,10,700,70);
        label.setForeground(Color.WHITE);
        return label;
    }

    /**
     * Creates a new Button based on the parameters
     *
     * @param theName as the text of the button
     * @param theXPosition the y position of the button
     * @param theYPosition the y position of the button
     * @param theAction what the button is supposed to do
     *
     * @return a new JButton
     */
    static JButton createMenuButton(final String theName, final int theXPosition, final int theYPosition, final ActionListener theAction){
        JButton button = new JButton(theName);
        button.setBounds(theXPosition,theYPosition,200,50);
        button.setFocusable(false);
        button.addActionListener(theAction);
        button.setFont(new Font(Font.DIALOG,  Font.BOLD, 30));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLUE);
        button.setBorder(BorderFactory.createEtchedBorder());
        return(button);
    }}