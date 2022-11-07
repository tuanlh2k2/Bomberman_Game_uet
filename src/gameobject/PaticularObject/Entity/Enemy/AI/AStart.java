package gameobject.PaticularObject.Entity.Enemy.AI;

import gameobject.GameWorld;
import gameobject.PaticularObject.ParticularObject;

import java.awt.*;
import java.util.ArrayList;

public class AStart {
    private int maxWorldCol; // Số cột của map.
    private int maxWorldRow; // Số hàng của map.
    private GameWorld gameWorld; // dùng để tương tác với các đối tượng khác.
    private Node [][] node; // Mảng các node của đối tượng.
    protected Boolean[][] checkColistion; // kiểm tra xem trong map có đối tượng nào có thể đi qua.
    ArrayList <Node> openList = new ArrayList<>(); // chứa các node đã đi qua.
    public ArrayList<Node> pathList = new ArrayList<>(); // đánh dấu địa chỉ.
    Node startNode, goalNode, currentNode; // node đầu tiên, node đích, node trung gian.
    boolean goalReached = false; // Chưa tìm thấy đích.
    int step = 0; // số bước tìm.

    public AStart(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        maxWorldCol = gameWorld.backgroundMap.map[0].length(); // 31
        maxWorldRow = gameWorld.backgroundMap.map.length; //13
        instantiateNodes();
    }

    // Khởi tạo các node.
    public void instantiateNodes() {
        node = new Node[maxWorldRow][maxWorldCol];
        for (int i = 0; i < maxWorldRow; i++) {
            for (int j = 0; j < maxWorldCol; j++) {
                node[i][j] = new Node(i, j);
            }
        }
    }

    // trước khi tìm sẽ reset lại các node.
    public void resetNodes() {
        for (int i = 0; i < maxWorldRow; i++) {
            for (int j = 0; j < maxWorldCol; j++) {
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

    // Cài đặt các node.
    public void setNodes(int startPosX, int startPosY, int goalPosX, int goalPosY) {
        resetNodes();
        setCheckColistion();
        // cài đặt node bắt đầu và node kết thúc.
        startNode = node[startPosY][startPosX];
        currentNode = startNode; // gán cho node đi qua đầu tiên sẽ là node bắt đầu.
        goalNode = node[goalPosY][goalPosX];
        openList.add(currentNode);

        // kiểm tra xem node đấy có đi qua được không (true => vật cứng).
        for (int i = 0; i < maxWorldRow; i++) {
            for (int j = 0; j < maxWorldCol; j++) {
                if (checkColistion[i][j] == true) {
                    node[i][j].solid = true;
                } else {
                    node[i][j].solid = false;
                }
                // cài đặt chi phí của node.
                getCost(node[i][j]);
            }
        }
    }

    public void getCost(Node node) {

        // G cost. => khoảng cách từ node đến node ban đầu.
        int xDistance = Math.abs(node.posX - startNode.posX);
        int yDistance = Math.abs(node.posY - startNode.posY);
        node.gCost = xDistance + yDistance;

        // H cost. => khoảng cách từ node đến node kết thúc.
        xDistance = Math.abs(node.posX - goalNode.posX);
        yDistance = Math.abs(node.posY - goalNode.posY);
        node.hCost = xDistance + yDistance;

        // F cost. (tổng chi phí).
        node.fCost = node.gCost + node.hCost;
    }

    // Tìm kiếm đường đi theo thuật toán.
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

            // nếu không còn đường để đi => không có đường.
            if (openList.size() == 0) {
                break;
            }

            // chọn node có chi phí đi thấp nhất để kiểm tra.
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
