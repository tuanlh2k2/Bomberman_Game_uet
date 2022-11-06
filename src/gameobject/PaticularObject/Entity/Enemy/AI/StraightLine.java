package gameobject.PaticularObject.Entity.Enemy.AI;

import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Bomber.Player;
import gameobject.PaticularObject.Entity.Entity;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;
import java.util.Random;

public class StraightLine {
    private long setTimeReset;
    private Integer[] direcsion = new Integer[4];
    private String[] map;
    private Entity entity;
    private GameWorld gameWorld;
    protected boolean checkLine = true;

    public StraightLine(Entity entity, GameWorld gameWorld) {
        this.entity = entity;
        this.gameWorld = gameWorld;
        map = gameWorld.backgroundMap.map;
    }

    public void findWay() {
        int x = (int) ( (entity.getPosX() - 24)/ 48);
        int y = (int) ( (entity.getPosY() - 24)/ 48);
        int toX = (int) ((gameWorld.player.getPosX())/ 48);
        int toY = (int) ((gameWorld.player.getPosY())/ 48);

        if (x == toX && y < toY && checkWay(x, y, toX, toY) == true)  {
            entity.setDirection(entity.DOWN_DIR);
            entity.setSpeedY(2);
        } else if (x == toX && y > toY && checkWay(toX, toY, x, y) == true) {
            entity.setDirection(entity.TOP_DIR);
            entity.setSpeedY(-2);
        } else if (y == toY && x < toX && checkWay(x, y, toX, toY) == true) {
            entity.setDirection(entity.RIGHT_DIR);
            entity.setSpeedX(2);
        } else if (y == toY && x > toX && checkWay(toX, toY, x, y) == true) {
            entity.setDirection(entity.LEFT_DIR);
            entity.setSpeedX(-2);
        } else {
            checkLine = false;
        }
    }

    public boolean checkWay(int x, int y, int xx, int yy) {
        if (x == xx) {
            for (int i = y ; i < yy; i++) {
                Rectangle rectCheck = new Rectangle(x * 48, i * 48, GameWorld.tileSize, GameWorld.tileSize);
                ParticularObject check = gameWorld.particularObjectManager.checkCollisionWithRect(rectCheck);
                if (check != null) {
                    if (check.getRigid() == true) {
                        return false;
                    }
                }
            }
            return true;
        } else if (y == yy) {
            for (int i = x; i < xx; i++) {
                Rectangle rectCheck = new Rectangle(i * 48, y * 48, GameWorld.tileSize, GameWorld.tileSize);
                ParticularObject check = gameWorld.particularObjectManager.checkCollisionWithRect(rectCheck);
                if (check != null) {
                    if (check.getRigid() == true) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
