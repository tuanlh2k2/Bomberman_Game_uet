package emtity.move;

import emtity.Emtity;
import main.GamePanel;
import main.KeyControl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Emtity {
    KeyControl keyControl;
    GamePanel gp;

    public Player(GamePanel gp, KeyControl keyControl) {
        this.gp = gp;
        this.keyControl = keyControl;
        setDefautValues();
        getPlayerImage();
    }

    public void setDefautValues() {
        positionX = gp.titleSize;
        positionY = gp.titleSize;
        speed = 3;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_up.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_up_1.png"));
            up3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_up_2.png"));

            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_down.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_down_1.png"));
            down3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_down_2.png"));

            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_left.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_left_1.png"));
            left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_left_2.png"));

            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_right.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_right_1.png"));
            right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyControl.upPressed == true || keyControl.downPressed == true
                || keyControl.leftPressed == true || keyControl.rightPressed == true) {
            if (keyControl.upPressed == true) {
                positionY -= speed;
                direction = "up";
            }
            if (keyControl.downPressed == true) {
                positionY += speed;
                direction = "down";
            }
            if (keyControl.leftPressed == true) {
                positionX -= speed;
                direction = "left";
            }
            if (keyControl.rightPressed == true) {
                positionX += speed;
                direction = "right";
            }

            spriteCounter++;
            if (spriteCounter > 6) {
                if (spriteNum == 3) {
                    spriteNum = 1;
                } else {
                    spriteNum ++;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) throws IOException {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                } else {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                } else {
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                } else {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                } else {
                    image = right3;
                }
                break;
        }
        g2.drawImage(image, positionX, positionY, gp.titleSize, gp.titleSize, null);
    }
}
