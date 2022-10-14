package gameobject;

import java.awt.*;

public class Player {
    private double posX;
    private double posY;
    private double width;
    private double height;
    private double speedX; // Van toc len tren
    private double speedY; // Van toc chieu Y.

    public static int DIR_LEFT;
    private static int DIR_RIGHT;
    private int direction; // Huong di.


    public Player(double posX, double posY, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillRect((int)posX, (int)posY, (int) width, (int)height );
    }

    public void update() {
        setPosX(getPosX() + this.posX);
    }

    /**
     * setter and getter.
     */
    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
