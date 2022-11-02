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
    private FrameImage monster = CacheDataLoader.getInstance().getFrameImage("monster"); // load hinh anh bomber hien thi.
    private FrameImage key = CacheDataLoader.getInstance().getFrameImage("key");
    private FrameImage backGround = CacheDataLoader.getInstance().getFrameImage("background"); // load hinh anh bomber hien thi.
    private FrameImage stage1 = CacheDataLoader.getInstance().getFrameImage("stage1");
    private FrameImage winGame = CacheDataLoader.getInstance().getFrameImage("win");
    private FrameImage heart = CacheDataLoader.getInstance().getFrameImage("heart");
    private Sound soundPlayGame = new Sound();
    private Sound soundStartGame = new Sound();
    private Sound soundReadyGame = new Sound();
    private Sound soundWinGame = new Sound();

    public UI(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        arial_40 = new Font("Arial", Font.PLAIN, 70);

        soundPlayGame.setFile("playGame");
        soundStartGame.setFile("startGame");
        soundReadyGame.setFile("readyGame");
        soundWinGame.setFile("winGame");
    }

    // dung de ve ra man hinh.
    public void draw(Graphics2D g2) {
        playMusic();
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

        if (gameWorld.getGameState() == gameWorld.nextLever) {
            drawNewGame();
        }

        if (gameWorld.getGameState() == gameWorld.playState) {
            drawPlayGame();
        }

        if (gameWorld.getGameState() == gameWorld.winGame) {
            drawWinGame();
        }
    }

    // Hien thi man hinh khi choi.
    public void drawPlayGame() {
        // Hiển thị lever hiện tại.
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        String text = "Lever " + gameWorld.getLever();
        g2.drawString(text,48 * 5, 36);

        // Ve hinh so quai con lai.
        g2.drawImage(monster.getImage(), 48 * 18,0, 48,48, null);
        g2.setColor(Color.orange);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        text = "X" + gameWorld.getCountEnemy();
        g2.drawString(text,48 * 19, 36);

        // Ve hinh key.
        g2.drawImage(key.getImage(),48 * 16 + 24, 5, 36, 36, null);
        g2.setColor(Color.orange);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        text = "X" + (gameWorld.getCountEnemy() == 0 ? 1 : 0);
        g2.drawString(text,48 * 17 + 10, 36);

        // Ve so mang con lai.
        for (int i = 0; i < gameWorld.player.getBlood(); i++) {
            g2.drawImage(heart.getImage(),48 * i, 5, 36, 36, null);
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
    // Hien thi man hinh khi chuyen man.
    public void drawNewGame() {
        g2.drawImage(stage1.getImage(), 0, 0 , GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 128F));
        String text = "STAGE " + gameWorld.getLever();

        int x = getXforCenteredText(text);
        int y = GameFrame.SCREEN_HEIGHT/2 - 48 * 4;

        // In bóng.
        g2.setColor(Color.black);
        g2.drawString(text, x + 6, y + 6);
        // In chữ.
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);


        // Ve chu plese ....
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 21F));
        text = "Press Enter to continue....";

        x = GameFrame.WIDTH/2 + 48 * 12;
        y = GameFrame.SCREEN_HEIGHT/2 - 48 * 3;
        g2.setColor(Color.ORANGE);
        g2.drawString(text, x, y);

    }

    // Hien thi menu man hinh Menu.
    public void drawTitleScreen() {
        g2.drawImage(backGround.getImage(), 0,0,GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,null);

        String text;
        int x;
        int y;

        // MENU.
        // New game.
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y = 48 * 12;
        g2.setColor(Color.ORANGE);
        g2.fillRect(GameFrame.SCREEN_WIDTH/2 - 120, y - 48, 256, 48);
        if (commandNum == 0) {
            g2.setColor(Color.green);
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

        g2.setColor(Color.ORANGE);
        g2.fillRect(GameFrame.SCREEN_WIDTH / 2 - 120, y - 48, 256, 48);
        if (commandNum == 1) {
            g2.setColor(Color.green);
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

        // Menu.
        // replay.
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
        text = "replay";
        x = getXforCenteredText(text);
        y = 48 * 7;
        g2.setColor(Color.ORANGE);
        g2.fillRect(GameFrame.SCREEN_WIDTH/2 - 120, y - 48, 256, 48);
        if (commandNum == 0) {
            g2.setColor(Color.green);
        } else {
            g2.setColor(Color.lightGray);
        }
        g2.fillRect(GameFrame.SCREEN_WIDTH / 2 - 117, y - 45, 250, 42);
        g2.setColor(Color.black);
        g2.drawString(text, x, y - 12);

        // Quit
        text = "quit";
        x = getXforCenteredText(text);
        y += 50;

        g2.setColor(Color.ORANGE);
        g2.fillRect(GameFrame.SCREEN_WIDTH / 2 - 120, y - 48, 256, 48);
        if (commandNum == 1) {
            g2.setColor(Color.green);
        } else {
            g2.setColor(Color.lightGray);
        }
        g2.fillRect(GameFrame.SCREEN_WIDTH / 2 - 117, y - 45, 250, 42);
        g2.setColor(Color.black);
        g2.drawString(text, x, y - 12);
    }

    // Hien thi ra man hinh chien thang.
    public void drawWinGame() {
        g2.drawImage(winGame.getImage(), 0,0,GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, null);
    }

    // Xac dinh vi tri in chu => o giua man hinh.
    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = GameFrame.SCREEN_WIDTH/2 - length/2;
        return x;
    }

    public void playMusic() {
        if (gameWorld.getGameState() == gameWorld.playState) {
            soundReadyGame.stop();
            soundStartGame.stop();
            soundPlayGame.playMusic();
        } else if (gameWorld.getGameState() == gameWorld.titleState) {
            soundReadyGame.stop();
            soundPlayGame.stop();
            soundStartGame.playMusic();
        } else if (gameWorld.getGameState() == gameWorld.nextLever){
            soundStartGame.stop();
            soundPlayGame.stop();
            soundReadyGame.playMusic();
        } else if (gameWorld.getGameState() == gameWorld.winGame) {
            soundPlayGame.stop();
            soundWinGame.playMusic();
        }
    }
}