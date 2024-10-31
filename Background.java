/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Background {
    private int x;
    private int y;
    private int interval = 0;
    private Image image = Toolkit.getDefaultToolkit().getImage("waterbackground.PNG");

    public Background() {
        this(0, 0);
    }

    public Background(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            URL url = Background.class.getResource("waterbackground.PNG");
            this.image = Toolkit.getDefaultToolkit().getImage(url);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void draw(Graphics window) {
        window.drawImage(this.image, this.getX(), this.getY(), 1920, 1080, null);
        this.x -= this.interval;
        if (this.x <= -1920) {
            this.x += 3840;
        }
    }

    public void setInterval(int x) {
        this.interval = x;
    }

    public int getImageWidth() {
        return 1920;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }
}
