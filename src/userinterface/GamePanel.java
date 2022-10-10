package userinterface;

import effect.Animation;
import effect.FrameImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    private Thread thread;
    private boolean isRunning;
    private static final int FPS = 80;
    private InputManager inputManager;
//    BufferedImage image;
//    FrameImage frame1, frame2, frame3;
//    Animation animation;

    public GamePanel() {
        inputManager = new InputManager();
//            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gameImage.png"));
//            BufferedImage image1 = image.getSubimage(0, 0, 16, 16);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
    }

    public void startGame() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
            isRunning = true;
        }
    }

    @Override
    public void run() {
        long period = (1000 * 1000000) / FPS;
        long beginTime;
        long sleepTime = 0;
        beginTime = System.nanoTime();
        while (isRunning) {

            // Updategame.
            // Rendergame.
            repaint();
            long deltaTime = System.nanoTime() - beginTime;
            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime/1000000);
                } else {
                    Thread.sleep(period / 2000000);
                }
            } catch (InterruptedException ex) {}
        }
    }
}
