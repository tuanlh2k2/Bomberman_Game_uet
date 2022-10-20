package gameobject;

import gameobject.paticularObject.Entity.Enemy.Balloom;
import gameobject.paticularObject.Entity.Bomber.Player;
import gameobject.paticularObject.ParticularObject;
import gameobject.paticularObject.ParticularObjectManager;
import gameobject.paticularObject.Tile.Brick;
import gameobject.paticularObject.Weapon.WeaponManager;
import userinterface.GameFrame;

import java.awt.*;

public class GameWorld {
    public Player player;
    public PhysicalMap physicalMap;
    public ParticularObjectManager particularObjectManager; // dung de chua cac doi tuong.
    public BackgroundMap backgroundMap;
    public WeaponManager weaponManager; // dieu khien vu khi.
    public Camera camera;

    public GameWorld() {
        physicalMap = new PhysicalMap(0, 0, this);
        player = new Player(72, 72, this);
        player.setTeamType(ParticularObject.LEAGUE_TEAM);
        backgroundMap = new BackgroundMap(0, 0, this);

        camera = new Camera(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
        weaponManager = new WeaponManager(this);
        particularObjectManager = new ParticularObjectManager(this);
        particularObjectManager.addObject(player);
        initEnemies();
    }

    public void initEnemies() {
        for (int i = 0; i < backgroundMap.map.length; i++) {
            for (int j = 0; j < backgroundMap.map[0].length(); j++) {
                if (backgroundMap.map[i].charAt(j) == '1') {
                    ParticularObject balloom = new Balloom(j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * 48 + 24, this);
                    balloom.setDirection(ParticularObject.LEFT_DIR);
                    balloom.setTeamType(ParticularObject.ENEMY_TEAM);
                    particularObjectManager.addObject(balloom);
                } else if (backgroundMap.map[i].charAt(j) == '*') {
                    ParticularObject brick = new Brick(j * backgroundMap.tileSize + backgroundMap.tileSize / 2, i * 48 + 24, this);
                    brick.setDirection(ParticularObject.LEFT_DIR);
                    particularObjectManager.addObject(brick);
                }
            }
            System.out.println(backgroundMap.map[i]);
        }
    }

    public void Update() {
       // player.Update();
        particularObjectManager.UpdateObjects();
        camera.Update();
        weaponManager.UpdateObjects();
    }

    public void Render(Graphics2D g2) {
        //physicalMap.draw(g2);
        backgroundMap.draw(g2);
//        player.draw(g2);
        particularObjectManager.draw(g2);
        weaponManager.draw(g2);
    }
}
