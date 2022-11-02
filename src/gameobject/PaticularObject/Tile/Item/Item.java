package gameobject.PaticularObject.Tile.Item;

import gameobject.GameFuncion.Sound;
import gameobject.GameWorld;
import gameobject.PaticularObject.ParticularObject;
import gameobject.PaticularObject.Tile.Brick;

import java.awt.*;

public abstract class Item extends ParticularObject {
    Sound soundEatItem = new Sound();
    private boolean hide = true; // moi item deu bi an.
    public Item(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, 48, 48, 1, gameWorld);

        soundEatItem.setFile("eatItem");
    }

    public void hideItem() {
        ParticularObject brick = new Brick(getPosX(), getPosY(), getGameWorld());
        getGameWorld().particularObjectManager.addObject(brick);
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - getWidth()/2 + 5);
        bound.y = (int) (getPosY() - getHeight()/2 + 5);
        bound.width = (int) getWidth() - 10;
        bound.height = (int) getHeight() - 10;
        return bound;
    }
    public boolean getHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }
}
