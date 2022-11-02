package gameobject.GameFuncion;

import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameFuncion.Camera;
import gameobject.GameObject;
import gameobject.GameWorld;

import java.awt.*;

/**
 * Ve background cua game len man hinh => bao gom wall va glass.
 */
public class BackgroundMap extends GameObject {
    public String[] map;
    public int tileSize;
    FrameImage glass = new FrameImage();
    FrameImage wall = new FrameImage();

    public BackgroundMap(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        map = CacheDataLoader.getInstance().getPhys_map(1);
        tileSize = 48;

        glass = CacheDataLoader.getInstance().getFrameImage("glass");
        wall = CacheDataLoader.getInstance().getFrameImage("wall");
    }

    @Override
    public void Update() {
    }

    public void draw(Graphics2D g2) {
        Camera camera = getGameWorld().camera;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length(); j++) {
                g2.drawImage(glass.getImage(), (int) getPosX() + j * tileSize - (int) camera.getPosX(),
                            (int) getPosY() + i * tileSize - (int) camera.getPosY(),tileSize,tileSize, null);
            }
        }
    }

    public void setMap(int lever) {
        this.map = CacheDataLoader.getInstance().getPhys_map(lever);
    }
}
