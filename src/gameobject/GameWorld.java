package gameobject;

import effect.CacheDataLoader;
import gameobject.GameFuncion.*;
import gameobject.paticularObject.Entity.Enemy.*;
import gameobject.paticularObject.Entity.Bomber.Player;
import gameobject.paticularObject.ParticularObject;
import gameobject.paticularObject.ParticularObjectManager;
import gameobject.paticularObject.Tile.Brick;
import gameobject.paticularObject.Tile.Item.FlameItem;
import gameobject.paticularObject.Tile.Item.Portal;
import gameobject.paticularObject.Tile.Item.SpeedItem;
import gameobject.paticularObject.Tile.Item.BombItem;
import userinterface.GameFrame;

import java.awt.*;

public class GameWorld {
    private int lever = 1;
    private int countEnemy = 0;
    private int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;
    public final int nextLever = 4;

    public Player player;
    public PhysicalMap physicalMap;
    public ParticularObjectManager particularObjectManager; // dung de chua cac doi tuong.
    public BackgroundMap backgroundMap;
    public Camera camera;
    public UI ui;

    public GameWorld() {
        setGameState(titleState);
        ui = new UI(this);
        physicalMap = new PhysicalMap(0, 0, this);
        backgroundMap = new BackgroundMap(0, 0, this);
        camera = new Camera(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
        particularObjectManager = new ParticularObjectManager(this);
        initEnemies();
    }

    public void initEnemies() {
        for (int i = 0; i < backgroundMap.map.length; i++) {
            for (int j = 0; j < backgroundMap.map[0].length(); j++) {
                if (backgroundMap.map[i].charAt(j) == 'p') {
                    player = new Player(j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * backgroundMap.tileSize + 24, this);
                    player.setTeamType(ParticularObject.LEAGUE_TEAM);
                    particularObjectManager.addObject(player);
                } else if (backgroundMap.map[i].charAt(j) == '1') {
                    ParticularObject balloom = new Balloom(j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * 48 + 24, this);
                    balloom.setDirection(ParticularObject.LEFT_DIR);
                    balloom.setTeamType(ParticularObject.ENEMY_TEAM);
                    particularObjectManager.addObject(balloom);
                } else if (backgroundMap.map[i].charAt(j) == '*')
                {
                    ParticularObject brick = new Brick(j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * 48 + 24, this);
                    brick.setDirection(ParticularObject.LEFT_DIR);
                    particularObjectManager.addObject(brick);
                } else if (backgroundMap.map[i].charAt(j) == 's') {
                    ParticularObject speedItem = new SpeedItem(j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * 48 + 24, this);
                    particularObjectManager.addObject(speedItem);
                } else if (backgroundMap.map[i].charAt(j) == 'b') {
                    ParticularObject bombItem = new BombItem(j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * 48 + 24, this);
                    particularObjectManager.addObject(bombItem);
                } else if (backgroundMap.map[i].charAt(j) == 'f') {
                    ParticularObject flameItem = new FlameItem(j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * 48 + 24, this);
                    particularObjectManager.addObject(flameItem);
                } else if (backgroundMap.map[i].charAt(j) == 'x') {
                    ParticularObject Portal = new Portal ( j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * 48 + 24, this);
                    particularObjectManager.addObject(Portal);
                }
                else if (backgroundMap.map[i].charAt(j) == '2') {
                    ParticularObject Oneal = new Oneal(j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * 48 + 24, this);
                    Oneal.setTeamType(ParticularObject.ENEMY_TEAM);
                    particularObjectManager.addObject(Oneal);
                } else if (backgroundMap.map[i].charAt(j) == '3') {
                    ParticularObject Minvo = new Minvo(j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * 48 + 24, this);
                    Minvo.setTeamType(ParticularObject.ENEMY_TEAM);
                    particularObjectManager.addObject(Minvo);
                } else if (backgroundMap.map[i].charAt(j) == '4') {
                    ParticularObject Doll = new Doll(j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * 48 + 24, this);
                    Doll.setTeamType(ParticularObject.ENEMY_TEAM);
                    particularObjectManager.addObject(Doll);
                } else if (backgroundMap.map[i].charAt(j) == '5') {
                    ParticularObject Kondoria = new Kondoria(j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * 48 + 24, this);
                    Kondoria.setTeamType(ParticularObject.ENEMY_TEAM);
                    particularObjectManager.addObject(Kondoria);
                }
            }
        }
    }

    public void Update() {
        if (getGameState() == playState) {
            particularObjectManager.UpdateObjects();
            camera.Update();
            if (player.getStatePlayer() == false) {
                setGameState(gameOverState);
            } if (player.getOppenTheDoor() == true && this.lever < CacheDataLoader.MAX_LEVER) {
                setGameState(nextLever);
                this.lever++;
            }
        }
        if (getGameState() == nextLever) {
            nextLever();
        }
    }

    // Next lever.
    public void nextLever() {
        player.setOppenTheDoor(false);
        camera = new Camera(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
        particularObjectManager.Clear();
        backgroundMap.setMap(this.lever);
        initEnemies();
    }

    public void Render(Graphics2D g2) {
        ui.draw(g2);
        if (getGameState() == playState || getGameState() == pauseState) {
            backgroundMap.draw(g2);
            particularObjectManager.draw(g2);
            ui.draw(g2);
        }
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getLever() {
        return lever;
    }

    public void setLever(int lever) {
        this.lever = lever;
    }

    public int getCountEnemy() {
        return countEnemy;
    }

    public void setCountEnemy(int countEnemy) {
        this.countEnemy = countEnemy;
    }
}