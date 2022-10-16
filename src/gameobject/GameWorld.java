package gameobject;

import gameobject.paticularObject.Entity.Player;

import java.awt.*;

public class GameWorld {
    public Player player;
    public PhysicalMap physicalMap;

    public GameWorld() {
        physicalMap = new PhysicalMap(0, 0, this);
        player = new Player(72, 72, this);
    }

    public void Update() {
        player.Update();
    }

    public void Render(Graphics2D g2) {
        physicalMap.draw(g2);
        player.draw(g2);
    }
}
