package gameobject.PaticularObject.Weapon;

import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Bomber.Player;
import gameobject.PaticularObject.ParticularObject;

public abstract class Weapon extends ParticularObject {
    private boolean haveMove = false;
    private int amountMax = 1; // So luong bom duoc phep dat.
    public Weapon(double posX, double posY, double width, double height, int damage, GameWorld gameWorld) {
        super(posX, posY, width, height, 1, gameWorld);
        setRigid(true);
        setTeamType(WEAPON_TEAM);
        setDamage(damage); // dam gay ra khi cham vao doi tuong.
    }

    public void Update() {
        super.Update();
    }

    @Override
    public boolean getRigid() {
        ParticularObject other = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
        if (other != null) {
            if (other instanceof Player && getHaveMove() == true) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public boolean getHaveMove() {
        return haveMove;
    }

    public void setHaveMove(boolean haveMove) {
        this.haveMove = haveMove;
    }
}
