package gameFunction;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Image {
    private int positionX;
    private int positionY;
    private String key;

    public List<BufferedImage> image = new ArrayList<BufferedImage>();

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}