package gameobject.paticularObject.Tile.Item;

import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameWorld;
import gameobject.paticularObject.ParticularObject;
import gameobject.paticularObject.Weapon.Bomb;
import gameobject.paticularObject.Weapon.Weapon;

import java.awt.*;

public class FlameItem extends Item {
    private FrameImage item;

    Weapon bomb = new Bomb(getPosX(), getPosY(), getGameWorld());

    public FlameItem(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        hideItem();
        setTeamType(WEAPON_TEAM);
        item = CacheDataLoader.getInstance().getFrameImage("FlameItem");
    }

    @Override
    public void attack() {

    }

    @Override
    public void draw(Graphics2D g2) {
        if (getState() == ALIVE && getHide() == false) {
            g2.drawImage(item.getImage(), (int) (getPosX() - getWidth()/2 - getGameWorld().camera.getPosX()),
                    (int) (getPosY() - getHeight()/2 - getGameWorld().camera.getPosY()), 48,48, null);
        }
    }

    @Override
    public void Update() {
        super.Update();
        ParticularObject checkCollisionWithPlayer = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
        if (checkCollisionWithPlayer != null) {
            if (checkCollisionWithPlayer.getTeamType() == LEAGUE_TEAM) {
                bomb.setScopeBom(bomb.getScopeBom() + 16);
                setState(DEATH);
            }
        } else {
            setHide(false);
        }
    }
}
