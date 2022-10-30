package gameobject.paticularObject.Entity;

import gameobject.GameWorld;
import gameobject.paticularObject.ParticularObject;
import gameobject.paticularObject.ParticularObjectManager;

import java.awt.*;

public abstract class Entity extends ParticularObject {
    private boolean isPlantingBombs;
    private boolean haveCollision;
    private boolean checkRigid;

    public Entity(double posX, double posY, double width, double height, int blood, GameWorld gameWorld) {
        super(posX, posY, width, height, blood, gameWorld);
        setState(ALIVE);
    }

    public abstract void run(); // chay.
    public abstract void stopRun();


    public void Update() {
        super.Update();
        checkRigid = false;
        setHaveCollision(false);
        checkCollisionWithRigid();
        // Kiem tra va cham tuong.
        if (getState() == ALIVE) {
            if (getDirection() == LEFT_DIR ) {
                if (getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) == null) {
                    setPosX(getPosX() + getSpeedX());
                } else {
                    Rectangle rectLeftWall = getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                    if (getPosY() > rectLeftWall.y + rectLeftWall.height + getHeight()/2 - 20 ) {
                        setPosY(rectLeftWall.y + rectLeftWall.height + getHeight()/2);
                        setPosX(getPosX() + getSpeedX());
                    } else if (rectLeftWall.y > getPosY() + getHeight()/2 - 20) {
                        setPosY(rectLeftWall.y - getHeight()/2);
                    } else {
                        setPosX(rectLeftWall.x + rectLeftWall.width + getWidth() / 2);
                        setHaveCollision(true);
                    }
                }
            } else if (getDirection() == RIGHT_DIR ) {
                if (getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) == null) {
                    setPosX(getPosX() + getSpeedX());
                } else {
                    Rectangle rectRightWall = getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                    if (getPosY() - getHeight()/2 + 20 > rectRightWall.y + rectRightWall.height) {
                        setPosY(rectRightWall.y + rectRightWall.height + getHeight()/2);
                        setPosX(getPosX() + getSpeedX());
                    } else if (rectRightWall.y > getPosY() + getHeight()/2 - 20) {
                        setPosY(rectRightWall.y - getHeight()/2);
                        setPosX(getPosX() + getSpeedX());
                    } else {
                        setPosX(rectRightWall.x - getWidth() / 2);
                        setHaveCollision(true);
                    }
                }
            } else if (getDirection() == TOP_DIR ) {
                if (getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionWithMap()) == null) {
                    setPosY(getPosY() + getSpeedY());
                } else {
                    Rectangle rectTopWall = getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionWithMap());
                    if (getPosX() - getWidth()/2 + 20 > rectTopWall.x + rectTopWall.width) {
                        setPosX(rectTopWall.x + rectTopWall.width + getWidth()/2);
                    } else if (getPosX() + getWidth()/2 - 20 < rectTopWall.x) {
                        setPosX(rectTopWall.x - getWidth()/2);
                    } else {
                        setPosY(rectTopWall.y + getHeight()/2 + rectTopWall.height);
                        setHaveCollision(true);
                    }
                }
            } else if (getDirection() == DOWN_DIR ) {
                if (getGameWorld().physicalMap.haveCollisionWithBottom(getBoundForCollisionWithMap()) == null) {
                    setPosY(getPosY() + getSpeedY());
                } else {
                    Rectangle rectBottomWall = getGameWorld().physicalMap.haveCollisionWithBottom(getBoundForCollisionWithMap());
                    if (getPosX() - getWidth()/2 + 20 > rectBottomWall.x + rectBottomWall.width) {
                        setPosX(rectBottomWall.x + rectBottomWall.width + getWidth()/2);
                    } else if (getPosX() + getWidth()/2 - 20 < rectBottomWall.x) {
                        setPosX(rectBottomWall.x - getWidth()/2);
                    } else {
                        setPosY(rectBottomWall.y - getHeight()/2);
                        setHaveCollision(true);
                    }
                }
            }
        }
    }

    public boolean isPlantingBombs() {
        return isPlantingBombs;
    }

    public void setPlantingBombs(boolean plantingBombs) {
        isPlantingBombs = plantingBombs;
    }

    public boolean getHaveCollision() {
        return haveCollision;
    }

    public void setHaveCollision(boolean haveCollision) {
        this.haveCollision = haveCollision;
    }

    /**
     * Check vat di qua vat ko cho di chuyen.
     */
    public void checkCollisionWithRigid() {
        ParticularObject other = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
        if (other != null) {
            if (other.getRigid() == true) {
                checkRigid = true;
                Rectangle rect = other.getBoundForCollisionWithEnemy();
                if (rect.x == (getPosX() + getWidth()/2 - getSpeedX())) {
                    setPosX(rect.x - 24);
                    setSpeedX(0);
                } else if (rect.x + rect.width == getPosX() - getWidth()/2 - getSpeedX()) {
                    setPosX(rect.x + rect.width + 24);
                    setSpeedX(0);
                }
                if (getPosY() + getHeight()/2 - getSpeedY() == rect.y) {
                    setPosY(rect.y - 24);
                    setSpeedY(0);
                } else if (getPosY() - getHeight()/2 - getSpeedY() == rect.y + rect.height) {
                    setPosY(rect.y + rect.height + 24);
                    setSpeedY(0);
                }
                setHaveCollision(true);
            }
        }
    }
}
