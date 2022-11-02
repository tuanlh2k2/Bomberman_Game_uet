package gameobject.PaticularObject.Tile.Item;

import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Bomber.Player;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;

/**
 * Item giúp đặt thêm 1 quả bom nữa. (giới hạn = 2).
 */
public class BombItem extends Item {

    private FrameImage item;

    public BombItem(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        hideItem();
        setTeamType(WEAPON_TEAM);
        item = CacheDataLoader.getInstance().getFrameImage("bombItem");
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
//        drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void Update() {
        super.Update();
        ParticularObject checkCollisionWithPlayer = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
        if (checkCollisionWithPlayer != null) {
            if (checkCollisionWithPlayer instanceof Player) {
                soundEatItem.play();
                getGameWorld().player.setMAX_WEAPON(2);
                setState(DEATH);
            }
        } else {
            setHide(false);
        }
    }
}
