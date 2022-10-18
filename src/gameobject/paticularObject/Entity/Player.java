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
        super(x, y, 48, 48, 1, gameWorld);

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
            Weapon bomb = new Bomb(getPosX() - getWidth() / 2, getPosY() - getHeight() / 2, getGameWorld());

            /**
             * cai dat thuat toan de dat bom dung cho => de co vu no sang cac huong co the.
             */
            // Kiem tra va cham tren va duoi.
            Rectangle checkRectTop = new Rectangle((int) bomb.getPosX(), (int) bomb.getPosY() - 48, 48, 48);
            Rectangle checkRectRight = new Rectangle((int) bomb.getPosX() + 48, (int) bomb.getPosY(), 48, 48);
            Rectangle checkRectLeft = new Rectangle((int) bomb.getPosX() - 48, (int) bomb.getPosY(), 48, 48);
            if (getGameWorld().physicalMap.haveCollisionWithTop(checkRectTop) != null) {
                Rectangle r = getGameWorld().physicalMap.haveCollisionWithTop(checkRectTop);
                if (checkRectTop.x + 15 > r.x + r.width) { // bi va cham voi tuong ben trai.
                    if (getDirection() == RIGHT_DIR || getDirection() == LEFT_DIR) {
                        bomb.setPosX(r.x + r.width); // dat bom tai vi tri mep tuong ben trai va cham.
                    }
                } else if (r.x + 15 > checkRectTop.x) { // va cham o mep tuong ben phai.
                    if (getDirection() == RIGHT_DIR) {
                        bomb.setPosX(r.x);
                    } else if (getDirection() == LEFT_DIR && getGameWorld().physicalMap.haveCollisionWithRightWall(checkRectLeft)== null) {
                        bomb.setPosX(r.x - r.height);
                    }
                }
            }
            // Kiem tra va cham trai va phai.
            if (getGameWorld().physicalMap.haveCollisionWithLeftWall(checkRectRight) != null) {
                System.out.println("co va cham");
                Rectangle r = getGameWorld().physicalMap.haveCollisionWithLeftWall(checkRectRight);
                Rectangle l = getGameWorld().physicalMap.haveCollisionWithRightWall(checkRectLeft);
                if (checkRectRight.y + 20 > r.y + r.height) {
                    bomb.setPosY(r.y + r.height);
                }
                if (checkRectRight.y + checkRectRight.height< r.y + 20) {
                    bomb.setPosY(r.y - r.height);
                }
                if (l != null) {
                    if (checkRectRight.y + 20 > l.y + l.height) {
                        bomb.setPosY(l.y + l.height);
                    }
                    if (checkRectRight.y + checkRectRight.height< l.y + 20) {
                        bomb.setPosY(l.y - l.height);
                    }
                }
            }
            getGameWorld().weaponManager.addObject(bomb);
            lastShootingTime = System.nanoTime();
            this.isShooting = true;
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
        }
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }
}
