package gameobject;

import effect.CacheDataLoader;
import gameobject.GameFuncion.*;
import gameobject.PaticularObject.Entity.Enemy.*;
import gameobject.PaticularObject.Entity.Bomber.Player;
import gameobject.PaticularObject.Entity.Enemy.AI.AStart;
import gameobject.PaticularObject.ParticularObject;
import gameobject.PaticularObject.ParticularObjectManager;
import gameobject.PaticularObject.Tile.Brick;
import gameobject.PaticularObject.Tile.Item.*;
import gameobject.PaticularObject.Tile.Wall;
import userinterface.GameFrame;

import java.awt.*;

public class GameWorld {
    public static final int tileSize = 48;
    private int lever = 1;
    private int countEnemy = 0;
    private int scopeBom = 48;
    private int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;
    public final int nextLever = 4;
    public final int winGame = 5;

    public long timePlayGame; // thời gian chơi game.

    public Player player;
    public ParticularObjectManager particularObjectManager; // dung de chua cac doi tuong.
    public BackgroundMap backgroundMap;
    public Camera camera;
    public UI ui;

    public GameWorld() {
        timePlayGame = System.currentTimeMillis();
        setGameState(titleState);
        ui = new UI(this);
        backgroundMap = new BackgroundMap(0, 0, this);
        camera = new Camera(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
        particularObjectManager = new ParticularObjectManager(this);
        initEnemies();
    }

    public void initEnemies() {
        for (int i = 0; i < backgroundMap.map.length; i++) {
            for (int j = 0; j < backgroundMap.map[0].length(); j++) {
                if (backgroundMap.map[i].charAt(j) == 'p') {
                    player = new Player(j * backgroundMap.tileSize + backgroundMap.tileSize / 2,
                            i * backgroundMap.tileSize + tileSize/2, this);
                    player.setTeamType(ParticularObject.LEAGUE_TEAM);
                    particularObjectManager.addObject(player);
                } else if (backgroundMap.map[i].charAt(j) == '1') {
                    ParticularObject balloom = new Balloom(j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    balloom.setDirection(ParticularObject.LEFT_DIR);
                    balloom.setTeamType(ParticularObject.ENEMY_TEAM);
                    particularObjectManager.addObject(balloom);
                } else if (backgroundMap.map[i].charAt(j) == '*') {
                    ParticularObject brick = new Brick(j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    brick.setDirection(ParticularObject.LEFT_DIR);
                    particularObjectManager.addObject(brick);
                } else if (backgroundMap.map[i].charAt(j) == 's') {
                    ParticularObject speedItem = new SpeedItem(j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    particularObjectManager.addObject(speedItem);
                } else if (backgroundMap.map[i].charAt(j) == 'b') {
                    ParticularObject bombItem = new BombItem(j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    particularObjectManager.addObject(bombItem);
                } else if (backgroundMap.map[i].charAt(j) == 'f') {
                    ParticularObject flameItem = new FlameItem(j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    particularObjectManager.addObject(flameItem);
                } else if (backgroundMap.map[i].charAt(j) == 't') {
                    ParticularObject bloodItem = new BloodItem(j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    particularObjectManager.addObject(bloodItem);
                }  else if (backgroundMap.map[i].charAt(j) == 'i') {
                    ParticularObject immortalItem = new ImmortalItem(j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    particularObjectManager.addObject(immortalItem);
                }  else if (backgroundMap.map[i].charAt(j) == 'g') {
                    ParticularObject throughBrickItem = new ThroughBrickItem (j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    particularObjectManager.addObject(throughBrickItem);
                } else if (backgroundMap.map[i].charAt(j) == 'x') {
                    ParticularObject Portal = new Portal ( j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    particularObjectManager.addObject(Portal);
                } else if (backgroundMap.map[i].charAt(j) == '2') {
                    ParticularObject Oneal = new Oneal(j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    Oneal.setTeamType(ParticularObject.ENEMY_TEAM);
                    particularObjectManager.addObject(Oneal);
                } else if (backgroundMap.map[i].charAt(j) == '3') {
                    ParticularObject Minvo = new Minvo(j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    Minvo.setTeamType(ParticularObject.ENEMY_TEAM);
                    particularObjectManager.addObject(Minvo);
                } else if (backgroundMap.map[i].charAt(j) == '4') {
                    ParticularObject Doll = new Doll(j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    Doll.setTeamType(ParticularObject.ENEMY_TEAM);
                    particularObjectManager.addObject(Doll);
                } else if (backgroundMap.map[i].charAt(j) == '5') {
                    ParticularObject Kondoria = new Kondoria(j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    Kondoria.setTeamType(ParticularObject.ENEMY_TEAM);
                    particularObjectManager.addObject(Kondoria);
                } else if (backgroundMap.map[i].charAt(j) == '6') {
                    ParticularObject mommy = new Mommy(j * backgroundMap.tileSize + tileSize / 2,
                            i * tileSize + tileSize/2, this);
                    mommy.setTeamType(ParticularObject.ENEMY_TEAM);
                    particularObjectManager.addObject(mommy);
                } else if (backgroundMap.map[i].charAt(j) == '#') {
                ParticularObject wall = new Wall(j * backgroundMap.tileSize + tileSize / 2,
                        i * tileSize + tileSize/2, this);
                wall.setTeamType(ParticularObject.NO_TEAM);
                particularObjectManager.addObject(wall);
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
            }
            if (player.getOppenTheDoor() == true && this.lever <= CacheDataLoader.MAX_LEVER) {
                setGameState(nextLever);
                this.lever++;
            }
            if (player.getOppenTheDoor() == true && this.lever > CacheDataLoader.MAX_LEVER) {
                setGameState(winGame);
            }
        } if (getGameState() == nextLever) {
            nextLever();
        }
    }

    // Next lever.
    public void nextLever() {
        player.setOppenTheDoor(false);
        setScopeBom(48);
        camera = new Camera(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
        particularObjectManager.Clear();
        backgroundMap.setMap(this.lever);
        initEnemies();
        timePlayGame = System.currentTimeMillis();
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

    public int getScopeBom() {
        return scopeBom;
    }

    public void setScopeBom(int scopeBom) {
        this.scopeBom = scopeBom;
    }
}