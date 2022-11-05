package gameobject.PaticularObject.Entity.Enemy;

import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameWorld;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Trứng sau 5 s sẽ nở ra ballom va oneal.
 */
public class Egg extends ParticularObject {
    private long eggHatch = System.currentTimeMillis(); // thời gian nở trứng.
    FrameImage egg;

    public Egg(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, 36, 36, 1, gameWorld);
        egg = CacheDataLoader.getInstance().getFrameImage("egg");
    }

    @Override
    public void attack() {

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(egg.getImage(), (int) (getPosX() - getWidth()/2 - getGameWorld().camera.getPosX()),
                (int) (getPosY() - getHeight()/2 - getGameWorld().camera.getPosY()), 36,36, null);
    }

    @Override
    public void Update() {
        super.Update();
        if (System.currentTimeMillis() - eggHatch > 500) {
            setRigid(true);
        }
        if (System.currentTimeMillis() - eggHatch > 5000) {
            ParticularObject baloom = new Balloom(getPosX(), getPosY(), getGameWorld());
            ParticularObject oneal = new Oneal (getPosX(), getPosY(), getGameWorld());
            getGameWorld().particularObjectManager.addObject(baloom);
            getGameWorld().particularObjectManager.addObject(oneal);
            setState(DEATH);
        }
    }
}
