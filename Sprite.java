/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Graphics;
import java.awt.Image;

public class Sprite {
    public Image image;

    public Sprite(Image i) {
        this.image = i;
    }

    public int getWidth() {
        return this.image.getWidth(null);
    }

    public int getHeight() {
        return this.image.getHeight(null);
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(this.image, x, y, null);
    }
}
