package gameobject;

import effect.CacheDataLoader;

import java.awt.*;

public class PhysicalMap {
    public String[] phys_map;
    private int tileSize;
    public double x, y;

    public PhysicalMap(double x, double y) {
        this.x = x;
        this.y = y;
        this.tileSize = 48;
        phys_map = CacheDataLoader.getInstance().getPhys_map();
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.green);
        for (int i = 0; i < phys_map.length; i++) {
            for (int j = 0; j < phys_map[i].length(); j++) {
                if (phys_map[i].charAt(j) != ' ') {
                    g2.fillRect((int) x + j * tileSize, (int) y + i * tileSize, tileSize, tileSize);
                }
            }
        }
    }
}
