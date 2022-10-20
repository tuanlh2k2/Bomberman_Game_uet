package gameobject.paticularObject.Weapon;

import effect.Animation;
import effect.CacheDataLoader;
import gameobject.GameWorld;
import gameobject.paticularObject.ParticularObject;

import java.awt.*;

public class Bomb extends Weapon {
    private Animation bomb, exbomb;
    private Animation firetop, firedown, fireleft, fireright;
    public long timeBegin;
    private long timeHT;
    public Bomb(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, 48, 48, 1, gameWorld);
        setRigid(true);
        setTeamType(NO_TEAM);
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
        Rectangle bound = getBoundForCollisionWithMap();
        return bound;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (getState() == ALIVE) {
            bomb.Update(System.nanoTime());
            bomb.draw((int) (getPosX() - getGameWorld().camera.getPosX() + 10 - getWidth()/2),
                    (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight() /2 + 10), g2);
        } else if (getState() == BEHURT) {
            exbomb.Update(System.nanoTime());
            firetop.Update(System.nanoTime());
            firedown.Update(System.nanoTime());
            fireleft.Update(System.nanoTime());
            fireright.Update(System.nanoTime());
            exbomb.draw((int) (getPosX() - getGameWorld().camera.getPosX() - getWidth()/2 + 10),
                    (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight()/2 + 10), g2);

            if (getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionTop()) == null) {
                firetop.draw((int) (getPosX() - getGameWorld().camera.getPosX() + 10 - getWidth()/2),
                        (int) (getPosY() - getGameWorld().camera.getPosY() - 38 - getHeight()/2), g2);
            }
            if (getGameWorld().physicalMap.haveCollisionWithBottom(getBoundForCollisionDown()) == null
                    && getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionDown()) == null) {
                firedown.draw((int) (getPosX() - getGameWorld().camera.getPosX() + 10 - getWidth()/2),
                        (int) (getPosY() -  getGameWorld().camera.getPosY() + 58 - getHeight()/2), g2);
            }
            if (getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionLeft()) == null) {
                fireleft.draw((int) (getPosX() - getGameWorld().camera.getPosX() - 38 - getWidth()/2),
                        (int) (getPosY() - getGameWorld().camera.getPosY() + 10 - getHeight()/2), g2);
            }
            if (getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionRight()) == null) {
                fireright.draw((int) (getPosX() - getGameWorld().camera.getPosX() + 58 - getWidth()/2),
                        (int) (getPosY() - getGameWorld().camera.getPosY() + 10 - getHeight()/2), g2);
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
            getGameWorld().player.setShooting(false);
            setState(DEATH);
        }
        if (getState() == BEHURT) {
            ParticularObject checkBomb = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
            ParticularObject checkTop = getGameWorld().particularObjectManager.checkCollisionWithFire(this, getBoundForCollisionTop());
            ParticularObject checkBottom = getGameWorld().particularObjectManager.checkCollisionWithFire(this, getBoundForCollisionDown());
            ParticularObject checkLeft = getGameWorld().particularObjectManager.checkCollisionWithFire(this, getBoundForCollisionLeft());
            ParticularObject checkRight = getGameWorld().particularObjectManager.checkCollisionWithFire(this, getBoundForCollisionRight());
            if (checkBomb != null) {
                checkBomb.setState(BEHURT);
                checkBomb.setTimeStartBeHurt(System.nanoTime());
            }
            if (checkTop != null) {
                checkTop.setState(BEHURT);
                checkTop.setTimeStartBeHurt(System.nanoTime());
            }
            if (checkBottom != null) {
                checkBottom.setState(BEHURT);
                checkBottom.setTimeStartBeHurt(System.nanoTime());
            }
            if (checkLeft != null) {
                checkLeft.setState(BEHURT);
                checkLeft.setTimeStartBeHurt(System.nanoTime());
            }
            if (checkRight != null) {
                checkRight.setState(BEHURT);
                checkRight.setTimeStartBeHurt(System.nanoTime());
            }
        }
    }

    @Override
    public Rectangle getBoundForCollisionTop() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - getWidth()/2);
        bound.y = (int) (getPosY() - getHeight()/2 - 48);
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    @Override
    public Rectangle getBoundForCollisionLeft() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - 48 - getWidth()/2);
        bound.y = (int) (getPosY() - getHeight()/2);
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    @Override
    public Rectangle getBoundForCollisionDown() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - getWidth()/2);
        bound.y = (int) (getPosY() + 48 - getHeight()/2);
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    @Override
    public Rectangle getBoundForCollisionRight() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() + 48 - getWidth()/2);
        bound.y = (int) (getPosY() - getHeight()/2);
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }
}
