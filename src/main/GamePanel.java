package main;

import emtity.move.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16; // khung kinh ban dau 16 x 16
    final int scale = 3;  //
    public final int titleSize = originalTileSize * scale; // ficture 48 x 48.
    final int maxScreenColum = 16;  // So khung hinh hien thi toi da tren cot
    final int maxScreenRow = 12;    // So khung hinh hien thi toi da tren hang
    final int screenWidth = maxScreenColum * titleSize;    // Chieu rong cua man hinh hien thi toi da
    final int screenHeight = maxScreenRow * titleSize;  // Chieu dai cua man hien thi toi da
    final int FPS = 60;
    KeyControl keyControl = new KeyControl();
    Thread gameThread;  //

    Player player = new Player(this, keyControl);

    /**
     * This is contructor.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the size of this class (JPanel).
        this.setBackground(Color.BLACK); // Set the background color.
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
            player.draw(graphics2D);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
