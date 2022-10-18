package gameobject;

import effect.CacheDataLoader;
import effect.FrameImage;

import java.awt.*;

public class PhysicalMap extends GameObject {
    public String[] phys_map;
    FrameImage glass = CacheDataLoader.getInstance().getFrameImage("glass");
    private int tileSize;

    public PhysicalMap(double x, double y, GameWorld gameWorld) {
        super(x, y, gameWorld);
        this.tileSize = 48;
        phys_map = CacheDataLoader.getInstance().getPhys_map();

    }

    public void draw(Graphics2D g2) {
        Camera camera = getGameWorld().camera;
        for (int i = 0; i < phys_map.length; i++) {
            for (int j = 0; j < phys_map[i].length(); j++) {
                if (phys_map[i].charAt(j) == '#') {
                    g2.setColor(Color.darkGray);
                    g2.fillRect((int) getPosX() + j * tileSize - (int) camera.getPosX(),
                            (int) getPosY() + i * tileSize - (int) camera.getPosY(), tileSize, tileSize);
                }
            }
        }
    }

//     Xu ly va cham o duoi.
    public Rectangle haveCollisionWithBottom(Rectangle rect) {
        int posX1 = rect.x/ tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width) / tileSize;
        posX2 += 2;
        int posY1 = (rect.y + rect.height) / tileSize;
        if (posX1 < 0) posX1 = 0;
        if (posX2 >= phys_map[0].length()) posX2 = phys_map[0].length() - 1;
        for (int y = posY1; y < phys_map.length; y++) {
            for (int x = posX1; x <= posX2; x++) {
                if (phys_map[y].charAt(x) == '#') {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize,
                            (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rect.intersects(r)) { // kiem tra xem hai hinh chu nhat co giao nhau hay khong.
                        return r;
                    }
                }
             }
        }
        return null;
    }

    public Rectangle haveCollisionWithTop(Rectangle rect) {
        int posX1 = rect.x / tileSize;
        posX1 -= 3;
        int posX2 = (rect.x + rect.width) / tileSize;
        posX2 +=3;

        int posY = rect.y / tileSize;

        if (posX1 < 0) posX1 = 0;
        if (posX2 >= phys_map[0].length()) posX2 = phys_map[0].length() - 1;

        for (int y = posY; y >= 0; y--) {
            for (int x = posX1; x < posX2; x++) {
                if (phys_map[y].charAt(x) == '#') {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize,
                            (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rect.intersects(r)) {
                        return r;
                    }
                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithRightWall(Rectangle rect) {
        int posY1 = rect.y / tileSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height) / tileSize;
        posY2 += 2;
        int posX1 = (rect.x + rect.width) / tileSize;
        posX1 -= 1;
        int posX2 = posX1 + 3;
        if (posX1 < 0) posX1 = 0;
        if (posY1 < 0) posY1 = 0;
        if (posY2 >= phys_map.length) posY2 = phys_map.length - 1;
        if (posX2 >= phys_map[0].length()) posX2 = phys_map[0].length() - 1;

        for (int x = posX1; x < posX2; x++) {
            for (int y = posY1; y < posY2; y++) {
                if (phys_map[y].charAt(x) == '#') {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize,
                            (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rect.intersects(r)) { // Dang thieu.
                        return r;
                    }
                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithLeftWall(Rectangle rect) {
        int posY1 = rect.y / tileSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height) / tileSize;
        posY2 += 2;

        int posX1 = (rect.x + rect.width) / tileSize;
        posX1 -= 2;
        int posX2 = posX1 + 4;
        if (posY1 < 0) posY1 = 0;
        if (posY2 >= phys_map.length) posY2 = phys_map.length - 1;
        if (posX2 >= phys_map[0].length()) posX2 = phys_map[0].length() - 1;

        for (int x = posX1; x <= posX2; x++) {
            for (int y = posY1; y < posY2; y++) {
                if (phys_map[y].charAt(x) == '#') {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize,
                            (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rect.intersects(r)) {
                        return r;
                    }
                }
            }
        }
        return null;
    }
    @Override
    public void Update() {
    }
}
