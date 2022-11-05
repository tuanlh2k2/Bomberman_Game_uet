package gameobject.PaticularObject.Tile;

import effect.Animation;
import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Entity;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;

public class Brick extends ParticularObject {
    private FrameImage brick;
    private Animation brickdie;
    public Brick(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, 48, 48, 1, gameWorld);
        setRigid(true);
        setTeamType(NO_TEAM);
        brick = CacheDataLoader.getInstance().getFrameImage("brick");
        brickdie = CacheDataLoader.getInstance().getAnimation("brickdie");
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
        if (getState() == ALIVE) {
            g2.drawImage(brick.getImage(), (int) (getPosX() - getWidth()/2 - getGameWorld().camera.getPosX()),
                    (int) (getPosY() - getHeight()/2 - getGameWorld().camera.getPosY()), 48,48, null);
        } else if (getState() == BEHURT) {
            brickdie.Update(System.nanoTime());
            brickdie.draw((int) (getPosX() - getWidth()/2 - getGameWorld().camera.getPosX()) + 10,(int) (getPosY() - getHeight()/2 - getGameWorld().camera.getPosY()) + 10, g2);
        }
//        drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void Update() {
        super.Update();
    }

    @Override
    public boolean getRigid() {
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
        if (object != null) {
            if (object instanceof Entity) {
                if (((Entity) object).getThroughBrick() == true) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }
}
