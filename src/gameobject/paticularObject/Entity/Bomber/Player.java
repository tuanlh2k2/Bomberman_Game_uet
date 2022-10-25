package gameobject.paticularObject.Entity.Bomber;

import effect.Animation;
import effect.CacheDataLoader;
import gameobject.GameFuncion.Sound;
import gameobject.GameWorld;
import gameobject.paticularObject.Entity.Entity;
import gameobject.paticularObject.ParticularObject;
import gameobject.paticularObject.Weapon.Bomb;
import gameobject.paticularObject.Weapon.Weapon;

//import java.applet.AudioClip;
import java.awt.*;

public class Player extends Entity {

    private boolean statePlayer = true; // Kiem tra trang thai cua nguoi choi => neu fasle => gameover.
    private Animation runLeft, runRight, runUp, runDown;
    private Animation idleup, idledown, idleleft, idleRight;
    private Animation playerdie;
    private Sound gameover = new Sound();
    private long lastShootingTime;

    /**
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param blood
     * @param gameWorld
     */
    public Player(double posX, double posY, double width, double height, int blood, GameWorld gameWorld) {
        super(posX, posY, width, height, blood, gameWorld);
    }

    public Player(double x, double y, GameWorld gameWorld) {
        super(x, y, 48, 48, 1, gameWorld);
        setRunSpeed(3);
        setRigid(false);
        setTeamType(LEAGUE_TEAM);

        runLeft = CacheDataLoader.getInstance().getAnimation("left");
        runRight = CacheDataLoader.getInstance().getAnimation("right");
        runUp = CacheDataLoader.getInstance().getAnimation("up");
        runDown = CacheDataLoader.getInstance().getAnimation("down");

        idleup = CacheDataLoader.getInstance().getAnimation("idleup");
        idledown = CacheDataLoader.getInstance().getAnimation("idledown");
        idleleft = CacheDataLoader.getInstance().getAnimation("idleleft");
        idleRight = CacheDataLoader.getInstance().getAnimation("idleright");

        playerdie = CacheDataLoader.getInstance().getAnimation("playerdie");

        gameover.setFile(2);

    }

    public void Update() {
        super.Update();
    }

    // Xét tốc độ khi chạy theo hướng.
    @Override
    public void run() {
        if (getDirection() == LEFT_DIR) {
            setSpeedX(-getRunSpeed());
        } else if (getDirection() == RIGHT_DIR) {
            setSpeedX(getRunSpeed());
        } else if (getDirection() == TOP_DIR) {
            setSpeedY(-getRunSpeed());
        } else if (getDirection() == DOWN_DIR) {
            setSpeedY(getRunSpeed());
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
        if (!getBomb()) {
            Weapon bomb = new Bomb(getPosX(), getPosY(), getGameWorld());

            /**
             * cai dat thuat toan de dat bom dung cho => de co vu no sang cac huong co the.
             */
            // Kiem tra va cham tren va duoi.
            Rectangle checkRectTop = bomb.getBoundForCollisionTop();
            Rectangle checkRectLeft = bomb.getBoundForCollisionLeft();

            // Kiem tra va dieu chinh viec dat bom khi nhan vat di theo huong sang trai hoac sang phai.

            if (getGameWorld().physicalMap.haveCollisionWithTop(checkRectTop) != null) {
                Rectangle r = getGameWorld().physicalMap.haveCollisionWithTop(checkRectTop);
                if (checkRectTop.x + checkRectTop.width - 15 < r.x) {
                    bomb.setPosX(r.x - r.width/2);
                } else if (checkRectTop.x > r.x + r.width - 15) {
                    bomb.setPosX(r.x + r.width + r.width/2);
                } else {
                    bomb.setPosX(r.x + r.width/2);
                }
            }

            // Kiem tra va dieu chinh viec dat bom khi nhan vat di len hoac xuong.
            if (getGameWorld().physicalMap.haveCollisionWithRightWall(checkRectLeft) != null) {
                Rectangle l = getGameWorld().physicalMap.haveCollisionWithRightWall(checkRectLeft);
                if (checkRectLeft.y + checkRectLeft.height - 15 < l.y) {
                    bomb.setPosY(l.y - l.height/2);
                } else if (checkRectLeft.y + 15 > l.y + l.height) {
                    bomb.setPosY(l.y + checkRectLeft.height + l.height/2);
                } else {
                    bomb.setPosY(l.y + l.height/2);
                }
            }
                getGameWorld().particularObjectManager.addObject(bomb);
                lastShootingTime = System.nanoTime();
                setBomb(true);
            }
    }

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
//                drawBoundForCollisionWithEnemy(g2);
                break;
            case BEHURT:
                gameover.play();
                playerdie.Update(System.nanoTime());
                playerdie.draw((int) (getPosX() - getGameWorld().camera.getPosX() - 12), (int) getPosY() - (int) getGameWorld().camera.getPosY() - 12, g2);
                break;
        }
    }

    public boolean getStatePlayer() {
        return statePlayer;
    }

    public void setStatePlayer(boolean statePlayer) {
        this.statePlayer = statePlayer;
    }
}
