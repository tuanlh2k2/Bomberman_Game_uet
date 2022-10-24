package userinterface;

import gameobject.GameWorld;

import java.awt.event.KeyEvent;

/**
 * Nhan gia tri tu gamePanel de xu ly.
 */
public class InputManager {
    GamePanel gamePanel;
    private GameWorld gameWorld;

    public InputManager( GamePanel gamePanel, GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.gamePanel = gamePanel;
    }

    public void processKeyPressed(int keyCode) {
        if (gamePanel.getGameState() == gamePanel.playState) {
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
                    gameWorld.player.attack();
                    break;
                case KeyEvent.VK_ENTER:
                    if (gamePanel.getGameState() == gamePanel.playState) {
                        gamePanel.setGameState(gamePanel.pauseState);
                        System.out.println("OK");
                    }
                    break;
            }
        } else if (gamePanel.getGameState() == gamePanel.titleState) {
            switch (keyCode) {
                case KeyEvent.VK_DOWN:
                    gamePanel.getUi().commandNum++;
                    if (gamePanel.getUi().commandNum > 1) {
                        gamePanel.getUi().commandNum = 0;
                    }
                    break;
                case KeyEvent.VK_UP:
                    gamePanel.getUi().commandNum--;
                    if (gamePanel.getUi().commandNum < 0) {
                        gamePanel.getUi().commandNum = 1;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (gamePanel.getUi().commandNum == 0) {
                        gamePanel.setGameState(gamePanel.playState);
                        gamePanel.sound.stop();
                        gamePanel.sound.playMusic(0);
                    } else {
                        System.exit(0);
                    }
            }
        } else if (gamePanel.getGameState() == gamePanel.pauseState) {
            gamePanel.setGameState(gamePanel.playState);
        }
    }

    public void processKeyReleased(int keyCode) {
        if (gamePanel.getGameState() == gamePanel.playState
                || gamePanel.getGameState() == gamePanel.playState) {
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
                    break;
            }
        }
    }
}
