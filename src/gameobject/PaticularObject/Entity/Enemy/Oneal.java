package gameobject.PaticularObject.Entity.Enemy;

import effect.CacheDataLoader;
import gameobject.GameWorld;

import java.util.Random;

/**
 * Quái với thuật toán AI.
 */
public class Oneal extends Enemy {
    public Oneal(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY,  gameWorld);
        setDirection(DOWN_DIR);
        setRunSpeed(1);

        left = CacheDataLoader.getInstance().getAnimation("lOneal");
        right = CacheDataLoader.getInstance().getAnimation("rOneal");
        die = CacheDataLoader.getInstance().getAnimation("Onealdie");
        stand = CacheDataLoader.getInstance().getAnimation("fOneal");
    }

    @Override
    public void Update() {
        super.Update();
    }

    @Override
    public void run() {
        ai.AI_Pursue();
    }

    @Override
    public void stopRun() {
    }

    @Override
    public void attack() {
    }
}
