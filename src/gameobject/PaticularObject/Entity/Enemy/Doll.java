package gameobject.PaticularObject.Entity.Enemy;

import effect.CacheDataLoader;
import gameobject.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class Doll extends Enemy {
    private long setTimeReset;
    private List<Integer> listdirection = new ArrayList<>();
    public Doll(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY,  gameWorld);
        setNewDirection();

        left = CacheDataLoader.getInstance().getAnimation("lDoll");
        right = CacheDataLoader.getInstance().getAnimation("rDoll");
        die = CacheDataLoader.getInstance().getAnimation("Dolldie");
        stand = CacheDataLoader.getInstance().getAnimation("fDoll");
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
            setDirection(this.listdirection.get(0));
            resetDirection();
        }
        if (getDirection() == LEFT_DIR) {
            setSpeedX(-2);
        } else if (getDirection() == RIGHT_DIR) {
            setSpeedX(2);
        } else if (getDirection() == TOP_DIR) {
            setSpeedY(-2);
        } else if (getDirection() == DOWN_DIR) {
            setSpeedY(2);
        }
    }
    @Override
    public void stopRun() {
    }

    // Khoi tao mang quy dinh huong cho doll.
    public void setNewDirection() {
        listdirection.add(0);
        listdirection.add(2);
        listdirection.add(1);
        listdirection.add(3);
    }

    // cai lai huong cho doll khi no cham tuong.

    public void resetDirection() {
        int tmp = this.listdirection.get(3);
        this.listdirection.remove(3);
        this.listdirection.add(0, tmp);
    }
}
