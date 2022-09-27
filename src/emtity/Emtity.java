package emtity;

import main.GamePanel;
import main.KeyControl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Emtity {
    protected int positionX;
    protected int positionY;
    protected int speed;
    protected BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    protected String direction;
    protected int spriteCounter = 0;
    protected int spriteNum = 1;

}
