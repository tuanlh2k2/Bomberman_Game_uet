package gameobject.paticularObject.Weapon;

import effect.Animation;
import effect.CacheDataLoader;
import gameobject.GameWorld;

import java.awt.*;

public class Bomb extends Weapon {
    private Animation bomb, exbomb;
    private Animation firetop, firedown, fireleft, fireright;
    public long timeBegin;
    private String direction = "";
    private long timeHT;
    public Bomb(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, 48, 48, 1, gameWorld);
        bomb = CacheDataLoader.getInstance().getAnimation("bomb");
        exbomb = CacheDataLoader.getInstance().getAnimation("exbomb");
        firetop = CacheDataLoader.getInstance().getAnimation("firetop");
        firedown = CacheDataLoader.getInstance().getAnimation("firedown");
        fireleft = CacheDataLoader.getInstance().getAnimation("fireleft");
        fireright = CacheDataLoader.getInstance().getAnimation("fireright");

        setState(ALIVE);
        timeBegin = System.nanoTime();
    }

    @Override
    public void attack() {
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX());
        bound.y = (int) (getPosY());
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (getState() == ALIVE) {
            bomb.Update(System.nanoTime());
            bomb.draw((int) (getPosX() - getGameWorld().camera.getPosX() + 10), (int) getPosY() - (int) getGameWorld().camera.getPosY() + 10, g2);
        } else if (getState() == BEHURT) {
            exbomb.Update(System.nanoTime());
            firetop.Update(System.nanoTime());
            firedown.Update(System.nanoTime());
            fireleft.Update(System.nanoTime());
            fireright.Update(System.nanoTime());
            exbomb.draw((int) (getPosX() - getGameWorld().camera.getPosX() + 10), (int) getPosY() - (int) getGameWorld().camera.getPosY() + 10, g2);

            if (getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionTop()) == null) {
                firetop.draw((int) (getPosX() - getGameWorld().camera.getPosX() + 10), (int) getPosY() - (int) getGameWorld().camera.getPosY() - 38, g2);
            }
            if (getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionDown()) == null) {
                firedown.draw((int) (getPosX() - getGameWorld().camera.getPosX() + 10), (int) getPosY() - (int) getGameWorld().camera.getPosY() + 58, g2);
            }
            if (getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionLeft()) == null) {
                fireleft.draw((int) (getPosX() - getGameWorld().camera.getPosX() - 38), (int) getPosY() - (int) getGameWorld().camera.getPosY() + 10, g2);
            }
            if (getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionRight()) == null) {
                fireright.draw((int) (getPosX() - getGameWorld().camera.getPosX() + 58), (int) getPosY() - (int) getGameWorld().camera.getPosY() + 10, g2);
            }
        }
        drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void Update() {
        timeHT = System.nanoTime();
        if (timeHT - timeBegin > 500 * 1000000000 && getState() == ALIVE) {
            setState(BEHURT);
            this.timeBegin = timeHT;
        } else if (timeHT - timeBegin > 500 * 1000000000 && getState() == BEHURT) {
            setState(DEATH);
        }
    }

    @Override
    public Rectangle getBoundForCollisionTop() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX());
        bound.y = (int) (getPosY() - 48);
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    @Override
    public Rectangle getBoundForCollisionLeft() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - 48);
        bound.y = (int) (getPosY());
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    @Override
    public Rectangle getBoundForCollisionDown() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX());
        bound.y = (int) (getPosY() + 48);
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    @Override
    public Rectangle getBoundForCollisionRight() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() + 48);
        bound.y = (int) (getPosY());
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }
}
