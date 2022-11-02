package gameobject.GameFuncion;

import gameobject.GameObject;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Bomber.Player;

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
            if (player.getPosX() - getPosX() > 400 && getPosX() < 500) setPosX(player.getPosX() - 400);
            if (player.getPosX() - getPosX() < 400 && getPosX() > 0) setPosX(player.getPosX() - 400);
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
