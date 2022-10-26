package gameobject.paticularObject.Entity.Enemy;

import effect.Animation;
import effect.CacheDataLoader;
import gameobject.paticularObject.ParticularObject;
import gameobject.GameWorld;
import gameobject.paticularObject.ParticularObjectManager;
import gameobject.paticularObject.Tile.Brick;


import java.awt.*;
import java.util.Random;

public class Kondoria extends Enemy {
    private long setTimeReset;
    private Integer[] direcsion = new Integer[4];
    private int length;
    private String[] map;
    private boolean state = true;
    private Animation after;
    public Kondoria(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY,  gameWorld);
        setDirection(DOWN_DIR);
        setSpeedX(1);
        setSpeedY(1);

        map = getGameWorld().backgroundMap.map;
        left = CacheDataLoader.getInstance().getAnimation("lKondoria");
        right = CacheDataLoader.getInstance().getAnimation("rKondoria");
        die = CacheDataLoader.getInstance().getAnimation("Kondoriadie");
        stand = CacheDataLoader.getInstance().getAnimation("fKondoria");

        state = true;
    }

    @Override
    public void Update() {
        super.Update();
        if (getState() == DEATH) {
            Brick brick = new Brick(getPosX(), getPosY(), getGameWorld());
            getGameWorld().particularObjectManager.addObject(brick);
        }
    }

    @Override
    public void run() {
        int x = (int) (getPosX());
        int y = (int) (getPosY());
        int xx = (int) (getPosX() / 48);
        int yy = (int) (getPosY() / 48);
        int toX = (int) ((getGameWorld().player.getPosX()));
        int toY = (int) ((getGameWorld().player.getPosY()));
        //  System.out.println(getPosX() + " " + getGameWorld().player.getPosX());
        if (x == toX && y < toY && checkWay(x/48, y/48, toX/48, toY/48) == true)  {
            setDirection(DOWN_DIR);
            setSpeedY(2);
        } else if (x == toX && y > toY && checkWay(toX/48, toY/48, x/48, y/48) == true) {
            setDirection(TOP_DIR);
            setSpeedY(-2);
        } else if (y == toY && x < toX && checkWay(x/48, y/48, toX/48, toY/48) == true) {
            setDirection(RIGHT_DIR);
            setSpeedX(2);
        } else if (y == toY && x > toX && checkWay(toX/48, toY/48, x/48, y/48) == true) {
            setDirection(LEFT_DIR);
            setSpeedX(-2);
        }
        else {
            length = 0;
            Random random = new Random();
            if (getHaveCollision() == true || System.currentTimeMillis() - setTimeReset > 5000) {
                setTimeReset = System.currentTimeMillis();
                if (map[yy + 1].charAt(xx) != '#' || map[yy + 1].charAt(xx) != '*') {
                    direcsion[length++] = DOWN_DIR;
                }
                if (map[yy - 1].charAt(xx) != '#' || map[yy - 1].charAt(xx) != '*') {
                    direcsion[length++] = TOP_DIR;
                }
                if (map[yy].charAt(xx + 1) != '#' || map[yy].charAt(xx + 1) != '*') {
                    direcsion[length++] = RIGHT_DIR;
                }
                if (map[yy].charAt(Math.abs(xx - 1)) != '#' ||  map[yy].charAt(Math.abs(xx - 1)) != '*') {
                    direcsion[length++] = LEFT_DIR;
                }

                int ranNum = (random.nextInt(101) + 1) % length;
                setDirection(ranNum);
            }

            if (getDirection() == LEFT_DIR) {
                setSpeedX(-1);
            } else if (getDirection() == RIGHT_DIR) {
                setSpeedX(1);
            } else if (getDirection() == TOP_DIR) {
                setSpeedY(-1);
            } else if (getDirection() == DOWN_DIR) {
                setSpeedY(1);
            }
        }
    }

    @Override
    public void stopRun() {
    }

    @Override
    public void attack() {
    }

    public String[] getMap() {
        return map;
    }

    public void setMap(String[] map) {
        this.map = map;
    }

    public boolean checkWay(int x, int y, int xx, int yy) {
        if (x == xx) {
            for (int i = y; i < yy; i++) {
                if (map[i].charAt(x) != ' ') {
                    return false;
                }
            }
            return true;
        } else if (y == yy) {
            for (int i = x; i < xx; i++) {
                if (map[yy].charAt(i) != ' ') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
