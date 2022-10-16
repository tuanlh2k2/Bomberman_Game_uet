package gameobject;

import gameobject.paticularObject.Entity.Player;
import userinterface.GameFrame;

public class Camera extends GameObject {
    private double widthView;
    private double heightView;

    private boolean isLocked = false; // khi xet camara = locked thi no se khong di theo nhan vat nua.

    public Camera(double posX, double posY, double widthView, double heightView, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        this.widthView = widthView;
        this.heightView = heightView;
    }

    @Override
    public void Update() {
        if (!isLocked) {
            Player player = getGameWorld().player;
            if (player.getPosX() - getPosX() > 600 && (player.getDirection() == 1)) setPosX(player.getPosX() - 600);
            if (player.getPosX() - getPosX() < 200) setPosX(player.getPosX() - 200);
        }
    }

    public void lock() {
        this.isLocked = true;
    }

    public void unlock() {
        this.isLocked = false;
    }

    public double getWidthView() {
        return widthView;
    }

    public void setWidthView(double widthView) {
        this.widthView = widthView;
    }

    public double getHeightView() {
        return heightView;
    }

    public void setHeightView(double heightView) {
        this.heightView = heightView;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
