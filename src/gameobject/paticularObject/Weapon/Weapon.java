package gameobject.paticularObject.Weapon;

import gameobject.GameWorld;
import gameobject.paticularObject.ParticularObject;

import java.awt.*;

public abstract class Weapon extends ParticularObject {
    private long scopeBom = 48;
    public Weapon(double posX, double posY, double width, double height, int damage, GameWorld gameWorld) {
        super(posX, posY, width, height, 1, gameWorld);
        setRigid(true);
        setTeamType(NO_TEAM);
        setDamage(damage); // dam gay ra khi cham vao doi tuong.
    }

    public void Update() {
        super.Update();
    }

    public long getScopeBom() {
        return scopeBom;
    }

    public void setScopeBom(long scopeBom) {
        this.scopeBom = scopeBom;
    }

    public abstract Rectangle getBoundForCollisionTop();
    public abstract Rectangle getBoundForCollisionLeft();
    public abstract Rectangle getBoundForCollisionDown();
    public abstract Rectangle getBoundForCollisionRight();
}
