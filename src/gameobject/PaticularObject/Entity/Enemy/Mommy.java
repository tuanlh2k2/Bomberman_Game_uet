package gameobject.PaticularObject.Entity.Enemy;

import effect.CacheDataLoader;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Enemy.AI.AI;
import gameobject.PaticularObject.ParticularObject;

/**
 * Quái có thể sinh ra quái khác.
 */
public class Mommy extends Enemy {
    private long timeReproduction = System.currentTimeMillis(); // thời gian sinh sản.
    public Mommy(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        setRunSpeed(1);

        left = CacheDataLoader.getInstance().getAnimation("lMommy");
        right = CacheDataLoader.getInstance().getAnimation("rMommy");
        die = CacheDataLoader.getInstance().getAnimation("Mommydie");
        stand = CacheDataLoader.getInstance().getAnimation("fMommy");
    }

    @Override
    public void run() {
        ai.AI_Basic();
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
            ParticularObject object = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
            if (object == null) {
                timeReproduction = System.currentTimeMillis();
                Egg egg = new Egg(getPosX(), getPosY(), getGameWorld());
                getGameWorld().particularObjectManager.addObject(egg);
            }
        }
    }
}
