package project.something.game.bullet;

import project.something.IO.Input;
import project.something.game.Entity;
import project.something.game.EntityType;
import project.something.game.Game;
import project.something.game.Player;
import project.something.graphics.Sprite;
import project.something.graphics.SpriteSheet;
import project.something.graphics.TextureAtlas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;


public class Bullet extends Entity {

    public static final int SPRITE_SCALE = 4;
    public static final int SPRITES_PER_HEADING = 2;

    enum Heading {
        NORTH(0 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        EAST(6 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        SOUTH(4 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        WEST(2 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);

        private int x, y, h, w;

        Heading(int x, int y, int h, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        protected BufferedImage texture(TextureAtlas atlas) {
            return atlas.cut(x, y, w, h);
        }
    }

    private Bullet.Heading heading;
    private Map<Bullet.Heading, Sprite> spriteMap;
    private float scale;
    private float speed;

    private int ID;

    public Bullet(float x, float y, float scale, float speed, Heading heading, int ID, TextureAtlas atlas) {
        super(EntityType.Bullet, x, y);
        this.ID = ID;
        this.heading = heading;
        spriteMap = new HashMap<Bullet.Heading, Sprite>();
        this.scale = scale * 2;
        this.speed = speed * 3;
        for (Heading h : Heading.values()) {
            SpriteSheet sheet = new SpriteSheet(h.texture(atlas), SPRITES_PER_HEADING, SPRITE_SCALE);
            Sprite sprite = new Sprite(sheet, this.scale);
            spriteMap.put(h, sprite);
        }
    }

    @Override
    public void update(Input input) {

        float newX = x;
        float newY = y;

        //Стрелочки
        if (heading == Heading.NORTH) {
            newY -= speed;
        } else if (heading == Heading.EAST) {
            newX += speed;
        } else if (heading == Heading.SOUTH) {
            newY += speed;
        } else if (heading == Heading.WEST) {
            newX -= speed;
        }

        if (Game.AllowMove(newX, newY, this.ID)) {
            x = newX;
            y = newY;
        }

    }

    public float getScale() {
        return this.scale;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getSPRSCL() {
        return this.SPRITE_SCALE;
    }

    public int getID() {
        return this.ID;
    }

    @Override
    public void render(Graphics2D g) {
        spriteMap.get(heading).render(g, x, y);
    }
}