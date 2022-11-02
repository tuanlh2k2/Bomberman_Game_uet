package gameobject.PaticularObject.Tile;

import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameWorld;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;

public class Wall extends ParticularObject {
    private FrameImage wall = CacheDataLoader.getInstance().getFrameImage("wall");
    public Wall(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, 48, 48, Integer.MAX_VALUE, gameWorld);
        setTeamType(WEAPON_TEAM);
        setRigid(true);
    }

    @Override
    public void attack() {
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle bound = getBoundForCollisionWithMap();
        return bound;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(wall.getImage(),  (int) (getPosX() - getWidth()/2 - getGameWorld().camera.getPosX()),
                (int) (getPosY() - getHeight()/2 - getGameWorld().camera.getPosY()), 48,48, null);
//        drawBoundForCollisionWithEnemy(g2);
    }
}
