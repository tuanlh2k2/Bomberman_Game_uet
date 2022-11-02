package gameobject.PaticularObject.Tile.Item;

import effect.CacheDataLoader;
import effect.FrameImage;
import gameobject.GameWorld;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;

/**
 * Item giúp tăng tốc độ của người chơi.
 */
public class SpeedItem extends Item {
    private FrameImage item;
    public SpeedItem(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        hideItem();
        setTeamType(WEAPON_TEAM);
        item = CacheDataLoader.getInstance().getFrameImage("speedItem");
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
            if (checkCollisionWithPlayer.getTeamType() == LEAGUE_TEAM) {
                soundEatItem.play();
                checkCollisionWithPlayer.setRunSpeed(6);
                setState(DEATH);
            }
        } else {
            setHide(false);
        }
    }
}
