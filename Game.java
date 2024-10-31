/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game
extends Canvas {
    private BufferStrategy strategy;
    private boolean waitingForKeyPress = true;
    private boolean spacePressed = false;
    private boolean gameRunning = true;
    private ArrayList entities = new ArrayList();
    private ArrayList removeEntities = new ArrayList();
    long lastLoopTime;
    private BufferedImage back;
    private Background background1;
    private Background background2;
    private Entity fish;
    private Entity tubeUp;
    private Entity tubeDown;
    private String scoreMessage = "";
    private int count = 0;
    private int score = 0;
    private int rand;

    public Game() {
        JFrame container = new JFrame("Floppy Fish");
        JPanel panel = (JPanel)container.getContentPane();
        panel.setPreferredSize(new Dimension(1920, 1080));
        panel.setLayout(null);
        this.setBounds(0, 0, 1920, 1080);
        panel.add(this);
        this.setIgnoreRepaint(true);
        container.pack();
        container.setResizable(false);
        container.setVisible(true);
        container.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyInputHandler());
        this.requestFocus();
        this.createBufferStrategy(2);
        this.strategy = this.getBufferStrategy();
        this.initEntities();
        this.gameLoop();
    }

    private void initEntities() {
        this.fish = new FishEntity(this, "sprites/fish.png", 400, 400);
        this.entities.add(this.fish);
    }

    public void removeEntity(Entity entity) {
        this.removeEntities.add(entity);
    }

    public void notifyDeath() {
        this.waitingForKeyPress = true;
    }

    public void gameLoop() {
        this.lastLoopTime = System.currentTimeMillis();
        this.back = null;
        this.background1 = new Background();
        this.background2 = new Background(this.background1.getImageWidth(), 0);
        while (this.gameRunning) {
            Entity entity;
            int i;
            long delta = System.currentTimeMillis() - this.lastLoopTime;
            this.lastLoopTime = System.currentTimeMillis();
            Graphics2D g = (Graphics2D)this.strategy.getDrawGraphics();
            if (this.back == null) {
                this.back = (BufferedImage)this.createImage(this.getWidth(), this.getHeight());
            }
            Graphics2D buffer = this.back.createGraphics();
            this.background1.draw(buffer);
            this.background2.draw(buffer);
            g.drawImage(this.back, null, 0, 0);
            if (!this.waitingForKeyPress) {
                this.scoreMessage = "";
                if (this.count % 60 == 0) {
                    this.rand = (int)Math.round(Math.random() * 350.0 + 450.0);
                    this.tubeUp = new SeaweedEntity(this, "sprites/tube.png", 1900, this.rand - (int)(Math.random() * 250.0 + 1050.0));
                    this.tubeDown = new SeaweedEntity(this, "sprites/tube.png", 1900, this.rand);
                    this.entities.add(this.tubeUp);
                    this.entities.add(this.tubeDown);
                    ++this.score;
                }
                ++this.count;
                this.background1.setInterval(7);
                this.background2.setInterval(7);
                if (this.score >= 3) {
                    g.setColor(new Color(30, 72, 71));
                    g.setFont(new Font("Dialog", 1, 30));
                    g.drawString("score: " + String.valueOf(this.score - 3), 130, 140);
                    this.scoreMessage = "score: " + String.valueOf(this.score - 3);
                }
            }
            if (!this.waitingForKeyPress) {
                i = 0;
                while (i < this.entities.size()) {
                    entity = (Entity)this.entities.get(i);
                    entity.move(delta);
                    ++i;
                }
            }
            i = 0;
            while (i < this.entities.size()) {
                entity = (Entity)this.entities.get(i);
                entity.draw(g);
                ++i;
            }
            i = 0;
            while (i < this.entities.size()) {
                int j = i + 1;
                while (j < this.entities.size()) {
                    Entity him;
                    Entity me = (Entity)this.entities.get(i);
                    if (me.collidesWith(him = (Entity)this.entities.get(j))) {
                        me.collidedWith(him);
                        him.collidedWith(me);
                    }
                    ++j;
                }
                ++i;
            }
            this.entities.removeAll(this.removeEntities);
            this.removeEntities.clear();
            if (this.waitingForKeyPress) {
                g.setColor(new Color(226, 233, 196));
                g.fillRoundRect(830, 225, 260, 125, 40, 40);
                g.fillRoundRect(760, 395, 400, 85, 40, 40);
                g.setColor(new Color(30, 72, 71));
                g.setFont(new Font("Dialog", 1, 36));
                if (this.scoreMessage.contentEquals("")) {
                    this.scoreMessage = "Floppy Fish";
                }
                g.drawString(this.scoreMessage, (1920 - g.getFontMetrics().stringWidth(this.scoreMessage)) / 2, 300);
                g.drawString("Press space to play", (1920 - g.getFontMetrics().stringWidth("Press space to play")) / 2, 450);
                this.background1.setInterval(0);
                this.background2.setInterval(0);
                this.score = 0;
            }
            g.dispose();
            this.strategy.show();
            this.fish.setHorizontalMovement(0.0);
            this.fish.setVerticalMovement(0.0);
            if (this.spacePressed) {
                this.fish.setVerticalMovement(-1000 + this.score * 3);
            }
            try {
                Thread.sleep(16L);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private void startGame() {
        this.entities.clear();
        this.initEntities();
        this.spacePressed = false;
    }

    public static void main(String[] args) {
        new Game();
    }

    private class KeyInputHandler
    extends KeyAdapter {
        private int pressCount = 1;

        private KeyInputHandler() {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (Game.this.waitingForKeyPress) {
                return;
            }
            if (e.getKeyCode() == 32) {
                Game.this.spacePressed = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (Game.this.waitingForKeyPress) {
                return;
            }
            if (e.getKeyCode() == 32) {
                Game.this.spacePressed = false;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (Game.this.waitingForKeyPress) {
                if (this.pressCount == 1) {
                    Game.this.waitingForKeyPress = false;
                    Game.this.startGame();
                    this.pressCount = 0;
                } else {
                    ++this.pressCount;
                }
            }
            if (e.getKeyChar() == '\u001b') {
                System.exit(0);
            }
        }
    }
}
