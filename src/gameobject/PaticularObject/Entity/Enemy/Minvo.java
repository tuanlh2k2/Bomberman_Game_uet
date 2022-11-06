package gameobject.PaticularObject.Entity.Enemy;

import effect.CacheDataLoader;
import gameobject.GameWorld;

import java.util.Random;

/**
 * ..........
 */
public class Minvo extends Enemy {

    private long setTimeReset;
    private Integer[] direcsion = new Integer[4];
    private int length;
    private String[] map;
    private int heart = 2;

    public Minvo(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        setDirection(TOP_DIR);
        setRunSpeed(2);

        map = getGameWorld().backgroundMap.map;
        left = CacheDataLoader.getInstance().getAnimation("lMinvo");
        right = CacheDataLoader.getInstance().getAnimation("rMinvo");
        die = CacheDataLoader.getInstance().getAnimation("Minvodie");
        stand = CacheDataLoader.getInstance().getAnimation("fMinvo");

    }

    public void Update() {
        super.Update();
        if (getState() == DEATH) {
            heart--;
            setState(ALIVE);
        }
        if (heart == 0 && getState() == ALIVE) {
            setState(DEATH);
        }
    }

    public void run() {
        ai.AI_Pursue();
    }

    @Override
    public void stopRun() {
    }

    @Override
    public void attack() {
    }

    public String[] getMap() {
        return map;
    }

    public void setMap(String[] map) {
        this.map = map;
    }
}
