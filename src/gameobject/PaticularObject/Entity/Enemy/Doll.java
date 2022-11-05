package gameobject.PaticularObject.Entity.Enemy;

import effect.CacheDataLoader;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Enemy.AI.AI;

import java.util.ArrayList;
import java.util.List;

/**
 * Quái xuyên gạch.
 */
public class Doll extends Enemy {
    private long setTimeReset;
    private AI ai;
    private List<Integer> listdirection = new ArrayList<>();
    public Doll(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY,  gameWorld);
        setThroughBrick(true);

        ai = new AI(this, getGameWorld());
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
        ai.AI_Pursue();
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
}
