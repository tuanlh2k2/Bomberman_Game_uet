package gameobject.paticularObject.Entity;

import gameobject.GameWorld;

import java.awt.*;

public class Player extends Human {
    public static final int RUNSPEED = 3;
    public Player(double x, double y, GameWorld gameWorld) {
        super(x, y, 48,  48, 1, gameWorld);
    }

    public void Update() {
        super.Update();
    }

    // Xét tốc độ khi chạy theo hướng.
    @Override
    public void run() {
        if (getDirection() == LEFT_DIR) {
            setSpeedX(-2);
        } else if (getDirection() == RIGHT_DIR) {
            setSpeedX(2);
        } else if (getDirection() == TOP_DIR) {
            setSpeedY(-2);
        } else if (getDirection() == DOWN_DIR) {
            setSpeedY(2);
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
        drawBoundForCollisionWithMap(g2);
        drawBoundForCollisionWithEnemy(g2);
    }
}
