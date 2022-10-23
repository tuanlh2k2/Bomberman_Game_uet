package userinterface;

import java.awt.*;

public class UI {
    private GamePanel gamePanel;
    private Font arial_40;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 70);
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.orange);

        if (gamePanel.getGameState() == gamePanel.pauseState) {
            g2.drawString("PAUSE", GameFrame.SCREEN_WIDTH/2 - 48 - 24, GameFrame.SCREEN_HEIGHT/2 - 48 - 24);
        }
    }
}
