package gameobject.PaticularObject.Entity.Enemy;

import effect.Animation;
import effect.CacheDataLoader;
import gameobject.GameWorld;
import gameobject.PaticularObject.Tile.Brick;


import java.util.Random;

/**
 * Quái sinh tường.
 */
public class Kondoria extends Enemy {
    public Kondoria(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY,  gameWorld);
        setDirection(DOWN_DIR);
        setRunSpeed(1);

        left = CacheDataLoader.getInstance().getAnimation("lKondoria");
        right = CacheDataLoader.getInstance().getAnimation("rKondoria");
        die = CacheDataLoader.getInstance().getAnimation("Kondoriadie");
        stand = CacheDataLoader.getInstance().getAnimation("fKondoria");
    }

    @Override
    public void Update() {
        super.Update();
        if (getState() == DEATH) {
            Brick brick = new Brick(getPosX(), getPosY(), getGameWorld());
            getGameWorld().particularObjectManager.addObject(brick);
        }
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