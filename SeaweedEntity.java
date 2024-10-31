/*
 * Decompiled with CFR 0.152.
 */
public class SeaweedEntity
extends Entity {
    private double moveSpeed = 378.0;
    private Game game;

    public SeaweedEntity(Game g, String r, int newX, int newY) {
        super(r, newX, newY);
        this.game = g;
        this.dx = -this.moveSpeed;
    }

    @Override
    public void collidedWith(Entity other) {
    }
}
