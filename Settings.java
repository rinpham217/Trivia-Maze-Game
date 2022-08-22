import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;

/**
 * The settings frame where user can change settings
 *
 * @author Ilya Kozorezov
 * @author Rin Pham
 * @version 1.0
 * @since 1.0
 */

public class Settings extends JFrame {
    private static final String myBackgroundSettings = "static/images/Settings_background.jpg";
    private static URL mySoundURL = Settings.class.getResource("sound.wav");
    private static Audio mySound = new Audio();


    /**
     * Creates a new settings frame
     */
    public Settings() {
        super("Settings");
        this.add(createSettingPanel());


        this.setSize(500, 500);
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);

    }

    /**
     * Creates a new settings panel
     */
    private JPanel createSettingPanel() {
        JPanel panel = new JPanel();
        panel.add(Components.createTitleLabel(100, "Settings"));
        createVolume(panel);
        JCheckBox mute = createCheckedSetting("Muted", 200);
        mute.addItemListener(e -> {
            if (mute.isSelected()) {
                mySound.stop();
            } else {
                mySound.play();
            }
        });
        panel.add(mute);

        JCheckBox cheats = createCheckedSetting("Stack Overflow", 250);
        cheats.setSelected(Controller.cheatsAllowed());
        cheats.addActionListener(e -> Controller.allowCheats(cheats.isSelected()));
        this.add(cheats);


        panel.add(Components.createBackground(myBackgroundSettings, 500, 500));
        panel.setLayout(null);
        return panel;

    }

    /**
     * Creates the Volume components for the panel passed as the parameter
     *
     * @param thePanel the panel where the volume components should be added
     */
    private void createVolume(final JPanel thePanel){
        JLabel volumeLabel = new JLabel("Volume");
        volumeLabel.setFont(new Font(Font.DIALOG,  Font.BOLD, 30));
        volumeLabel.setBounds(40,150,150,25);
        volumeLabel.setForeground(Color.white);
        JSlider volume = new JSlider(-40, 6);
        volume.setName("Volume");
        volume.setOpaque(false);
        volume.setBounds(175,150,250,25);
        volume.addChangeListener(e -> mySound.getFc().setValue(volume.getValue()));
        thePanel.add(volumeLabel);
        thePanel.add(volume);
    }

    /**
     * Creates a checkbox and adds it to the panel passed as a parameter
     *
     * @param theTitle     the text of the checkbox
     * @param theYPosition where the y position should be
     */
    private JCheckBox createCheckedSetting(final String theTitle, final int theYPosition) {
        JCheckBox checkBox = new JCheckBox("   " + theTitle);
        checkBox.setBounds(40, theYPosition, 300, 50);
        checkBox.setFocusable(false);
        checkBox.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        checkBox.setForeground(Color.white);
        checkBox.setOpaque(false);
        return checkBox;
    }

    /**
     * play music background.
     */
    public void playMusic() {
        mySound.setFile(mySoundURL);
        mySound.play();
        mySound.loop();
    }


}
