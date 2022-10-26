package userinterface;

import gameobject.GameFuncion.Sound;
import gameobject.GameFuncion.UI;
import gameobject.GameWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Thread thread;
    private boolean isRunning;
    private static final int FPS = 80;
    private InputManager inputManager;
    private BufferedImage bufferedImage;
    private Graphics2D bufG2D;
    protected GameWorld gameWorld;

    public GamePanel() {
        gameWorld = new GameWorld();
        inputManager = new InputManager(gameWorld);
        bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bufferedImage, 0, 56, this);
    }

    public void renderGame() {
        if (bufferedImage == null) {
            bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        } else {
            bufG2D = (Graphics2D) bufferedImage.getGraphics();
        }
        if (bufG2D != null) {
            bufG2D.setColor(Color.white);
            gameWorld.Render(bufG2D);
        }
    }

    public void updateGame() {
        gameWorld.Update();
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

            updateGame();
            renderGame();

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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputManager.processKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.processKeyReleased(e.getKeyCode());
    }
}