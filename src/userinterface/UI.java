package userinterface;

import effect.CacheDataLoader;
import effect.FrameImage;

import java.awt.*;

public class UI {
    private GamePanel gamePanel;
    private Font arial_40;
    private Graphics2D g2;
    public int commandNum = 0;
    FrameImage bomber = CacheDataLoader.getInstance().getFrameImage("bomber");
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 70);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        if (gamePanel.getGameState() == gamePanel.pauseState) {
            drawPause();
        }

        if (gamePanel.getGameState() == gamePanel.titleState) {
            drawTitleScreen();
        }

        if (gamePanel.getGameState() == gamePanel.gameOverState) {
            drawGameOver();
        }
    }

    // Hien thi man hinh khi tam dung.
    public void drawPause() {
        g2.setColor(Color.orange);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "PAUSE";
        int x = getXforCenteredText(text);
        int y = GameFrame.SCREEN_HEIGHT/2 - 48 - 24;
        g2.drawString(text, x, y);
    }

    // Hien thi menu man hinh.
    public void drawTitleScreen() {
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
        // TITLE NAME.
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "BOMBERMAN";
        int x = getXforCenteredText(text);
        int y = 48 * 3;

        //SHADOW.
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR.
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);

        // BOMBER IMAGE.
        x = gamePanel.getWidth() / 2 - 48;
        y += 48;
        g2.drawImage(bomber.getImage(), x, y,150,150,null);

        // MENU.
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += 48 * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">",x - 48, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += 48;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">",x - 48, y);
        }
    }

    // Ve khi game ket thuc.
    public void drawGameOver() {
        g2.setColor(Color.orange);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "GAME OVER";
        int x = getXforCenteredText(text);
        int y = GameFrame.SCREEN_HEIGHT/2 - 48 - 24;
        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = GameFrame.SCREEN_WIDTH/2 - length/2;
        return x;
    }
}
