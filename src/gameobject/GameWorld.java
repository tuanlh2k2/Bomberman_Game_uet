package gameobject;

import userinterface.GameFrame;

import java.awt.*;

public class GameWorld {
    public Player player;
    public PhysicalMap physicalMap;

    public GameWorld() {
        physicalMap = new PhysicalMap(0, 0);
        player = new Player(300, 300, 48, 48);
    }

    public void Update() {
        player.update();
    }

    public void Render(Graphics2D g2) {
        physicalMap.draw(g2);
        player.draw(g2);
    }

}
