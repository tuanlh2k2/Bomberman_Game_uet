package emtity;

import main.GamePanel;
import main.KeyControl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    KeyControl keyControl;
    GamePanel gp;
    BufferedImage image;
    // Gốc tọa độ
    public Player(GamePanel gp, KeyControl keyControl) {
        this.gp = gp;
        this.keyControl = keyControl;
        solidArea = new Rectangle(4, 5, 27, 38); // Xu ly va cham
        setDefautValues();
        getPlayerImage();
    }

    public void setDefautValues() {
        worldX = gp.titleSize;
        worldY = gp.titleSize * 5;
        speed = 2;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("image/classic.png"));
            up1 = image.getSubimage(0, 0, 16, 16);
            up2 = image.getSubimage(0,16,16,16);
            up3 = image.getSubimage(0,32,16,16);

            down1 = image.getSubimage(32,0,16,16);
            down2 = image.getSubimage(32,16,16,16);
            down3 = image.getSubimage(32,32,16,16);

            right1 = image.getSubimage(16,0,16,16);
            right2 = image.getSubimage(16,16,16,16);
            right3 = image.getSubimage(16,32,16,16);

            left1 = image.getSubimage(48, 0, 16, 16);
            left2 = image.getSubimage(48, 16, 16, 16);
            left3 = image.getSubimage(48, 32, 16, 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyControl.upPressed == true || keyControl.downPressed == true
                || keyControl.leftPressed == true || keyControl.rightPressed == true) {
            if (keyControl.upPressed == true) {
                direction = "up";
            }
            if (keyControl.downPressed == true) {
                direction = "down";
            }
            if (keyControl.leftPressed == true) {
                direction = "left";
            }
            if (keyControl.rightPressed == true) {
                direction = "right";
            }

            //  Check tile collision.
            collisionOn = false;
            gp.cChecker.tileCheck(this);

            // Check collision is false, player can moving.

            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
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
        g2.drawImage(image, worldX, worldY, gp.titleSize, gp.titleSize, null);
    }
}
