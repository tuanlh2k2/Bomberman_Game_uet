package gameobject.paticularObject.Tile.Item;

import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameWorld;
import gameobject.paticularObject.ParticularObject;

import java.awt.*;

public class Portal extends Item {

    FrameImage item;
    public Portal(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        hideItem();
        setTeamType(WEAPON_TEAM);
        item = CacheDataLoader.getInstance().getFrameImage("portal");
    }

    /**
     *
     */
    @Override
    public void attack() {

    }

    /**
     * @param g2
     */
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
                CacheDataLoader.getInstance().setLever(CacheDataLoader.getInstance().getLever()+1);
                CacheDataLoader.getInstance().loadMap();
                setState(DEATH);
            }
        } else {
            setHide(false);
        }
    }
}
