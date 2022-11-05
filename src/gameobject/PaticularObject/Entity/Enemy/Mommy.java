package gameobject.PaticularObject.Entity.Enemy;

import effect.CacheDataLoader;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Enemy.AI.AI;

/**
 * Quái có thể sinh ra quái khác.
 */
public class Mommy extends Enemy {
    private long timeReproduction = System.currentTimeMillis(); // thời gian sinh sản.
    private AI ai;
    public Mommy(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        ai = new AI(this, getGameWorld());

        left = CacheDataLoader.getInstance().getAnimation("lMommy");
        right = CacheDataLoader.getInstance().getAnimation("rMommy");
        die = CacheDataLoader.getInstance().getAnimation("Mommydie");
        stand = CacheDataLoader.getInstance().getAnimation("fMommy");
    }

    @Override
    public void run() {
        ai.AI_Basic();

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

    @Override
    public void attack() {
    }

    @Override
    public void Update() {
        super.Update();
        if (System.currentTimeMillis() - timeReproduction > 10000) {
            timeReproduction = System.currentTimeMillis();
            Egg egg = new Egg(getPosX(), getPosY(), getGameWorld());

            getGameWorld().particularObjectManager.addObject(egg);
        }
    }
}
