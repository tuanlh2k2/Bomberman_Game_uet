package gameobject.PaticularObject.Entity.Enemy.AI;

import gameobject.GameFuncion.BackgroundMap;
import gameobject.GameWorld;
import gameobject.PaticularObject.Entity.Entity;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;
import java.util.ArrayList;

public class PathFinder {
    private int maxWorldCol;
    private int maxWorldRow;
    GameWorld gameWorld;
    Node [][] node;
    Boolean[][] checkColistion;
    ArrayList <Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        maxWorldCol = gameWorld.backgroundMap.map[0].length(); // 31
        maxWorldRow = gameWorld.backgroundMap.map.length; //13
        instantiateNodes();
    }

    public void instantiateNodes() {
        node = new Node[13][31];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 31; j++) {
                node[i][j] = new Node(i, j);
            }
        }
    }

    public void resetNodes() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 31; j++) {
                node[i][j].open = false;
                node[i][j].solid = false;
                node[i][j].checked = false;
            }
        }

        // Reset other setting.
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startPosX, int startPosY, int goalPosX, int goalPosY) {
        resetNodes();
        setCheckColistion();
        //set start node and goal node.
        startNode = node[startPosY][startPosX];
        currentNode = startNode;
        goalNode = node[goalPosY][goalPosX];
        openList.add(currentNode);

//        System.out.println(currentNode.row + " " + currentNode.col);
        for (int i = 0; i < 13; i++) {
            // CHECK TITLES.
            for (int j = 0; j < 31; j++) {
                if (checkColistion[i][j] == true) {
                    node[i][j].solid = true;
                } else {
                    node[i][j].solid = false;
                }
                // SET COST.
                getCost(node[i][j]);
            }
        }
    }

    public void getCost(Node node) {

        // G cost.
        int xDistance = Math.abs(node.posX - startNode.posX);
        int yDistance = Math.abs(node.posY - startNode.posY);
        node.gCost = xDistance + yDistance;

        // H cost.
        xDistance = Math.abs(node.posX - goalNode.posX);
        yDistance = Math.abs(node.posY - goalNode.posY);
        node.hCost = xDistance + yDistance;

        // F cost.
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (goalReached == false && step < 500) {
            int posX = currentNode.posX;
            int posY = currentNode.posY;
            // Check the current code.
            currentNode.checked = true;
            openList.remove(currentNode);

            if (posY - 1 >= 0) {
                openNode(node[posY - 1][posX]);
            }
            if (posY + 1 < maxWorldRow) {
                openNode(node[posY + 1][posX]);
            }
            if (posX - 1 >= 0) {
                openNode(node[posY][posX - 1]);
            }
            if (posX + 1 < maxWorldCol) {
                openNode(node[posY][posX + 1]);
            }

            // Find the best node.
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                } else if (openList.get(i).fCost == bestNodefCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            // ........
            if (openList.size() == 0) {
                break;
            }

            // ...........
            currentNode = openList.get(bestNodeIndex);
            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }

    public void openNode(Node node) {
        if (node.open == false && node.checked == false && node.solid == false) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackThePath() {
        Node current = goalNode;
        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }

    public void setCheckColistion() {
        checkColistion = new Boolean[maxWorldRow][maxWorldCol];
        int tileSize = GameWorld.tileSize;
        for (int i = 0; i < maxWorldRow; i++) {
            for (int j = 0; j < maxWorldCol; j++) {
                Rectangle rectCheck = new Rectangle(j * tileSize,
                        i * tileSize, tileSize, tileSize);
                ParticularObject check = gameWorld.particularObjectManager.checkCollisionWithRect(rectCheck);
                if (check == null) {
                    checkColistion[i][j] = false;
                } else {
                    if (check.getRigid() == false) {
                        checkColistion[i][j] = false;
                    } else {
                        checkColistion[i][j] = true;
                    }
                }
            }
        }
    }
}
