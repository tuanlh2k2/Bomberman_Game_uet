package gameobject;

import gameobject.paticularObject.Entity.Player;
import userinterface.GameFrame;

import java.awt.*;

public class GameWorld {
    public Player player;
    public PhysicalMap physicalMap;
    public Camera camera;

    public GameWorld() {
        physicalMap = new PhysicalMap(0, 0, this);
        player = new Player(72, 72, this);
        camera = new Camera(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
    }

    public void Update() {
        player.Update();
        camera.Update();
    }

    public void Render(Graphics2D g2) {
        physicalMap.draw(g2);
        player.draw(g2);
    }
}
