package gameobject.paticularObject.Entity.Enemy;

import effect.Animation;
import effect.CacheDataLoader;
import gameobject.GameWorld;
import gameobject.paticularObject.Entity.Entity;
import gameobject.paticularObject.ParticularObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Balloom extends Entity {
    private Animation lballoom, rballoom, balloomdie, fballoom;
    private long setTimeReset;
    private List<Integer> listdirection = new ArrayList<>();
    public Balloom(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, 48, 48, 1, gameWorld);
        setNewDirection();
        setRigid(false);
        lballoom = CacheDataLoader.getInstance().getAnimation("lballom");
        rballoom = CacheDataLoader.getInstance().getAnimation("rballom");
        balloomdie = CacheDataLoader.getInstance().getAnimation("balloomdie");
        fballoom = CacheDataLoader.getInstance().getAnimation("fballom");
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
        switch (getState()) {
            case ALIVE:
                if (getDirection() == LEFT_DIR) {
                    lballoom.Update(System.nanoTime());
                    lballoom.draw((int) (getPosX() - getGameWorld().camera.getPosX() - getWidth()/2) + 10,
                            (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight()/2) + 10, g2);
                    drawBoundForCollisionWithEnemy(g2);
                } else if (getDirection() == RIGHT_DIR) {
                    rballoom.Update(System.nanoTime());
                    rballoom.draw((int) (getPosX() - getGameWorld().camera.getPosX() - getWidth()/2) + 10,
                            (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight()/2) + 10, g2);
                    drawBoundForCollisionWithEnemy(g2);
                } else {
                    fballoom.Update(System.nanoTime());
                    fballoom.draw((int) (getPosX() - getGameWorld().camera.getPosX() - getWidth()/2) + 10,
                            (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight()/2) + 10, g2);
                    drawBoundForCollisionWithEnemy(g2);
                }
                break;
            case BEHURT:
                balloomdie.Update(System.nanoTime());
                balloomdie.draw((int) (getPosX() - getGameWorld().camera.getPosX() - getWidth()/2) + 10,
                        (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight()/2) + 10, g2);
                break;
        }
    }

    @Override
    public void Update() {
        super.Update();
        run();
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
        if (object != null) {
            if (object.getTeamType() == ParticularObject.LEAGUE_TEAM && object.getState() == ALIVE) {
                object.setState(BEHURT);
                object.setTimeStartBeHurt(System.nanoTime());
            }
        }
    }

    public void run() {
        if ( getHaveCollision() == true || System.nanoTime() - setTimeReset > 500 * 1000000000 ) {
            setTimeReset = System.nanoTime();
            setDirection(this.listdirection.get(3));
            System.out.println(getDirection());
            resetDirection();
        }
        if (getDirection() == LEFT_DIR) {
            setSpeedX(-1);
        } else if (getDirection() == RIGHT_DIR) {
            setSpeedX(1);
        } else if (getDirection() == TOP_DIR) {
            setSpeedY(-1);
        } else if (getDirection() == DOWN_DIR) {
            setSpeedY(1);
        }
    }
    @Override
    public void stopRun() {
    }

    // Khoi tao mang quy dinh huong cho balloom.
    public void setNewDirection() {
        listdirection.add(0);
        listdirection.add(2);
        listdirection.add(1);
        listdirection.add(3);
    }

    // cai lai huong cho balloom khi no cham tuong.

    public void resetDirection() {
        int tmp = this.listdirection.get(3);
        this.listdirection.remove(3);
        this.listdirection.add(0, tmp);
    }
}
