package gameobject.paticularObject;

import effect.Animation;
import gameobject.GameObject;
import gameobject.GameWorld;

import java.awt.*;

public abstract class ParticularObject extends GameObject {
    public static final int LEAGUE_TEAM = 1; // Doi tuong khi cham vao se ko chet.
    public static final int ENEMY_TEAM = 2; // Doi tuong enemy (ke dich).

    public static final int LEFT_DIR = 0;
    public static final int RIGHT_DIR = 1;
    public static final int TOP_DIR = 3;
    public static final int DOWN_DIR = 4;

    public static final int ALIVE = 0; // trang thai song.
    public static final int DEATH = 1; // chet.
    public static final int BEHURT = 2; // bi dau.
    public static final int NOBEHURT = 3; // khong bi thuong khi hoi sinh.
    private int state = ALIVE; // trang thai cua nhan vat.

    private double width;
    private double height;
    private double speedX;
    private double speedY;
    private int blood; // mau cua nhan vat.

    private int damage; // do sat thuong.
    private int direction; // huong cua nhan vat.
    protected Animation behurtForwarAnim, behurtBackAnim;
    private int teamType; // loai team ( cung hoac khac).
    private long startTimeNoBeHurt; // thoi gian khong bi dau.
    private long timeForNoBeHurt; // thoi gian ket thuc khong bi dau.



    public ParticularObject(double posX, double posY, double width, double height, int blood, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        setWidth(width);
        setHeight(height);
        setBlood(blood);

        direction = DOWN_DIR;
    }

    /**
     * setter and getter.
     */

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

    public long getStartTimeNoBeHurt() {
        return startTimeNoBeHurt;
    }

    public void setStartTimeNoBeHurt(long startTimeNoBeHurt) {
        this.startTimeNoBeHurt = startTimeNoBeHurt;
    }

    public long getTimeForNoBeHurt() {
        return timeForNoBeHurt;
    }

    public void setTimeForNoBeHurt(long timeForNoBeHurt) {
        this.timeForNoBeHurt = timeForNoBeHurt;
    }

    public int getTeamType() {
        return teamType;
    }

    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    @Override
    public void Update() {
    }

    public abstract void attack(); // dat bom.

    // Kiem tra xem doi tuong co nam trong khu vuc view hay ko.
    public boolean isObjectOutOfCameraView() {
        if (getPosX() - getGameWorld().camera.getPosX() > getGameWorld().camera.getWidthView() ||
                getPosX() - getGameWorld().camera.getPosX() < -50
                || getPosY() - getGameWorld().camera.getPosY() > getGameWorld().camera.getHeightView()
                || getPosY() - getGameWorld().camera.getPosY() < -50) {
            return false;
        } else {
            return true;
        }
    }

    public Rectangle getBoundForCollisionWithMap() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - (getWidth()/2));
        bound.y = (int) (getPosY() - (getHeight()/2));
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    public abstract Rectangle getBoundForCollisionWithEnemy();

    public void drawBoundForCollisionWithMap(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithMap();
        g2.setColor(Color.BLUE);
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(),
                rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }

    public void drawBoundForCollisionWithEnemy(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithEnemy();
        g2.setColor(Color.RED);
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }
    public abstract void draw(Graphics2D g2);
}
