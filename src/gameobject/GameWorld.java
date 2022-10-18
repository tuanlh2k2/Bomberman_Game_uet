package gameobject;

import gameobject.paticularObject.Entity.Player;
import gameobject.paticularObject.Weapon.WeaponManager;
import userinterface.GameFrame;

import java.awt.*;

public class GameWorld {
    public Player player;
    public PhysicalMap physicalMap;
    public BackgroundMap backgroundMap;
    public WeaponManager weaponManager;
    public Camera camera;

    public GameWorld() {
        physicalMap = new PhysicalMap(0, 0, this);
        backgroundMap = new BackgroundMap(0, 0, this);
        player = new Player(72, 72, this);
        camera = new Camera(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
        weaponManager = new WeaponManager(this);
    }

    public void Update() {
        player.Update();
        camera.Update();
        weaponManager.UpdateObjects();
    }

    public void Render(Graphics2D g2) {
        //physicalMap.draw(g2);
        backgroundMap.draw(g2);
        player.draw(g2);
        weaponManager.draw(g2);
    }
}
