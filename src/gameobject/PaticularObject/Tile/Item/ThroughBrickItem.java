package gameobject.PaticularObject.Tile.Item;

import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Bomber.Player;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;

/**
 * Nhân vật sẽ có thể đi xuyên qua tường (kí hiệu là chữ g).
 */
public class ThroughBrickItem extends Item {
    private FrameImage item;

    public ThroughBrickItem(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        hideItem();
        item = CacheDataLoader.getInstance().getFrameImage("throughBrickItem");
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
            if (checkCollisionWithPlayer instanceof Player && getHide() == false) {
                soundEatItem.play();
                ((Player) checkCollisionWithPlayer).setThroughBrick(true);
                setState(DEATH);
            }
        } else {
            setHide(false);
        }
    }
}
