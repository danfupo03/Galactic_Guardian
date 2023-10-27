import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("assets/sounds/menu2.wav");
        soundURL[1] = getClass().getResource("assets/sounds/interlude.wav");
        soundURL[2] = getClass().getResource("assets/sounds/gameOver.wav");
        soundURL[3] = getClass().getResource("assets/sounds/explosion.wav");
        soundURL[4] = getClass().getResource("assets/sounds/explosion2.wav");
        soundURL[5] = getClass().getResource("assets/sounds/pause.wav");
        soundURL[6] = getClass().getResource("assets/sounds/unpause.wav");
        soundURL[7] = getClass().getResource("assets/sounds/shoot.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            System.out.println("Error loading sound: " + e);
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}