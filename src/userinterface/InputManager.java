package userinterface;

import gameobject.GameWorld;

import java.awt.event.KeyEvent;

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
                gameWorld.player.setDirection(gameWorld.player.TOP_DIR);
                gameWorld.player.run();
                break;
            case KeyEvent.VK_DOWN:
                gameWorld.player.setDirection(gameWorld.player.DOWN_DIR);
                gameWorld.player.run();
                break;
            case KeyEvent.VK_LEFT:
                gameWorld.player.setDirection(gameWorld.player.LEFT_DIR);
                gameWorld.player.run();
                break;
            case KeyEvent.VK_RIGHT:
                gameWorld.player.setDirection(gameWorld.player.RIGHT_DIR);
                gameWorld.player.run();
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("SPACE");
                break;
        }
    }

    public void processKeyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                gameWorld.player.stopRun();
                break;
            case KeyEvent.VK_DOWN:
                gameWorld.player.stopRun();
                break;
            case KeyEvent.VK_LEFT:
                gameWorld.player.stopRun();
                break;
            case KeyEvent.VK_RIGHT:
                gameWorld.player.stopRun();
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("END SPACE");
                break;
        }
    }
}
