package gameobject.PaticularObject.Entity.Enemy;

import effect.CacheDataLoader;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Enemy.AI.AI;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;
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
        setRunSpeed(2);

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
        onPath = true;
    }

    public void run() {
        if (onPath == true) {
            int goalPosX = (int) (getGameWorld().player.getPosX()/48);
            int goalPosY = (int) (getGameWorld().player.getPosY()/48);
            ai.searchPath(goalPosX, goalPosY);
        } else {
            ai.AI_Basic();
        }
    }
    @Override
    public void stopRun() {
    }
}
