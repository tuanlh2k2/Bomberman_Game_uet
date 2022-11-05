package gameobject.PaticularObject.Entity.Enemy.AI;

import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Entity;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AI {
    private long setTimeReset = System.currentTimeMillis();;
    private GameWorld gameWorld;
    private Entity entity;
    protected boolean check;
    protected Integer direction[];
    protected int length;
    // contructor basic.
    public AI(Entity entity, GameWorld gameWorld) {
        this.entity = entity;
        this.gameWorld = gameWorld;
    }

    // AI basic.
    public void checkDirection() {
        // reset.
        direction = new Integer[4];
        length = 0;
        for (int i = 0; i <=3; i++) {
            direction[i] = -1;
        }

        // Check top.
        Rectangle rectUp = entity.getBoundForCollisionWithMap();
        rectUp.y -= 48;
        check = gameWorld.particularObjectManager.checkCollisionWithRigid(rectUp);
        if (check == false) {
            direction[ParticularObject.TOP_DIR]++;
        }

        // Check down.
        Rectangle rectDown = entity.getBoundForCollisionWithMap();
        rectDown.y += 48;
        check = gameWorld.particularObjectManager.checkCollisionWithRigid(rectDown);
        if (check == false) {
            direction[ParticularObject.DOWN_DIR]++;
        }

        // Check left.
        Rectangle rectLeft = entity.getBoundForCollisionWithMap();
        rectLeft.x -= 48;
        check = gameWorld.particularObjectManager.checkCollisionWithRigid(rectLeft);
        if (check == false) {
            direction[ParticularObject.LEFT_DIR] ++;
        }

        // Check right.
        Rectangle rectRight = entity.getBoundForCollisionWithMap();
        rectRight.x += 48;
        check = gameWorld.particularObjectManager.checkCollisionWithRigid(rectRight);
        if (check == false) {
            direction[ParticularObject.RIGHT_DIR]++;
        }
    }

    // Random các hướng có thể đi.
    public void AI_Basic() {
        if (entity.getHaveCollision() == true || System.currentTimeMillis()- setTimeReset > 6000) {
            setTimeReset = System.currentTimeMillis();
            checkDirection();
            randomDirection();
        }
    }

    // biết đuổi theo người khi có đường đuổi.
    public void AI_Pursue() {
        checkDirection();
        int x = (int) (entity.getPosX()/48);
        int y = (int) (entity.getPosY()/48);
        int toX = (int) (gameWorld.player.getPosX()/48);
        int toY = (int) (gameWorld.player.getPosY()/48);
        if (x < toX && direction[ParticularObject.RIGHT_DIR] == 0) {
            entity.setDirection(ParticularObject.RIGHT_DIR);
        } else if (x > toX && direction[ParticularObject.LEFT_DIR] == 0) {
            entity.setDirection(ParticularObject.LEFT_DIR);
        } else if (y > toY && direction[ParticularObject.TOP_DIR] == 0) {
            entity.setDirection(ParticularObject.TOP_DIR);
        } else if (y < toY && direction[ParticularObject.DOWN_DIR] == 0) {
            entity.setDirection(ParticularObject.DOWN_DIR);
        } else {
            AI_Basic();
        }
    }

    public void randomDirection() {
        Random random = new Random();
        int leng = 0;
        Integer[] dir = new Integer[4];
        for (int i = 0; i <=3; i++) {
            if (direction[i] == 0)
                dir[leng++] = i;
        }
        int rand = random.nextInt(1001) % leng;
        entity.setDirection(dir[rand]);
    }
}
