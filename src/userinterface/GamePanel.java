package userinterface;

import effect.Animation;
import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameFuncion.Sound;
import gameobject.GameWorld;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Thread thread;
    private boolean isRunning;
    private static final int FPS = 80;
    private InputManager inputManager;
    private UI ui;
    private BufferedImage bufferedImage;
    private Graphics2D bufG2D;
    protected GameWorld gameWorld;
    Sound sound = new Sound();

    private int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;

    public GamePanel() {
        gameWorld = new GameWorld();
        inputManager = new InputManager( this ,gameWorld);
        ui = new UI(this);
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
            if (gameState == playState || gameState == pauseState){
                gameWorld.Render(bufG2D);
            }
            if (gameState == gameOverState) {
                sound.stop();
            }
            ui.draw(bufG2D);
        }
    }

    public void updateGame() {
        if (gameState == playState) {
            gameWorld.Update();
            if (gameWorld.player.getStatePlayer() == false) {
                setGameState(gameOverState);
            }
        }
    }

    public void startGame() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
            isRunning = true;
        }
        gameState = titleState;
        sound.playMusic(3);
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

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }
}
