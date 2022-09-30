package main;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chuong trinh se tat khi click vao nut "x".
        window.setResizable(false);
        window.setTitle("Bomberman Game");
        GamePanel gamePanel = null;
        gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        gamePanel.startGameThread();
        window.setLocationRelativeTo(null); // Hien thi window ra giua man hinh.
        window.setVisible(true);
    }
}
