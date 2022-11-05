package gameobject.PaticularObject.Weapon;

import effect.Animation;
import effect.CacheDataLoader;
import gameobject.GameWorld;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;

public class Flame extends ParticularObject {
    protected int directionUp = 0;
    protected int directionDown = 1;
    protected int directionLeft = 2;
    protected int directionRight = 3;
    protected int directionPos = 4;
    protected int direction;
    protected Bomb bomb;
    private Animation firetop, firedown, fireleft, fireright, exbomb;
    public Flame(double posX, double posY, Bomb bomb, GameWorld gameWorld) {
        super(posX, posY, 48, 48, 1, gameWorld);
        this.bomb = bomb;

        exbomb = CacheDataLoader.getInstance().getAnimation("exbomb");
        firetop = CacheDataLoader.getInstance().getAnimation("firetop");
        firedown = CacheDataLoader.getInstance().getAnimation("firedown");
        fireleft = CacheDataLoader.getInstance().getAnimation("fireleft");
        fireright = CacheDataLoader.getInstance().getAnimation("fireright");
    }

    public void addFrame(Rectangle rectangle) {
        setPosX(rectangle.x);
        setPosY(rectangle.y);
        setWidth(rectangle.width);
        setHeight(rectangle.height);
    }

    @Override
    public void attack() {
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle bound = new Rectangle((int) getPosX(), (int) getPosY(), (int) getWidth(), (int) getHeight());
        return bound;
    }

    @Override
    public void draw(Graphics2D g2) {
//        drawBoundForCollisionWithEnemy(g2);
        if (direction == directionPos) {
            exbomb.Update(System.nanoTime());
            exbomb.draw((int) (getPosX() + 10 - getGameWorld().camera.getPosX()),
                    (int) (getPosY() + 10 - getGameWorld().camera.getPosY()), (int) getWidth(), (int) getHeight(), g2);
        } else if (direction == directionUp) {
            firetop.Update(System.nanoTime());
            firetop.draw((int) (getPosX() + 10 - getGameWorld().camera.getPosX()),
                    (int) (getPosY() + 24 - getGameWorld().camera.getPosY()), (int) getWidth(), (int) getHeight(), g2);
        } else if (direction == directionDown) {
            firedown.Update(System.nanoTime());
            firedown.draw((int) (getPosX() + 10 - getGameWorld().camera.getPosX()),
                    (int) (getPosY() + 10 - getGameWorld().camera.getPosY()), (int) getWidth(), (int) getHeight(), g2);
        } else if (direction == directionLeft) {
            fireleft.Update(System.nanoTime());
            fireleft.draw((int) (getPosX() + 24 - getGameWorld().camera.getPosX()),
                    (int) (getPosY() + 10 - getGameWorld().camera.getPosY()), (int) getWidth(), (int) getHeight(), g2);
        } else if (direction == directionRight) {
            fireright.Update(System.nanoTime());
            fireright.draw((int) (getPosX() + 10 - getGameWorld().camera.getPosX()),
                    (int) (getPosY() + 10 - getGameWorld().camera.getPosY()), (int) getWidth(), (int) getHeight(), g2);
        }
    }

    @Override
    public void Update() {
        ParticularObject object = getGameWorld().particularObjectManager.checkCollisionWithRect(getBoundForCollisionWithEnemy());
        if (object != null) {
            if (object.getTeamType() != WEAPON_TEAM && object.getState() == ALIVE) {
                object.setTimeStartBeHurt(System.currentTimeMillis());
                object.setBlood(object.getBlood() - 1);
                object.setState(BEHURT);
            }
        }
        if (bomb.getState() == DEATH) {
            setState(DEATH);
        }
    }
}
