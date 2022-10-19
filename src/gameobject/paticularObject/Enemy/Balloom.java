package gameobject.paticularObject.Enemy;

import effect.Animation;
import effect.CacheDataLoader;
import gameobject.GameWorld;
import gameobject.paticularObject.ParticularObject;

import java.awt.*;

public class Balloom extends ParticularObject {
    private Animation lballoom, balloomdie;


    public Balloom(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, 48, 48, 1, gameWorld);
        setDamage(1);
        setState(ALIVE);
        lballoom = CacheDataLoader.getInstance().getAnimation("lballom");
        balloomdie = CacheDataLoader.getInstance().getAnimation("balloomdie");
    }

    @Override
    public void attack() {
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle bound = getBoundForCollisionWithMap();
        return bound;
    }

    @Override
    public void draw(Graphics2D g2) {
        switch (getState()) {
            case ALIVE:
                lballoom.Update(System.nanoTime());
                lballoom.draw((int) (getPosX() - getGameWorld().camera.getPosX() - getWidth()/2) + 10,
                        (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight()/2) + 10, g2);
                drawBoundForCollisionWithEnemy(g2);
                break;
            case BEHURT:
                balloomdie.Update(System.nanoTime());
                balloomdie.draw((int) (getPosX() - getGameWorld().camera.getPosX() - getWidth()/2) + 10,
                        (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight()/2) + 10, g2);
                break;
        }
    }

    @Override
    public void Update() {
        super.Update();
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
        if (object.getTeamType() == ParticularObject.LEAGUE_TEAM && object.getState() == ALIVE) {
            object.setState(BEHURT);
            object.setTimeStartBeHurt(System.nanoTime());
        }
    }
}
