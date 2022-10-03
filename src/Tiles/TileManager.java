package Tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static gameFunction.Constant.titleSize;

public class TileManager {
    GamePanel gp;
    public Tiles[] tiles;
    public String[] map = new String[1600];  // Map world.

    public int lever;
    public int maxWorldMapX;
    public int maxWorldMapY;

    public TileManager(GamePanel gp) throws IOException {
        this.gp = gp;
        tiles = new Tiles[10];
        getTileImage();
        loadMap();
    }

    public void getTileImage() throws IOException {
        BufferedImage images;
        images = ImageIO.read(getClass().getClassLoader().getResourceAsStream("image/classic.png"));

        // load wall.
        tiles[0] = new Tiles();
        tiles[0].image = images.getSubimage(80, 0, 16, 16);
        tiles[0].collision = true;

        // load grass.
        tiles[1] = new Tiles();
        tiles[1].image = images.getSubimage(96, 0, 16, 16);

        // load brick.
        tiles[2] = new Tiles();
        tiles[2].image = images.getSubimage(112, 0, 16, 16);
        tiles[2].collision = true;
    }

    public void loadMap() throws IOException {
        InputStream is = getClass().getResourceAsStream("/map/lever1.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String[] s = br.readLine().split(" ");
        lever = Integer.parseInt(s[0]);
        maxWorldMapX = Integer.parseInt(s[1]);
        maxWorldMapY = Integer.parseInt(s[2]);
        for (int i = 0; i < maxWorldMapX; i++) {
            map[i] = br.readLine();
        }
    }
    public void drawMap(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        for (int i = 0; i < maxWorldMapX; i++) {
            for (int j = 0; j < maxWorldMapY; j++) {
                if (map[i].charAt(j) == '#') {
                    g2.drawImage(tiles[0].image, worldCol, worldRow, titleSize, titleSize,null);
                } else if (map[i].charAt(j) == ' ') {
                    g2.drawImage(tiles[1].image, worldCol, worldRow, titleSize, titleSize,null);
                } else if (map[i].charAt(j) == '*') {
                    g2.drawImage(tiles[2].image, worldCol, worldRow, titleSize, titleSize,null);
                }
                worldCol += titleSize;
            }
            worldCol = 0;
            worldRow += titleSize;
        }
    }
}
