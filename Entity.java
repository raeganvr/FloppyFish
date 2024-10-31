/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    protected double x;
    protected double y;
    protected Sprite sprite;
    protected double dx;
    protected double dy;
    private Rectangle me = new Rectangle();
    private Rectangle him = new Rectangle();

    public Entity(String r, int newX, int newY) {
        this.x = newX;
        this.y = newY;
        this.sprite = SpriteStore.get().getSprite(r);
    }

    public void move(long delta) {
        this.x += (double)delta * this.dx / 1000.0;
        this.y += (double)delta * this.dy / 1000.0;
    }

    public void setHorizontalMovement(double newDX) {
        this.dx = newDX;
    }

    public void setVerticalMovement(double newDY) {
        this.dy = newDY;
    }

    public double getHorizontalMovement() {
        return this.dx;
    }

    public double getVerticalMovement() {
        return this.dy;
    }

    public int getX() {
        return (int)this.x;
    }

    public int getY() {
        return (int)this.y;
    }

    public void draw(Graphics g) {
        this.sprite.draw(g, (int)this.x, (int)this.y);
    }

    public boolean collidesWith(Entity other) {
        this.me.setBounds((int)this.x, (int)this.y, this.sprite.getWidth(), this.sprite.getHeight());
        this.him.setBounds(other.getX(), other.getY(), other.sprite.getWidth(), other.sprite.getHeight());
        return this.me.intersects(this.him);
    }

    public abstract void collidedWith(Entity var1);
}
