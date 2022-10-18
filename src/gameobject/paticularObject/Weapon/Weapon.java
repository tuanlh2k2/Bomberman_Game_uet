package gameobject.paticularObject.Weapon;

import gameobject.GameWorld;
import gameobject.paticularObject.ParticularObject;

import java.awt.*;

public abstract class Weapon extends ParticularObject {
    public Weapon(double posX, double posY, double width, double height, int damage, GameWorld gameWorld) {
        super(posX, posY, width, height, 1, gameWorld);
        setDamage(damage); // dam gay ra khi cham vao doi tuong.
    }

    public void Update() {
        super.Update();
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
    }

    public abstract Rectangle getBoundForCollisionTop();
    public abstract Rectangle getBoundForCollisionLeft();
    public abstract Rectangle getBoundForCollisionDown();
    public abstract Rectangle getBoundForCollisionRight();
}
