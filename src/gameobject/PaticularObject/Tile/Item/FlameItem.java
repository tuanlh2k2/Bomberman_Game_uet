package gameobject.PaticularObject.Tile.Item;

import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Bomber.Player;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;

/**
 * Item tăng độ dài của flame (lửa).
 */
public class FlameItem extends Item {
    private FrameImage item;
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
            if (checkCollisionWithPlayer instanceof Player &&  getHide() == false) {
                soundEatItem.play();
                getGameWorld().setScopeBom(96);
                setState(DEATH);
            }
        } else {
            setHide(false);
        }
    }
}
