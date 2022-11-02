package gameobject.PaticularObject.Weapon;

import effect.Animation;
import effect.CacheDataLoader;
import gameobject.GameFuncion.Sound;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Bomber.Player;
import gameobject.PaticularObject.ParticularObject;
import gameobject.PaticularObject.Tile.Brick;
import gameobject.PaticularObject.Tile.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bomb extends Weapon {
    private Animation bomb;
    public long timeBegin;
    private long timeHT;
    List<Flame> frame = new ArrayList<Flame>();
    private Sound nobomb = new Sound();

    public Bomb(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, 48, 48, 1, gameWorld);
        setRigid(true);
        setTeamType(WEAPON_TEAM);
        setState(ALIVE);
        timeBegin = System.currentTimeMillis();

        bomb = CacheDataLoader.getInstance().getAnimation("bomb");
        nobomb.setFile("bom");  // load am thanh.
    }

    @Override
    public void attack() {

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
        if (getState() == ALIVE) {
            bomb.Update(System.nanoTime());
            bomb.draw((int) (getPosX() - getGameWorld().camera.getPosX() + 10 - getWidth()/2),
                    (int) (getPosY() - getGameWorld().camera.getPosY() - getHeight() /2 + 10), g2);
        }
//        drawBoundForCollisionWithEnemy(g2);
        if (getState() == BEHURT) {
            for (Flame flames : frame) {
                flames.draw(g2);
            }
        }
    }

    @Override
    public void Update() {
        // Check lần dầu va chạm với bom.
        ParticularObject other = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
        if (other != null) {
            if (other instanceof Player) {
            } else {
                setHaveMove(false); // Đã qua lần đầu.
            }
        } else {
            setHaveMove(false); // Đã qua.
        }

        // Check trạng thái của bom.
        timeHT = System.currentTimeMillis();
        if (timeHT - timeBegin > 2000 && getState() == ALIVE) {
            nobomb.play();
            setState(BEHURT);
            this.timeBegin = timeHT;
            checkWallOfFrame();
        } else if (timeHT - timeBegin > 900 && getState() == BEHURT) {
            setState(DEATH);
            getGameWorld().player.setCount_bomb(getGameWorld().player.getCount_bomb()  - 1);
        }
        if (getState() == BEHURT) {
            for (Flame flames : frame) {
                flames.Update();
            }
        }
    }

    public void checkWallOfFrame() {
        int scope = 0;
        // frame position.
        Rectangle rectPos = new Rectangle((int) (getPosX() - getWidth()/2),
                (int) (getPosY() - getHeight()/2), (int) getWidth(), (int) getHeight());
        Flame flame = new Flame(0,0,this, getGameWorld());
        flame.direction = flame.directionPos;
        flame.addFrame(rectPos);
        frame.add(flame);

        // Check frame top.
        Rectangle rectUp = new Rectangle((int) (getPosX() - getWidth()/2),
                (int) (getPosY() - getHeight()/2), (int) getWidth(), (int) getHeight());
        for (int i = 1; 48 * i <= getGameWorld().getScopeBom(); i++) {
            // Check va chạm.
            rectUp.y = rectUp.y - 48;
            ParticularObject object = getGameWorld().particularObjectManager.checkCollisionWithFire(rectUp);
            if (object instanceof Wall) {
                rectUp.y += 48;
                break;
            } else if (object instanceof Brick) {
                scope += 48;
                break;
            } else {
                scope += 48;
            }
        }
        rectUp.height = scope;
        flame = new Flame(0,0,this, getGameWorld());
        flame.direction = flame.directionUp;
        flame.addFrame(rectUp);
        frame.add(flame);

        // frame down.
        scope = 0;
        Rectangle rectDown = new Rectangle((int) (getPosX() - getWidth()/2),
                (int) (getPosY() - getHeight()/2), (int) getWidth(), (int) getHeight());
        rectDown.y += 48;
        for (int i = 1; 48 * i <= getGameWorld().getScopeBom(); i++) {
            // Check va chạm.
            ParticularObject object = getGameWorld().particularObjectManager.checkCollisionWithFire(rectDown);
            if (object instanceof Wall) {
                rectDown.y -= 48;
                break;
            }
            else if (object instanceof Brick) {
                scope += 48;
                rectDown.y += 48;
                break;
            } else {
                scope += 48;
            }
            rectDown.y += 48;
        }
        rectDown.y -= scope;
        rectDown.height = scope;
        flame = new Flame(0,0,this, getGameWorld());
        flame.direction = flame.directionDown;
        flame.addFrame(rectDown);
        frame.add(flame);

        // check left.
        scope = 0;
        Rectangle rectLeft = new Rectangle((int) (getPosX() - getWidth()/2),
                (int) (getPosY() - getHeight()/2), (int) getWidth(), (int) getHeight());
        for (int i = 1; 48 * i <= getGameWorld().getScopeBom(); i++) {
            // Check va chạm.
            rectLeft.x -= 48;
            ParticularObject object = getGameWorld().particularObjectManager.checkCollisionWithFire(rectLeft);
            if (object instanceof Wall) {
                rectLeft.x += 48;
                break;
            }
            else if (object instanceof Brick) {
                scope += 48;
                break;
            } else {
                scope += 48;
            }
        }
        rectLeft.width = scope;
        flame = new Flame(0,0,this, getGameWorld());
        flame.direction = flame.directionLeft;
        flame.addFrame(rectLeft);
        frame.add(flame);

        // check right.
        scope = 0;
        Rectangle rectRight = new Rectangle((int) (getPosX() - getWidth()/2),
                (int) (getPosY() - getHeight()/2), (int) getWidth(), (int) getHeight());
        rectRight.x += 48;
        for (int i = 1; 48 * i <= getGameWorld().getScopeBom(); i++) {
            // Check va chạm.
            ParticularObject object = getGameWorld().particularObjectManager.checkCollisionWithFire(rectRight);
            if (object instanceof Wall) {
                rectRight.x -= 48;
                break;
            }
            else if (object instanceof Brick) {
                scope += 48;
                rectRight.x += 48;
                break;
            } else {
                scope += 48;
            }
            rectRight.x += 48;
        }
        rectRight.x -= scope;
        rectRight.width = scope;
        flame = new Flame(0,0,this, getGameWorld());
        flame.direction = flame.directionRight;
        flame.addFrame(rectRight);
        frame.add(flame);
    }
}
