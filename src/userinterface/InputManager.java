package userinterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager {
    private GamePanel gamePanel;

    public InputManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void processKeyPressed(int keyCode) {
        switch (keyCode ) {
            case KeyEvent.VK_UP:
                gamePanel.player.setSpeedY(-5);
                break;
            case KeyEvent.VK_DOWN:
                gamePanel.player.setSpeedY(5);
                break;
            case KeyEvent.VK_LEFT:
                gamePanel.player.setSpeedX(-5);
                break;
            case KeyEvent.VK_RIGHT:
                gamePanel.player.setSpeedY(5);
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("BOM");
                break;
        }
    }

    public void processKeyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                gamePanel.player.setSpeedY(-5);
                break;
            case KeyEvent.VK_DOWN:
                gamePanel.player.setSpeedY(5);
                break;
            case KeyEvent.VK_LEFT:
                gamePanel.player.setSpeedX(-5);
                break;
            case KeyEvent.VK_RIGHT:
                gamePanel.player.setSpeedY(5);
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("BOM");
                break;
        }
    }
}
