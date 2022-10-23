package gameobject.paticularObject;

import gameobject.GameWorld;
import gameobject.paticularObject.ParticularObject;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Quan ly tat ca cac doi tuong trong game.
 */
public class ParticularObjectManager {
    protected List<ParticularObject> particularObjects;
    private GameWorld gameWorld;

    public ParticularObjectManager(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        /**
         * Khoi tao o day theo kieu da luong => tranh xung dot tai nguyen khi goi ham.
         */
        particularObjects = Collections.synchronizedList(new LinkedList<ParticularObject>());
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    // Them mot doi tuong vao bang dieu khien.
    public void addObject(ParticularObject particularObject) {
        // synchronized => xu ly giong he dieu hanh => FIFS.
        synchronized(particularObjects) {
            particularObjects.add(particularObject);
        }
    }

    // Xoa mot doi tuong khoi danh sach.
    public void RemoveObject(ParticularObject particularObject) {
        synchronized (particularObjects) {
            for (int i = 0; i < particularObjects.size(); i++) {
                ParticularObject tmpOj =particularObjects.get(i);
                if (tmpOj == particularObject) {
                    particularObjects.remove(i);
                }
            }
        }
    }

    // Kiem tra va cham voi cac doi tuong khac.
    public ParticularObject getCollisionWithEnemyObject(ParticularObject object) {
        synchronized (particularObjects) {
            for (int i = 0; i < particularObjects.size(); i++) {
                ParticularObject tmpOj = particularObjects.get(i);
                // Kiem tra xem hai hinh bao cua 2 doi tuong co va cham nhau hay khong ?
                if (object.getTeamType() != tmpOj.getTeamType() && object.getBoundForCollisionWithEnemy().intersects(tmpOj.getBoundForCollisionWithEnemy())) {
                    return tmpOj;
                }
            }
        }
        return null;
    }

    // Update cua ta ca doi tuong.
    public void UpdateObjects() {
        synchronized (particularObjects) {
            for (int i = 0; i < particularObjects.size(); i++) {
                ParticularObject object = particularObjects.get(i);
                object.Update();
                // Neu doi tuong chet => xoa doi tuong.
                if (object.getState() == ParticularObject.DEATH) {
                    particularObjects.remove(i);
                }
            }
        }
    }

    // Ve doi tuong.
    public void draw(Graphics2D g2) {
        synchronized (particularObjects) {
            for (ParticularObject object : particularObjects) {
                object.draw(g2);
            }
        }
    }

    public ParticularObject checkCollisionWithFire(ParticularObject particularObject, Rectangle rect) {
        synchronized (particularObjects) {
            for (ParticularObject check : particularObjects) {
                if (check != particularObject && check.getBoundForCollisionWithEnemy().intersects(rect)) {
                    return check;
                }
            }
            return null;
        }
    }
}
