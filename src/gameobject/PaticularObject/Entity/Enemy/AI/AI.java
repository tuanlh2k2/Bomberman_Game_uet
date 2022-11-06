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
    protected StraightLine straightLine;
    public AStart pathFinder;


    // contructor basic.
    public AI(Entity entity, GameWorld gameWorld) {
        this.entity = entity;
        this.gameWorld = gameWorld;

        pathFinder = new AStart(gameWorld);
        straightLine = new StraightLine(entity, gameWorld);
    }

    // AI basic.
    public void checkDirection() {
        // reset.
        direction = new Integer[4];
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
        if (entity.getHaveCollision() == true || System.currentTimeMillis() - setTimeReset > 6000) {
            setTimeReset = System.currentTimeMillis();
            checkDirection();
            randomDirection();
        }
        if (entity.getDirection() == entity.LEFT_DIR) {
            entity.setSpeedX(- entity.getRunSpeed());
        } else if (entity.getDirection() == entity.RIGHT_DIR) {
            entity.setSpeedX(entity.getRunSpeed());
        } else if (entity.getDirection() == entity.TOP_DIR) {
            entity.setSpeedY(- entity.getRunSpeed());
        } else if (entity.getDirection() == entity.DOWN_DIR) {
            entity.setSpeedY(entity.getRunSpeed());
        }
    }

    // biết đuổi theo người khi có đường đuổi.
    public void AI_Pursue() {
        straightLine.findWay();

        if (straightLine.checkLine == false || entity.getHaveCollision() == true) {
            AI_Basic();

            if (entity.getDirection() == entity.LEFT_DIR) {
                entity.setSpeedX(-entity.getRunSpeed());
            } else if (entity.getDirection() == entity.RIGHT_DIR) {
                entity.setSpeedX(entity.getRunSpeed());
            } else if (entity.getDirection() == entity.TOP_DIR) {
                entity.setSpeedY(- entity.getRunSpeed());
            } else if (entity.getDirection() == entity.DOWN_DIR) {
                entity.setSpeedY(entity.getRunSpeed());
            }
        }
    }

    // random huong.
    public void randomDirection() {
        Random random = new Random();
        int leng = 0;
        Integer[] dir = new Integer[4];
        for (int i = 0; i <= 3; i++) {
            if (direction[i] == 0)
                dir[leng++] = i;
        }
        if (leng == 1) {
            entity.setDirection(dir[0]);
        }else if (leng > 0 ) {
            int rand = random.nextInt(1001) % leng;
            entity.setDirection(dir[rand]);
        }
    }

    // Tim duong di theo thuat toan A*.
    public void searchPath(int goalPosX, int goalPosY) {
        int startPosX = (int) entity.getPosX()  / 48;
        int startPosY = (int) entity.getPosY() / 48;
        pathFinder.setNodes(startPosX, startPosY, goalPosX, goalPosY);
        if (pathFinder.search() == true) {
            // Next worldX & worldY.
            int nextX = pathFinder.pathList.get(0).posX;
            int nextY = pathFinder.pathList.get(0).posY;

            //Entity's solidArea position.
            int tileSize = GameWorld.tileSize;


            if (nextX == startPosX) {
                entity.setPosX(nextX * tileSize + tileSize/2);
            }
            if (nextY == startPosY) {
                entity.setPosY(nextY * tileSize + tileSize/2);
            }
            if (startPosY < nextY) {
                entity.setDirection(entity.DOWN_DIR);
            } else if (startPosY > nextY) {
                entity.setDirection(entity.TOP_DIR);
            }
            else if (startPosX < nextX) {
                entity.setDirection(entity.RIGHT_DIR);
            } else if (startPosX > nextX) {
                entity.setDirection(entity.LEFT_DIR);
            }
        } else {
            AI_Basic();
        }

        // set speed.
        if (entity.getDirection() == entity.LEFT_DIR) {
            entity.setSpeedX(-entity.getRunSpeed());
        } else if (entity.getDirection() == entity.RIGHT_DIR) {
            entity.setSpeedX(entity.getRunSpeed());
        } else if (entity.getDirection() == entity.TOP_DIR) {
            entity.setSpeedY(- entity.getRunSpeed());
        } else if (entity.getDirection() == entity.DOWN_DIR) {
            entity.setSpeedY(entity.getRunSpeed());
        }
    }
}
