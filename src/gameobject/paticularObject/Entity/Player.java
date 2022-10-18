package gameobject.paticularObject.Entity;

import effect.Animation;
import effect.CacheDataLoader;
import gameobject.GameWorld;
import gameobject.paticularObject.Weapon.Bomb;
import gameobject.paticularObject.Weapon.Weapon;

import java.awt.*;

public class Player extends Human {
    public static final int RUNSPEED = 3;
    private Animation runLeft, runRight, runUp, runDown;
    private Animation idleup, idledown, idleleft, idleRight;

    private long lastShootingTime;
    private boolean isShooting = false;


    public Player(double x, double y, GameWorld gameWorld) {
        super(x, y, 48,  48, 1, gameWorld);

        runLeft = CacheDataLoader.getInstance().getAnimation("left");
        runRight = CacheDataLoader.getInstance().getAnimation("right");
        runUp = CacheDataLoader.getInstance().getAnimation("up");
        runDown = CacheDataLoader.getInstance().getAnimation("down");

        idleup = CacheDataLoader.getInstance().getAnimation("idleup");
        idledown = CacheDataLoader.getInstance().getAnimation("idledown");
        idleleft = CacheDataLoader.getInstance().getAnimation("idleleft");
        idleRight = CacheDataLoader.getInstance().getAnimation("idleright");
    }

    public void Update() {
        super.Update();
        if(isShooting){
            if(System.nanoTime() - lastShootingTime > 500000000){
                isShooting = false;
            }
        }
    }

    // Xét tốc độ khi chạy theo hướng.
    @Override
    public void run() {
        if (getDirection() == LEFT_DIR) {
            setSpeedX(-1.5);
        } else if (getDirection() == RIGHT_DIR) {
            setSpeedX(1.5);
        } else if (getDirection() == TOP_DIR) {
            setSpeedY(-1.5);
        } else if (getDirection() == DOWN_DIR) {
            setSpeedY(1.5);
        }
    }

    // Khi nhả phím.
    @Override
    public void stopRun() {
        setSpeedY(0);
        setSpeedX(0);
    }

    // Đặt bom.
    @Override
    public void attack() {
        if (!isShooting) {
            Weapon bomb = new Bomb(getPosX(), getPosY(), getGameWorld());
            // Test dat bom.
            if (getDirection() == LEFT_DIR && getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionWithMap()) != null) {
                    Rectangle rectTopWall = getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionWithMap());
                if (getPosX() - getWidth()/2 + 15 > rectTopWall.x + rectTopWall.width) {
                    bomb.setPosX(rectTopWall.x + rectTopWall.width + getWidth()/2);
                } else if (getPosX() + getWidth()/2 -15 < rectTopWall.x) {
                    bomb.setPosX(rectTopWall.x - getWidth()/2);
                }
            } else {
                bomb.setPosX(bomb.getPosX() - getWidth()/2);
                bomb.setPosY(bomb.getPosY() - getHeight()/2);
            }




            ///

            getGameWorld().weaponManager.addObject(bomb);
            lastShootingTime = System.nanoTime();
            isShooting = true;
        }
    }


    // con phai sua....
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        rect.x = (int) (getPosX() - getWidth()/2);
        rect.y = (int) (getPosY() - getHeight()/2);
        rect.width = (int) getWidth();
        rect.height = (int) getHeight();
        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
        switch (getState()) {
            case ALIVE:
            case NOBEHURT:
                if (getSpeedX() > 0) {
                    runRight.Update(System.nanoTime());
                    runRight.draw((int) (getPosX() - getGameWorld().camera.getPosX() - 12), (int) getPosY() - (int) getGameWorld().camera.getPosY() - 12, g2);
                    if(runRight.getCurrentFrame() == 1) runRight.setIgnoreFrame(0);
                } else if (getSpeedX() < 0) {
                    runLeft.Update(System.nanoTime());
                    runLeft.draw((int) (getPosX() - getGameWorld().camera.getPosX() - 12), (int) getPosY() - (int) getGameWorld().camera.getPosY() - 12, g2);
                    if(runLeft.getCurrentFrame() == 1) runLeft.setIgnoreFrame(0);
                } else if (getSpeedY() < 0) {
                    runUp.Update(System.nanoTime());
                    runUp.draw((int) (getPosX() - getGameWorld().camera.getPosX() - 12), (int) getPosY() - (int) getGameWorld().camera.getPosY() - 12, g2);
                    if(runUp.getCurrentFrame() == 1) runUp.setIgnoreFrame(0);
                } else if (getSpeedY() > 0) {
                    runDown.Update(System.nanoTime());
                    runDown.draw((int) (getPosX() - getGameWorld().camera.getPosX() - 12), (int) getPosY() - (int) getGameWorld().camera.getPosY() - 12, g2);
                    if(runDown.getCurrentFrame() == 1) runDown.setIgnoreFrame(0);
                } else {
                    if (getDirection() == RIGHT_DIR) {
                        idleRight.Update(System.nanoTime());
                        idleRight.draw((int) (getPosX() - getGameWorld().camera.getPosX() - 12), (int) getPosY() - (int) getGameWorld().camera.getPosY() - 12, g2);
                    } else if (getDirection() == LEFT_DIR) {
                        idleleft.Update(System.nanoTime());
                        idleleft.draw((int) (getPosX() - getGameWorld().camera.getPosX() - 12), (int) getPosY() - (int) getGameWorld().camera.getPosY() - 12, g2);
                    } else if (getDirection() == TOP_DIR && getSpeedX() == 0) {
                        idleup.Update(System.nanoTime());
                        idleup.draw((int) (getPosX() - getGameWorld().camera.getPosX() - 12), (int) getPosY() - (int) getGameWorld().camera.getPosY() - 12, g2);
                    } else if (getDirection() == DOWN_DIR){
                        idledown.Update(System.nanoTime());
                        idledown.draw((int) (getPosX() - getGameWorld().camera.getPosX() - 12), (int) getPosY() - (int) getGameWorld().camera.getPosY() - 12, g2);
                    }
                }
                drawBoundForCollisionWithEnemy(g2);
//                System.out.println(getPosX() + " : " + getPosY());
//                idleup.Update(System.nanoTime());
//                idleup.draw((int) (getPosX() - getGameWorld().camera.getPosX() - 12), (int) getPosY() - (int) getGameWorld().camera.getPosY() - 12, g2);
        }
    }
}
