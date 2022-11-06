package gameobject.PaticularObject.Entity;

import gameobject.GameWorld;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;

public abstract class Entity extends ParticularObject {
    private double oldPosX; // tọa độ cũ của đối tượng.
    private double oldPosY; // tọa độ cũ của đối tượng.
    private boolean haveCollision; // Kiểm tra xem có va chạm hay không.
    private boolean throughBrick = false; // Xét xem thực thể có thể xuyên qua gạch hay không.
    public boolean onPath = false;
    public Entity(double posX, double posY, double width, double height, int blood, GameWorld gameWorld) {
        super(posX, posY, width, height, blood, gameWorld);
        setState(ALIVE);
    }

    public abstract void run(); // chay.
    public abstract void stopRun();


    public void Update() {
        super.Update();
        setHaveCollision(false);
        if (getState() == ALIVE || getState() == NOBEHURT || getState() == IMMORTAL) {
            checkCollisionWithRigid();
        }
    }

    public boolean getHaveCollision() {
        return haveCollision;
    }

    public void setHaveCollision(boolean haveCollision) {
        this.haveCollision = haveCollision;
    }

    public boolean getThroughBrick() {
        return throughBrick;
    }

    public void setThroughBrick(boolean throughBrick) {
        this.throughBrick = throughBrick;
    }

    /**
     * Check vat di qua vat ko cho di chuyen.
     */
    public void checkCollisionWithRigid() {
        // Lưu lại tọa độ cũ.
        oldPosX = getPosX();
        oldPosY = getPosY();

        // Cài đặt tọa độ mới.
        if (getDirection() == LEFT_DIR || getDirection() == RIGHT_DIR) {
            setPosX(getPosX() + getSpeedX());
        } else if (getDirection() == TOP_DIR || getDirection() == DOWN_DIR) {
            setPosY(getPosY() + getSpeedY());
        }

        // Kiểm tra trạng thái va chạm khi di chuyển.
        ParticularObject other = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this, true);
        // Kiem tra va cham voi vat can.
        if (other != null) {
            // Xu ly va cham voi wall va brick.
            Rectangle rect = other.getBoundForCollisionWithEnemy();
            // Xử lý va chạm khi đi xuống hoặc đi lên.
            if (getDirection() == TOP_DIR || getDirection() == DOWN_DIR) {
                // Xử lý mép bên phải.
                if (rect.x - getPosX() >= 6) {
                    setPosX(rect.x - getWidth()/2);
                } else if (getPosX() - rect.x - rect.width >= 8) { // Xử lý mép bên trái.
                    setPosX(rect.x + getWidth() + getWidth()/2);
                } else {
                    setHaveCollision(true);
                    setPosX(oldPosX);
                    setPosY(oldPosY);
                }
            }

            // Xử lý khi đi sang trái hoặc sang phải.
            else if (getDirection() == RIGHT_DIR || getDirection() == LEFT_DIR) {
                // Xử lý mép bên phải.
                if (rect.y - getPosY() >= 8) {
                    setPosY(rect.y - getHeight()/2);
                }
                else if (getPosY() - rect.y - rect.height >= 8) {
                    setPosY(rect.y + rect.height + getHeight()/2);
                } else {
                    setHaveCollision(true);
                    setPosX(oldPosX);
                    setPosY(oldPosY);
                }
            } else {
                setHaveCollision(true); // Đánh dấu là đã va chạm.
                setPosX(oldPosX);
                setPosY(oldPosY);
            }
        }
    }
}
