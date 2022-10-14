package userinterface;

import gameobject.GameWorld;
import gameobject.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Nhan gia tri tu gamePanel de xu ly.
 */
public class InputManager {
    private GameWorld gameWorld;

    public InputManager(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void processKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                gameWorld.player.setSpeedY(-5);
                break;
            case KeyEvent.VK_DOWN:
                gameWorld.player.setSpeedY(5);
                break;
            case KeyEvent.VK_LEFT:
                gameWorld.player.setDirection(Player.DIR_LEFT);
                gameWorld.player.setSpeedX(-5);
                break;
            case KeyEvent.VK_RIGHT:
                gameWorld.player.setDirection(Player.DIR_RIGHT);
                gameWorld.player.setSpeedX(+5);
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("SPACE");
                break;
        }
    }

    public void processKeyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                gameWorld.player.setSpeedY(0);
                break;
            case KeyEvent.VK_DOWN:
                gameWorld.player.setSpeedY(0);
                break;
            case KeyEvent.VK_LEFT:
                gameWorld.player.setSpeedX(0);
                break;
            case KeyEvent.VK_RIGHT:
                gameWorld.player.setSpeedX(0);
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("END SPACE");
                break;
        }
    }
}
