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

public class Balloom extends Enemy {
    private long setTimeReset;
    private List<Integer> listdirection = new ArrayList<>();
    public Balloom(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY,  gameWorld);
        setNewDirection();

        left = CacheDataLoader.getInstance().getAnimation("lballom");
        right = CacheDataLoader.getInstance().getAnimation("rballom");
        die = CacheDataLoader.getInstance().getAnimation("balloomdie");
        stand = CacheDataLoader.getInstance().getAnimation("fballom");
    }

    @Override
    public void attack() {
    }
    @Override
    public void Update() {
        super.Update();
    }

    public void run() {
        if (getHaveCollision() == true || System.currentTimeMillis()- setTimeReset > 6000 ) {
            setTimeReset = System.currentTimeMillis();
            setDirection(this.listdirection.get(3));
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
