package effect;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    private String name;
    private boolean isRepeated; // Kiem tra trang thai --> false --> hinh se khong lap lai.
    private ArrayList<FrameImage> frameImages;
    private int currentFrame; // chi frame hien tai dang duoc ve tren man hinh. (1 -> 3)
    private ArrayList<Boolean> ignoreFrames; // trong qua trinh chay hinh anh thi se huy bo mot so hinh anh.
    private ArrayList<Double> delayFrame; // Chia thoi gian delay giua cac frame.
    private long beginTime;
    private boolean drawRectFrame;

    /**
     * This is contructor.
     */
    public Animation() {
        delayFrame = new ArrayList<Double>();
        beginTime = 0;
        currentFrame = 0;
        ignoreFrames = new ArrayList<Boolean>();
        frameImages = new ArrayList<FrameImage>();
        drawRectFrame = false;
        isRepeated = true;
    }

    public Animation(Animation animation) {
        this.beginTime = animation.beginTime;
        this.currentFrame = animation.currentFrame;
        this.drawRectFrame = animation.drawRectFrame;
        this.isRepeated = animation.isRepeated;

        delayFrame = new ArrayList<Double>();
        for (Double d : animation.delayFrame) {
            delayFrame.add(d);
        }

        ignoreFrames = new ArrayList<Boolean>();
        for (boolean b : animation.ignoreFrames) {
            ignoreFrames.add(b);
        }

        frameImages = new ArrayList<FrameImage>();
        for (FrameImage f : animation.frameImages) {
            frameImages.add(new FrameImage(f));
        }
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

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public ArrayList<FrameImage> getFrameImages() {
        return frameImages;
    }

    public void setFrameImages(ArrayList<FrameImage> frameImages) {
        this.frameImages = frameImages;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    /**
     * Cai dat hien thi cho frame => neu truyen sai thi se in ra frame 0.
     */
    public void setCurrentFrame(int currentFrame) {
        if (currentFrame >=0 && currentFrame <= frameImages.size()) {
            this.currentFrame = currentFrame;
        } else {
            this.currentFrame = 0;
        }
    }

    public ArrayList<Boolean> getIgnoreFrames() {
        return ignoreFrames;
    }

    public void setIgnoreFrames(ArrayList<Boolean> ignoreFrames) {
        this.ignoreFrames = ignoreFrames;
    }

    public ArrayList<Double> getDelayFrame() {
        return delayFrame;
    }

    public void setDelayFrame(ArrayList<Double> delayFrame) {
        this.delayFrame = delayFrame;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public boolean isDrawRectFrame() {
        return drawRectFrame;
    }

    public void setDrawRectFrame(boolean drawRectFrame) {
        this.drawRectFrame = drawRectFrame;
    }

    /**
     * Truy xuat mang isIgnoreFrame => kiem tra xem co bi null ko.
     */
    public boolean isIgnoreFrame(int id) {
        return ignoreFrames.get(id);
    }

    /**
     * Huy bo su lap lai cua frame thu id.
     */
    public void setIgnoreFrame(int id) {
        if (id >= 0 && id < ignoreFrames.size()) {
            ignoreFrames.set(id, true);
        }
    }

    /**
     * Cai dat su lap lai cua mot frame thu id.
     */
    public void unIgnoreFrame(int id) {
        if (id >= 0 && id < ignoreFrames.size()) {
            ignoreFrames.set(id, false);
        }
    }

    /**
     * Sau khi het thoi gian chay => quay ve frame 0.
     */
    public void reset() {
        currentFrame = 0;
        beginTime = 0;
        for (boolean b : ignoreFrames) {
            b = false;
        }
    }

    /**
     * add mot frame va list frame.
     */
    public void addFrame(FrameImage frameImage, double timeToNextFrame) {
        ignoreFrames.add(false);
        frameImages.add(frameImage);
        Double newTimeToNextFrame = new Double(timeToNextFrame);
        delayFrame.add(newTimeToNextFrame);
    }

    /**
     * Lay hinh anh cua frame cua currenFrame.
     */
    public BufferedImage getCurrentImage() {
        return frameImages.get(currentFrame).getImage();
    }

    /**
     *
     */
    public void Update(long currenTime) {
        if (beginTime == 0) {
            beginTime = currenTime;
        } else {
            if (currenTime - beginTime > delayFrame.get(currentFrame)) {
                nextFrame();
                beginTime = currenTime;
            }
        }
    }

    public void nextFrame() {
        if (currentFrame>=frameImages.size()-1){
            if (isRepeated) currentFrame=0;
        }
        else
            currentFrame++;
        if (ignoreFrames.get(currentFrame)) nextFrame();
    }

    /**
     *
     */
    public boolean isLastFrame() {
        if (currentFrame == frameImages.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     */
    public void draw(int x, int y, Graphics2D g2){
        BufferedImage image = getCurrentImage();
        g2.drawImage(image, x - image.getWidth() / 2,y - image.getHeight() / 2, 48, 48, null);
        if (drawRectFrame) {
            g2.drawRect(x - image.getWidth() / 2,
                    x - image.getHeight() / 2, image.getWidth(), image.getHeight());
        }
    }
}
