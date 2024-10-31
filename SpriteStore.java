/*
 * Decompiled with CFR 0.152.
 */
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class SpriteStore {
    private static SpriteStore single = new SpriteStore();
    private HashMap sprites = new HashMap();

    public static SpriteStore get() {
        return single;
    }

    public Sprite getSprite(String ref) {
        if (this.sprites.get(ref) != null) {
            return (Sprite)this.sprites.get(ref);
        }
        BufferedImage sourceImage = null;
        try {
            URL url = this.getClass().getClassLoader().getResource(ref);
            if (url == null) {
                System.out.println("Failed to load: " + ref);
                System.exit(0);
            }
            sourceImage = ImageIO.read(url);
        }
        catch (IOException e) {
            System.out.println("Failed to load: " + ref);
            System.exit(0);
        }
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), 2);
        ((Image)image).getGraphics().drawImage(sourceImage, 0, 0, null);
        Sprite sprite = new Sprite(image);
        this.sprites.put(ref, sprite);
        return sprite;
    }
}
