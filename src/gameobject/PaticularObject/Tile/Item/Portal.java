package gameobject.PaticularObject.Tile.Item;

import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Bomber.Player;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;

/**
 * Khi giết hết quái thì sẽ qua màn.
 */
public class Portal extends Item {
    FrameImage portal;

    public Portal(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        hideItem();
        setRigid(false);
        setTeamType(WEAPON_TEAM);
        portal = CacheDataLoader.getInstance().getFrameImage("portal");
    }

    @Override
    public void attack() {

    }

    @Override
    public void draw(Graphics2D g2) {
        if (getState() == ALIVE && getHide() == false) {
            g2.drawImage(portal.getImage(), (int) (getPosX() - getWidth()/2 - getGameWorld().camera.getPosX()),
                    (int) (getPosY() - getHeight()/2 - getGameWorld().camera.getPosY()), 48,48, null);
            drawBoundForCollisionWithEnemy(g2);
        }
    }

    @Override
    public void Update() {
        super.Update();
        ParticularObject checkCollisionWithPlayer = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
        if (checkCollisionWithPlayer != null) {
            if (checkCollisionWithPlayer instanceof Player) {
                if (getGameWorld().getCountEnemy() == 0) {
                    soundEatItem.play();
                    ((Player) checkCollisionWithPlayer).setOppenTheDoor(true);
                }
            }
        } else {
            setHide(false); // khong bi an nua.
        }
    }
}
