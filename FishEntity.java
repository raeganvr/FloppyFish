/*
 * Decompiled with CFR 0.152.
 */
public class FishEntity
extends Entity {
    private Game game;

    public FishEntity(Game g, String r, int newX, int newY) {
        super(r, newX, newY);
        this.game = g;
    }

    @Override
    public void move(long delta) {
        if (this.y < 950.0 & this.dy == 0.0) {
            this.dy = 0.0;
        }
        int i = 0;
        while (i < 200) {
            this.dy += 2.2;
            ++i;
        }
        if (this.dy > 0.0 && this.y + (double)delta > 1120.0 || this.y < -175.0) {
            this.game.notifyDeath();
            return;
        }
        super.move(delta);
    }

    @Override
    public void collidedWith(Entity other) {
        if (other instanceof SeaweedEntity) {
            this.game.notifyDeath();
        }
        if (this.dy > 500.0) {
            this.game.notifyDeath();
        }
    }
}
