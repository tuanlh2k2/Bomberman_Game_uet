package gameobject.paticularObject.Entity.Enemy;

import effect.CacheDataLoader;
import gameobject.GameWorld;
import gameobject.paticularObject.Weapon. *;


import java.awt.*;
import java.util.Random;

public class Minvo extends Enemy {

    private long setTimeReset;
    private Integer[] direcsion = new Integer[4];
    private int length;
    private String[] map;
    private boolean MinvoState = true;
    Weapon bomb = new Bomb(getPosX(), getPosY(), getGameWorld());

    public Minvo(double posX, double posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        setDirection(TOP_DIR);
        setSpeedX(1.5);
        setSpeedY(1.5);

        map = getGameWorld().backgroundMap.map;
        left = CacheDataLoader.getInstance().getAnimation("lMinvo");
        right = CacheDataLoader.getInstance().getAnimation("rMinvo");
        die = CacheDataLoader.getInstance().getAnimation("Minvodie");
        stand = CacheDataLoader.getInstance().getAnimation("fMinvo");
    }

    public void Update() {
        super.Update();
    }

    public void run() {
        int x = (int) (getPosX());
        int y = (int) (getPosY());
        int xx = (int) (getPosX() / 48);
        int yy = (int) (getPosY() / 48);
        int toX = (int) ((getGameWorld().player.getPosX()));
        int toY = (int) ((getGameWorld().player.getPosY()));
        int toBombX = (int) ((bomb.getPosX()) - bomb.getScopeBom());
        int toBombY = (int) ((bomb).getPosY() - bomb.getScopeBom());
        //  System.out.println(getPosX() + " " + getGameWorld().player.getPosX());
        if (x == toX && y < toY && y > toBombY && checkWay(x/48, y/48, toX/48, toY/48) == true)  {
            setDirection(DOWN_DIR);
            setSpeedY(2);
        } else if (x == toX && y > toY && y < toBombY && checkWay(toX/48, toY/48, x/48, y/48) == true) {
            setDirection(TOP_DIR);
            setSpeedY(-2);
        } else if (y == toY && x < toX && x > toBombX && checkWay(x/48, y/48, toX/48, toY/48) == true) {
            setDirection(RIGHT_DIR);
            setSpeedX(2);
        } else if (y == toY && x > toX && x < toBombX && checkWay(toX/48, toY/48, x/48, y/48) == true) {
            setDirection(LEFT_DIR);
            setSpeedX(-2);
        }
        else {
            length = 0;
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
                if (map[yy].charAt(xx - 1) != '#' ||  map[yy].charAt(xx - 1) != '*') {
                    direcsion[length++] = LEFT_DIR;
                }

                Random random = new Random();
                int ranNum = (random.nextInt(101) + 1) % length;
                setDirection(ranNum);
            }
            if (getDirection() == LEFT_DIR) {
                setSpeedX(-1.5);
            } else if (getDirection() == RIGHT_DIR) {
                setSpeedX(1.5);
            } else if (getDirection() == TOP_DIR) {
                setSpeedY(-1.5);
            } else if (getDirection() == DOWN_DIR) {
                setSpeedY(1.5);
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

    public boolean isMinvoState() {
        return MinvoState;
    }

    public void setMinvoState(boolean minvoState) {
        MinvoState = minvoState;
    }
}
