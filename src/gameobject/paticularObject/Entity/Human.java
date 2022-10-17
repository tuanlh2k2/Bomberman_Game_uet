package gameobject.paticularObject.Entity;

import gameobject.GameWorld;
import gameobject.paticularObject.ParticularObject;

import java.awt.*;

public abstract class Human extends ParticularObject {
//    private boolean isUping;
//    private boolean isDowning;
//    private boolean isLefting;
//    private boolean isRighting;
    private boolean isPlantingBombs;

    public Human(double posX, double posY, double width, double height, int blood, GameWorld gameWorld) {
        super(posX, posY, width, height, blood, gameWorld);
        setState(ALIVE);
    }

    public abstract void run(); // chay.
    public abstract void stopRun();


    public void Update() {
        super.Update();
        if (getState() == ALIVE) {
            //setPosY(getPosY() + getSpeedY());
            if (getDirection() == LEFT_DIR ) {
                if (getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap()) == null) {
                    setPosX(getPosX() + getSpeedX());
                } else {
                    Rectangle rectLeftWall = getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                    if (getPosY() > rectLeftWall.y + rectLeftWall.height + getHeight()/2 - 15 ) {
                        setPosY(rectLeftWall.y + rectLeftWall.height + getHeight()/2);
                        setPosX(getPosX() + getSpeedX());
                    } else if (rectLeftWall.y > getPosY() + getHeight()/2 - 15) {
                        setPosY(rectLeftWall.y - getHeight()/2);
                    } else {
                        //System.out.println(getPosY() + " : " + (rectLeftWall.y + rectLeftWall.height));
                        setPosX(rectLeftWall.x + rectLeftWall.width + getWidth() / 2);
                    }
                }
            }
            if (getDirection() == RIGHT_DIR ) {
                if (getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap()) == null) {
                    setPosX(getPosX() + getSpeedX());
                } else {
                    Rectangle rectRightWall = getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                    if (getPosY() - getHeight()/2 + 15 > rectRightWall.y + rectRightWall.height) {
                        setPosY(rectRightWall.y + rectRightWall.height + getHeight()/2);
                        setPosX(getPosX() + getSpeedX());
                       // System.out.println("OK");
                    } else if (rectRightWall.y > getPosY() + getHeight()/2 - 15) {
                        setPosY(rectRightWall.y - getHeight()/2);
                        setPosX(getPosX() + getSpeedX());
                       // System.out.println("YES");
                    } else {
                        setPosX(rectRightWall.x - getWidth() / 2);
                    }
                }
            }
            if (getDirection() == TOP_DIR ) {
                if (getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionWithMap()) == null) {
                    setPosY(getPosY() + getSpeedY());
                } else {
                    Rectangle rectTopWall = getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionWithMap());
                    if (getPosX() - getWidth()/2 + 15 > rectTopWall.x + rectTopWall.width) {
                        setPosX(rectTopWall.x + rectTopWall.width + getWidth()/2);
                        System.out.println("OK");
                    } else if (getPosX() + getWidth()/2 -15 < rectTopWall.x) {
                        setPosX(rectTopWall.x - getWidth()/2);
                        System.out.println("Ahaha");
                    } else {
                        setPosY(rectTopWall.y + getHeight()/2 + rectTopWall.height);
                    }
                }
            }
            if (getDirection() == DOWN_DIR ) {
                if (getGameWorld().physicalMap.haveCollisionWithBottom(getBoundForCollisionWithMap()) == null) {
                    setPosY(getPosY() + getSpeedY());
                } else {
                    Rectangle rectBottomWall = getGameWorld().physicalMap.haveCollisionWithBottom(getBoundForCollisionWithMap());
                    if (getPosX() - getWidth()/2 + 15 > rectBottomWall.x + rectBottomWall.width) {
                        setPosX(rectBottomWall.x + rectBottomWall.width + getWidth()/2);
                        System.out.println("OK");
                    } else if (getPosX() + getWidth()/2 -15 < rectBottomWall.x) {
                        setPosX(rectBottomWall.x - getWidth()/2);
                        System.out.println("Ahaha");
                    } else {
                        setPosY(rectBottomWall.y + getHeight()/2 + rectBottomWall.height);
                    }
                    setPosY(rectBottomWall.y - getHeight()/2);
                }
            }
        }
    }

//    public boolean isUping() {
//        return isUping;
//    }
//
//    public void setUping(boolean uping) {
//        isUping = uping;
//    }
//
//    public boolean isDowning() {
//        return isDowning;
//    }
//
//    public void setDowning(boolean downing) {
//        isDowning = downing;
//    }
//
//    public boolean isLefting() {
//        return isLefting;
//    }
//
//    public void setLefting(boolean lefting) {
//        isLefting = lefting;
//    }
//
//    public boolean isRighting() {
//        return isRighting;
//    }
//
//    public void setRighting(boolean righting) {
//        isRighting = righting;
//    }

    public boolean isPlantingBombs() {
        return isPlantingBombs;
    }

    public void setPlantingBombs(boolean plantingBombs) {
        isPlantingBombs = plantingBombs;
    }
}
