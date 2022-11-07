package effect;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

/**
 * Dung de load du lieu trong file.
 */
public class CacheDataLoader {
    public static final int MAX_LEVER = 3; // So luong map toi da.
    private static CacheDataLoader instance;
    private String framefile = "/data/frame.txt";
    private String animationfile = "/data/animation.txt";
    private String physmapfile;
    private Hashtable<String, FrameImage> frameImages;  // Luu vao mot mang hashtable de anh xa.
    private Hashtable<String, Animation> animations; // Luu vao mot mang hashtable de anh xa.
    private Hashtable<Integer, String[]> maps;
    private int lever = 1;


    private String[] phys_map;

    private CacheDataLoader() {
    }

    // Tranh viec tao ra mot cai giong no ma chi dung duy nhat instance.
    public static CacheDataLoader getInstance() {
        if (instance == null) {
            instance = new CacheDataLoader();
        }
        return instance;
    }

    public void loadData() throws IOException {
        loadFrame();
        loadAnimation();
        loadPhysMap();
    }

    /**
     * Dung de load tat ca du lieu la frameimage len hashtable.
     */
    public void loadFrame() {
        frameImages = new Hashtable<String, FrameImage>();
        try {
            InputStream is = getClass().getResourceAsStream(framefile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = br.readLine();
            int n = Integer.parseInt(str);

            for (int i = 0; i < n; i++) {
                FrameImage frame = new FrameImage();
                // load ten anh
                str = br.readLine();
                frame.setName(str);

                // load path cua anh.
                String[] s = br.readLine().split(" ");
                String path = s[1];

                // load vi tri x
                s = br.readLine().split(" ");
                int x = Integer.parseInt(s[1]);

                // load vi tri y.
                s = br.readLine().split(" ");
                int y = Integer.parseInt(s[1]);

                // load chieu rong w.
                s = br.readLine().split(" ");
                int w = Integer.parseInt(s[1]);

                // load chieu dai h.
                s = br.readLine().split(" ");
                int h = Integer.parseInt(s[1]);
                // Load mot doi tuong anh con.
                BufferedImage imageData = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
                BufferedImage image = imageData.getSubimage(x, y, w, h);
                frame.setImage(image);
                instance.frameImages.put(frame.getName(), frame);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load cac aniamtion cua hinh anh.
     */
    public void loadAnimation() throws IOException {
        animations = new Hashtable<String, Animation>();
        InputStream is = getClass().getResourceAsStream(animationfile);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str = br.readLine();
        int n = Integer.parseInt(str);

        for (int i = 0; i < n; i++) {
            Animation animation = new Animation();

            // read name of animations.
            str = br.readLine();
            animation.setName(str);

            // read frame.
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < s.length; j += 2) {
                animation.add(getFrameImage(s[j]), Double.parseDouble(s[j + 1]));
            }
            instance.animations.put(animation.getName(), animation);
        }

    }


    public void loadPhysMap() {
        maps = new Hashtable<Integer, String[]>();
        for (int lever = 1; lever <= MAX_LEVER; lever++) {
            physmapfile = "/map/lever" + lever + ".txt";
            try{
                InputStream is = getClass().getResourceAsStream(physmapfile);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String[] s = br.readLine().split(" ");
                int numberOfRows = Integer.parseInt(s[0]);

                instance.phys_map = new String[numberOfRows];
                for (int i = 0; i < numberOfRows; i++) {
                    phys_map[i] = br.readLine();
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            maps.put(lever, phys_map);
        }
    }

    public FrameImage getFrameImage(String name) {
        FrameImage frameImage = new FrameImage(instance.frameImages.get(name));
        return frameImage;
    }

    public Animation getAnimation(String name) {
        Animation animation = new Animation(instance.animations.get(name));
        return animation;
    }

    public String[] getPhys_map(int lever) {
        return instance.maps.get(lever);
    }
}
