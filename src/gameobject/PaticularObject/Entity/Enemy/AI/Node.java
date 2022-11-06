package gameobject.PaticularObject.Entity.Enemy.AI;

public class Node {
    Node parent;
    public int posX;
    public int posY;
    int gCost;
    int hCost;
    int fCost;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int posY, int posX) {
        this.posX = posX;
        this.posY = posY;
    }
}