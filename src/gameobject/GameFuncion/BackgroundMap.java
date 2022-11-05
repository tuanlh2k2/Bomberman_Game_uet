package gameobject.GameFuncion;

import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameFuncion.Camera;
import gameobject.GameObject;
import gameobject.GameWorld;
import userinterface.GameFrame;

import java.awt.*;

/**
 * Ve background cua game len man hinh => bao gom wall va glass.
 */
public class BackgroundMap extends GameObject {
    public String[] map;
    public int tileSize;
    FrameImage grass = new FrameImage();

    public BackgroundMap(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        map = CacheDataLoader.getInstance().getPhys_map(1);
        tileSize = 48;
        grass = CacheDataLoader.getInstance().getFrameImage("grass");
    }

    @Override
    public void Update() {
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(grass.getImage(), 0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, null);
    }

    public void setMap(int lever) {
        this.map = CacheDataLoader.getInstance().getPhys_map(lever);
    }
}
