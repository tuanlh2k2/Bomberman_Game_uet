package effect;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

/**
 * Dung de load du lieu trong file.
 */
public class CacheDataLoader {
    private static CacheDataLoader instance;
    private String framefile = "data/frame.txt";
    private String animationfile = "data/animation.txt";
    private Hashtable<String, FrameImage> frameImages;  // Luu vao mot mang hashtable de anh xa.
    private Hashtable<String, Animation> animations; // Luu vao mot mang hashtable de anh xa.

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
    }

    /**
     * Dung de load tat ca du lieu la frameimage len hashtable.
     */
    public void loadFrame() {
        frameImages = new Hashtable<String, FrameImage>();
        try {
            InputStream is = getClass().getResourceAsStream("/data/frame.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = br.readLine();
            int n = Integer.parseInt(str);
            System.out.println(n);

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

    public void loadAnimation() throws IOException {
        animations = new Hashtable<String, Animation>();
        InputStream is = getClass().getResourceAsStream("/data/animation.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str = br.readLine();
        int n = Integer.parseInt(str);
        System.out.println(n);

        for (int i = 0; i < n; i++) {
            Animation animation = new Animation();

            // read name of animations.
            str = br.readLine();
            animation.setName(str);
            System.out.println(animation.getName());

            // read frame.
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < s.length; j += 2) {
                animation.add(getFrameImage(s[j]), Double.parseDouble(s[j + 1]));
            }
            instance.animations.put(animation.getName(), animation);
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
}
