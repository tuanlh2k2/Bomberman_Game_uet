package gameobject.PaticularObject.Entity.Enemy;

import effect.CacheDataLoader;
import gameobject.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class Balloom extends Enemy {
    private long setTimeReset;
    public Balloom(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY,  gameWorld);
        setRunSpeed(1);

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
        ai.AI_Basic();
    }
    @Override
    public void stopRun() {
    }
}
