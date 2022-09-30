package Tiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Wall extends Tiles {
    protected boolean[][] wall = new boolean[screenWidth][screenHeight];

    BufferedImage image1;
    BufferedImage image2;

    public Wall() {
        loadMap();
        initWall();
        addWall();
    }

    public void initWall() {
        for (int i = 0; i < maxScreenRow; i++) {
            for (int j = 0; j < maxScreenColum; j++) {
                wall[i][j] = false;
            }
        }
    }

    public void addWall() {
        for (int i = 0; i < maxScreenRow; i++) {
            for (int j = 0; j < maxScreenColum; j++) {
                if (mapTileNum[i][j].equals("1")) {
                    wall[i][j] = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2) throws IOException {
        // Xu ly ngoai le
        image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("image/classic.png"));
        image1 = image.getSubimage(80, 0, 16, 16);
        image2 = image.getSubimage(96, 0, 16, 16);
        int x = 0;
        int y = 0;

        for (int i = 0; i < maxScreenRow; i++) {
            for (int j = 0; j < maxScreenColum; j++) {
                if (mapTileNum[i][j].equals("#")) {
                    g2.drawImage(image1, x, y, titleSize, titleSize, null);
                } else if (mapTileNum[i][j].equals("2")) {
                    g2.drawImage(image2, x, y, titleSize, titleSize, null);
                }
                x += titleSize;
            }
            y += titleSize;
            x = 0;
        }
    }
}
