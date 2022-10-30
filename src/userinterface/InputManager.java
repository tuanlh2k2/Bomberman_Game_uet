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
        if (gameWorld.getGameState() == gameWorld.playState) {
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
                    if (gameWorld.getGameState() == gameWorld.playState) {
                        gameWorld.setGameState(gameWorld.pauseState);
                    }
                    break;
            }
        } else if (gameWorld.getGameState() == gameWorld.titleState) {
            switch (keyCode) {
                case KeyEvent.VK_DOWN:
                    gameWorld.ui.commandNum++;
                    if (gameWorld.ui.commandNum > 1) {
                        gameWorld.ui.commandNum = 0;
                    }
                    break;
                case KeyEvent.VK_UP:
                    gameWorld.ui.commandNum--;
                    if (gameWorld.ui.commandNum < 0) {
                        gameWorld.ui.commandNum = 1;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (gameWorld.ui.commandNum == 0) {
                        gameWorld.setGameState(gameWorld.nextLever);
                    } else {
                        System.exit(0);
                    }
            }
        } else if (gameWorld.getGameState() == gameWorld.pauseState) {
            gameWorld.setGameState(gameWorld.playState);
        } else if (gameWorld.getGameState() == gameWorld.nextLever) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gameWorld.setGameState(gameWorld.playState);
            }
        }
    }

    public void processKeyReleased(int keyCode) {
        if (gameWorld.getGameState() == gameWorld.playState
                || gameWorld.getGameState() == gameWorld.playState) {
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