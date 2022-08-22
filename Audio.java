import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

/**
 * This class will create a background music.
 * @author Rin Pham
 * @version 1.0
 * @since 1.0
 */
 */
public class Audio {
    private static Clip myClip;
    private static final float myCurrentVolume = -12;
    private FloatControl myFc;



    public FloatControl getFc() {
        return myFc;
    }
    public  float getCurrentVolume() {
        return myCurrentVolume;
    }
    /**
     * Get url of the sound file.
     * @param url
     */
    public void setFile(URL url) {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(url);
            myClip = AudioSystem.getClip();
            myClip.open(sound);
            myFc = (FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * play music
     */
    public void play() {
        myClip.setFramePosition(0);
        myClip.start();
    }

    /**
     * Set loop for playing music
     */
    public void loop() {
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stop music
     */
    public void stop() {
        myClip.stop();
    }
}
