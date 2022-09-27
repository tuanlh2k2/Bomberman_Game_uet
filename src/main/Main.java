package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chuong trinh se tat khi click vao nut "x".
        window.setResizable(false);
        window.setTitle("Bomberman Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        gamePanel.startGameThread();
        window.setLocationRelativeTo(null); // Hien thi window ra giua man hinh.
        window.setVisible(true);
    }
}
