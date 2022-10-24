package userinterface;

import effect.CacheDataLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame {
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 748;
    GamePanel gamePanel;

    public GameFrame() {
        super("Bomberman");
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize(); // Lay toa do tu phan cung.
        this.setBounds((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT)/ 2,
                SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Thoat khi nhan X.
        try {
            CacheDataLoader.getInstance().loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gamePanel = new GamePanel();
        add(gamePanel);
        this.addKeyListener(gamePanel); // Nhan duoc xu ly khi bam ban phim.
    }

    public void startGame(){
        gamePanel.startGame();
        this.setVisible(true);
    }
}
