package main;

import Tiles.TileManager;
import emtity.Player;
import gameFunction.Constant;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, Constant {
    final int FPS = 70;
    KeyControl keyControl = new KeyControl();
    Thread gameThread;  //
    public Player player = new Player(this, keyControl);
    TileManager tileManager = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this, tileManager);

    /**
     * This is contructor.
     */
    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the size of this class (JPanel).
        this.setBackground(Color.black); // Set the background color.
        this.setDoubleBuffered(true); // if set to true, all the drawing from this component will be done in offscreen painting buffer.
        this.addKeyListener(keyControl);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // ham nay se tu dong goi den ham run.
    }

    /**
     * This is game loop.
     */
    @Override
    public void run() {
        while (gameThread != null) {
            double drawInterval = 100000000 / FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 100000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        try {
            tileManager.drawMap(graphics2D);
            player.draw(graphics2D);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
