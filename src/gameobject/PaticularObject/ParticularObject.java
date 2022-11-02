package gameobject.PaticularObject;

import gameobject.GameObject;
import gameobject.GameWorld;

import java.awt.*;

public abstract class ParticularObject extends GameObject {
    public static final int LEAGUE_TEAM = 1; // Doi tuong khi cham vao se ko chet.
    public static final int ENEMY_TEAM = 2; // Doi tuong enemy (ke dich).
    public static final int NO_TEAM = 3; // Khong cho cac doi tuong 1 2 di qua.
    public static final int WEAPON_TEAM = 4; // Khong bi bom no.


    public static final int LEFT_DIR = 0;
    public static final int RIGHT_DIR = 1;
    public static final int TOP_DIR = 2;
    public static final int DOWN_DIR = 3;


    public static final int ALIVE = 0; // trang thai song.
    public static final int DEATH = 1; // chet.
    public static final int BEHURT = 2; // bi dau.
    public static final int NOBEHURT = 3; // khong bi thuong khi hoi sinh.
    public static final int IMMORTAL = 4; // Trạng thái bất tử.
    private int state = ALIVE; // trang thai cua nhan vat.

    private double width;
    private double height;
    private double speedX;
    private double speedY;


    private int blood; // mau cua nhan vat.
    private int runSpeed; // Toc do cua doi tuong.
    private int damage; // do sat thuong.
    private int direction; // huong cua nhan vat.
    private int teamType; // loai team ( cung hoac khac).
    private long timeStartBeHurt; // thoi gian bat dau bi dau.
    private long timeStartNoBeHurt; // thoi gian bat dau bi dau.
    private long timeStartImmotal; // thoi gian bat dau bat tu.
    private boolean rigid; // doi tuong co cho doi tuong khac di qua khong ?.


    public ParticularObject(double posX, double posY, double width, double height, int blood, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        setWidth(width);
        setHeight(height);
        setBlood(blood);
        direction = RIGHT_DIR;
    }


    /**
     * setter and getter.
     */

    public long getTimeStartBeHurt() {
        return timeStartBeHurt;
    }

    public void setTimeStartBeHurt(long timeStartBeHurt) {
        this.timeStartBeHurt = timeStartBeHurt;
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getTeamType() {
        return teamType;
    }

    public boolean getRigid() {
        return rigid;
    }

    public void setRigid(boolean rigid) {
        this.rigid = rigid;
    }

    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    public int getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(int runSpeed) {
        this.runSpeed = runSpeed;
    }

    public void setTimeStartImmotal(long timeStartImmotal) {
        this.timeStartImmotal = timeStartImmotal;
    }

    @Override
    public void Update() {
        if (getState() == BEHURT && getBlood() > 0 ) {
            setState(NOBEHURT);
            timeStartNoBeHurt = System.currentTimeMillis();
        } else if (getState() == BEHURT && System.currentTimeMillis() - getTimeStartBeHurt() > 1000) {
            setState(DEATH);
        } else if (getState() == NOBEHURT) {
            if (System.currentTimeMillis() - timeStartNoBeHurt > 1500) {
                setState(ALIVE);
            }
        } else if (getState() == IMMORTAL && System.currentTimeMillis() - timeStartImmotal > 5000) {
            setState(ALIVE);
        }
    }

    public abstract void attack(); // dat bom.

    public Rectangle getBoundForCollisionWithMap() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - getWidth()/2);
        bound.y = (int) (getPosY() - getHeight()/2);
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    public abstract Rectangle getBoundForCollisionWithEnemy();

    public void drawBoundForCollisionWithEnemy(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithEnemy();
        g2.setColor(Color.RED);
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }

    public abstract void draw(Graphics2D g2);
}
