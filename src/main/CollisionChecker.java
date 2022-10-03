package main;

import Tiles.TileManager;
import emtity.Entity;

public class CollisionChecker {
    GamePanel gp;
    TileManager tileM;
    public CollisionChecker(GamePanel gp, TileManager tileM) {
        this.gp = gp;
        this.tileM = tileM;
    }

    /**
     * CheckTilet.
     */
    public void tileCheck(Entity entity) {
        int positionX1 = entity.worldX + entity.solidArea.x;
        int positionX2 = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int positionY1 = entity.worldY + entity.solidArea.y;
        int positionY2 = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int stepNextX1;
        int stepNextX2;
        int stepNextY1;
        int stepNextY2;


        switch (entity.direction) {
            case "up":
                stepNextX1 = positionX1 / gp.titleSize;
                stepNextX2 = positionX2 / gp.titleSize;
                stepNextY1 = (positionY1 - gp.player.speed)/gp.titleSize;
                System.out.println(stepNextX1);
                if (tileM.map[stepNextY1].charAt(stepNextX1) == '#' || tileM.map[stepNextY1].charAt(stepNextX1) == '*'
                        || tileM.map[stepNextY1].charAt(stepNextX2) == '#' || tileM.map[stepNextY1].charAt(stepNextX2) == '*' ) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                stepNextX1 = positionX1 / gp.titleSize;
                stepNextX2 = positionX2 / gp.titleSize;
                stepNextY2 = (positionY2 + gp.player.speed)/gp.titleSize;

                if (tileM.map[stepNextY2].charAt(stepNextX1) == '#' || tileM.map[stepNextY2].charAt(stepNextX2) == '#' ||
                        tileM.map[stepNextY2].charAt(stepNextX1) == '*' || tileM.map[stepNextY2].charAt(stepNextX2) == '*') {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                stepNextX2 = (positionX2 + gp.player.speed) / gp.titleSize;
                stepNextY1 = positionY1 / gp.titleSize;
                stepNextY2 =  positionY2 / gp.titleSize;
                if (tileM.map[stepNextY1].charAt(stepNextX2) == '#' || tileM.map[stepNextY1].charAt(stepNextX2) == '*'
                        || tileM.map[stepNextY2].charAt(stepNextX2) == '#' || tileM.map[stepNextY2].charAt(stepNextX2) == '*' ) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                stepNextX1 = (positionX1 - gp.player.speed) / gp.titleSize;
                stepNextY1 = positionY1 / gp.titleSize;
                stepNextY2 =  positionY2 / gp.titleSize;
                if (tileM.map[stepNextY1].charAt(stepNextX1) == '#' || tileM.map[stepNextY1].charAt(stepNextX1) == '*'
                        || tileM.map[stepNextY2].charAt(stepNextX1) == '#' || tileM.map[stepNextY2].charAt(stepNextX1) == '*') {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}