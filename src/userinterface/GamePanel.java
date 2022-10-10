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
//        try {
//            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gameImage.png"));
//            BufferedImage image1 = image.getSubimage(0, 0, 16, 16);
//            BufferedImage image2 = image.getSubimage(0, 16, 16, 16);
//            BufferedImage image3 = image.getSubimage(0, 32, 16, 16);
////
////            frame1 = new FrameImage("frame1", image1);
////            frame2 = new FrameImage("frame2", image2);
////            frame3 = new FrameImage("frame3", image3);
////
////            animation = new Animation();
////            animation.addFrame(frame1, 200 * 1000000);
////            animation.addFrame(frame2, 200 * 1000000);
////            animation.addFrame(frame3, 200 * 1000000);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0,GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
//        Graphics2D g2 = (Graphics2D) g;
//        animation.Update(System.nanoTime());
//        animation.draw(100, 130, g2);
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
