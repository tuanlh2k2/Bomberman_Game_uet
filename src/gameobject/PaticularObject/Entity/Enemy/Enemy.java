package gameobject.PaticularObject.Entity.Enemy;

import effect.Animation;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Entity;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;

public abstract class Enemy extends Entity {
    protected Animation left, right, die, stand;

    public Enemy(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, 48, 48, 1, gameWorld);
        setRigid(false);
    }

    @Override
    public void Update() {
        super.Update();
        run();
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
        if (object != null && getState() == ALIVE) {
            if (object.getTeamType() == ParticularObject.LEAGUE_TEAM && object.getState() == ALIVE) {
                object.setState(BEHURT);
                object.setBlood(object.getBlood() - 1);
                object.setTimeStartBeHurt(System.currentTimeMillis());
            }
        }
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
                if (getDirection() == LEFT_DIR) {
                    left.Update(System.nanoTime());
                    left.draw((int) (getPosX() - getGameWorld().camera.getPosX() - getWidth()/2) + 10,
                            (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight()/2) + 10, g2);
//                    drawBoundForCollisionWithEnemy(g2);
                } else if (getDirection() == RIGHT_DIR) {
                    right.Update(System.nanoTime());
                    right.draw((int) (getPosX() - getGameWorld().camera.getPosX() - getWidth()/2) + 10,
                            (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight()/2) + 10, g2);
//                      drawBoundForCollisionWithEnemy(g2);
                } else {
                    stand.Update(System.nanoTime());
                    stand.draw((int) (getPosX() - getGameWorld().camera.getPosX() - getWidth()/2) + 10,
                            (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight()/2) + 10, g2);
//                    drawBoundForCollisionWithEnemy(g2);
                }
                break;
            case BEHURT:
                die.Update(System.nanoTime());
                die.draw((int) (getPosX() - getGameWorld().camera.getPosX() - getWidth()/2) + 10,
                        (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight()/2) + 10, g2);
//                drawBoundForCollisionWithEnemy(g2);
                break;
        }
    }
}
