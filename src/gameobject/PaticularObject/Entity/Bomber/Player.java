package gameobject.PaticularObject.Entity.Bomber;

import effect.Animation;
import effect.CacheDataLoader;
import gameobject.GameFuncion.Sound;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Entity;
import gameobject.PaticularObject.Weapon.Bomb;
import gameobject.PaticularObject.Weapon.Weapon;

//import java.applet.AudioClip;
import java.awt.*;

public class Player extends Entity {
    private int MAX_WEAPON = 1; // số bom có thể đặt tối đa.
    private int count_bomb = 0; // đếm số bom đã đặt.
    private boolean oppenTheDoor = false; // Kiem tra xem nhan vat da mo cua hay chua.
    private boolean statePlayer = true; // Kiem tra trang thai cua nguoi choi => neu fasle => gameover.
    private Animation runLeft, runRight, runUp, runDown;
    private Animation idleup, idledown, idleleft, idleRight;
    private Animation playerdie;
    private Sound gameover = new Sound();

    public Player(double x, double y, GameWorld gameWorld) {
        super(x, y, 48, 48, 3, gameWorld);
        setRunSpeed(4);
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

        gameover.setFile("overGame");
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
        if (this.count_bomb < MAX_WEAPON) {
            // Cai dat toa do cho bomb de bomb no nhieu huong nhat.
            int posXBom = (int) (getPosX()/ 48) * 48 + 24;
            int posYBom = (int) (getPosY() / 48) * 48 + 24;
            Weapon bomb = new Bomb(posXBom, posYBom, getGameWorld());
            bomb.setHaveMove(true);
                this.count_bomb ++;
                getGameWorld().particularObjectManager.addObject(bomb);
            }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
        switch (getState()) {
            case ALIVE:
            case IMMORTAL:
            case NOBEHURT:
                if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1)
                {
                    System.out.println("Plash...");
                } else {
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
                }
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

    public boolean getOppenTheDoor() {
        return oppenTheDoor;
    }

    public void setOppenTheDoor(boolean oppenTheDoor) {
        this.oppenTheDoor = oppenTheDoor;
    }

    public void setMAX_WEAPON(int MAX_WEAPON) {
        this.MAX_WEAPON = MAX_WEAPON;
    }

    public int getCount_bomb() {
        return count_bomb;
    }

    public void setCount_bomb(int count_bomb) {
        this.count_bomb = count_bomb;
    }
}
