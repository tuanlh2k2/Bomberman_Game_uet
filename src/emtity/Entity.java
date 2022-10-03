package emtity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int worldX;
    public int worldY;
    public int speed;
    protected BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}