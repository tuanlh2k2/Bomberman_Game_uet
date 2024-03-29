package effect;

import gameobject.GameFuncion.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Muc dic dung de tao ra cac buc hinh nho => tao ra cac anh dong.
 */
public class FrameImage {
    private String name;
    private BufferedImage image;

    /**
     * This is contructor.
     */
    public FrameImage(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }

    public FrameImage() {
        this.name = null;
        this.image = null;
    }

    // Copy du lieu => Muc dich la tao ra ban sao cua chinh no.
    // Khi tach ra nhieu frame thi ko co tinh trang trung vung nho.
    public FrameImage(FrameImage frameImage) {
        this.image = new BufferedImage(frameImage.getImageWidth(),
                frameImage.getImageHeight(), frameImage.image.getType());
        Graphics g = image.getGraphics();
        g.drawImage(frameImage.image, 0, 0, null);
        name = frameImage.name;
    }

    /**
     * drawImage.
     */
    public void draw(Graphics2D g2, int x, int y) {
        g2.drawImage(image, x - image.getWidth()/2, (y - image.getHeight())/2,48,48, null);
    }

    /**
     * getImage.
     */
    public int getImageWidth() {
        return image.getWidth();
    }

    public int getImageHeight() {
        return image.getHeight();
    }

    /**
     * getter and setter.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
