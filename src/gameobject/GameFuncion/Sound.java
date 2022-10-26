package gameobject.GameFuncion;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound () {
        soundURL[0] = getClass().getResource("/sounds/play.wav");
        soundURL[1] = getClass().getResource("/sounds/nobomb.wav");
        soundURL[2] = getClass().getResource("/sounds/overgame.wav");
        soundURL[3] = getClass().getResource("/sounds/menu_game.wav");
    }

    public void setFile(String name) {
        int position = getPosition(name);
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[position]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
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

    public void playMusic() {
        play();
        loop();
    }

    public int getPosition(String name) {
        if (name == "playGame") {
            return 0;
        } else if (name == "bom") {
            return 1;
        } else if (name == "overGame") {
            return 2;
        } else if (name == "startGame") {
            return 3;
        }
        return -1;
    }
}