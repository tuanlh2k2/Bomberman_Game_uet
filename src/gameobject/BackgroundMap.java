package gameobject;

import effect.CacheDataLoader;
import effect.FrameImage;

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
        map = CacheDataLoader.getInstance().getPhys_map();
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
                if (map[i].charAt(j) == '#') {
                    g2.drawImage(wall.getImage(), (int) getPosX() + j * tileSize - (int) camera.getPosX(),
                            (int) getPosY() + i * tileSize - (int) camera.getPosY(),tileSize,tileSize, null);
                } else {
                    g2.drawImage(glass.getImage(), (int) getPosX() + j * tileSize - (int) camera.getPosX(),
                            (int) getPosY() + i * tileSize - (int) camera.getPosY(),tileSize,tileSize, null);
                }
            }
        }
    }

}
