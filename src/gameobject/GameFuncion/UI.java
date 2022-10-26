package gameobject.GameFuncion;

import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameWorld;
import userinterface.GameFrame;
import userinterface.GamePanel;

import java.awt.*;

/**
 * User interface => dung de hien thi giao dien tuong tac voi nguoi dung.
 */
public class UI {
    private GameWorld gameWorld;
    private Font arial_40;  // khai bao phong chu.
    private Graphics2D g2;
    public int commandNum = 0; // dung de kiem tra xem dang o vi tri nao (new game hay quit).
    FrameImage bomber = CacheDataLoader.getInstance().getFrameImage("bomber"); // load hinh anh bomber hien thi.

    public UI(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        arial_40 = new Font("Arial", Font.PLAIN, 70);
    }

    // dung de ve ra man hinh.
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        // Hien thi chu Pause khi nguoi dung an Enter.
        if (gameWorld.getGameState() == gameWorld.pauseState) {
            drawPause();
        }
        // Hien thi man hinh bat dau cua game (menu).
        if (gameWorld.getGameState() == gameWorld.titleState) {
            drawTitleScreen();
        }
        // Hien thi man hinh ket thuc game => in ra game over.
        if (gameWorld.getGameState() == gameWorld.gameOverState) {
            drawGameOver();
        }
    }

    // Hien thi man hinh khi tam dung (pause).
    public void drawPause() {
        g2.setColor(Color.orange);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "PAUSE";
        int x = getXforCenteredText(text);
        int y = GameFrame.SCREEN_HEIGHT/2 - 48 - 24;
        g2.drawString(text, x, y);
    }

    // Hien thi menu man hinh Menu.
    public void drawTitleScreen() {
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
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
        x = GameFrame.SCREEN_WIDTH / 2 - 48;
        y += 48;
        g2.drawImage(bomber.getImage(), x, y,144,144,null);

        // MENU.
        // New game.
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += 48 * 5;
        g2.setColor(Color.red);
        g2.fillRect(GameFrame.SCREEN_WIDTH/2 - 120, y - 48, 256, 48);
        if (commandNum == 0) {
            g2.setColor(Color.PINK);
        } else {
            g2.setColor(Color.lightGray);
        }
        g2.fillRect(GameFrame.SCREEN_WIDTH / 2 - 117, y - 45, 250, 42);
        g2.setColor(Color.black);
        g2.drawString(text, x, y - 12);

        // Quit
        text = "QUIT";
        x = getXforCenteredText(text);
        y += 50;

        g2.setColor(Color.red);
        g2.fillRect(GameFrame.SCREEN_WIDTH / 2 - 120, y - 48, 256, 48);
        if (commandNum == 1) {
            g2.setColor(Color.PINK);
        } else {
            g2.setColor(Color.lightGray);
        }
        g2.fillRect(GameFrame.SCREEN_WIDTH / 2 - 117, y - 45, 250, 42);
        g2.setColor(Color.black);
        g2.drawString(text, x, y - 12);
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

    // Xac dinh vi tri in chu => o giua man hinh.
    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = GameFrame.SCREEN_WIDTH/2 - length/2;
        return x;
    }
}