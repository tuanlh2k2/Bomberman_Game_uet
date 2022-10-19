package gameobject;

import gameobject.paticularObject.Enemy.Balloom;
import gameobject.paticularObject.Entity.Player;
import gameobject.paticularObject.ParticularObject;
import gameobject.paticularObject.ParticularObjectManager;
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
        ParticularObject balloom = new Balloom(72, 240, this);
        balloom.setDirection(ParticularObject.LEFT_DIR);
        balloom.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(balloom);
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
