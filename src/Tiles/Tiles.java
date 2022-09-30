package Tiles;

import gameFunction.Constant;
import gameFunction.LoadMap;

import java.awt.image.BufferedImage;

public abstract class Tiles extends LoadMap implements Constant {
    protected int positionX;
    protected int positionY;
    protected BufferedImage image;
    protected boolean collision = false; // va chạm.
}
