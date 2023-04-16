package project.something.game;

import project.something.IO.Input;
import project.something.game.Entity;
import project.something.game.EntityType;
import project.something.game.Game;
import project.something.graphics.Sprite;
import project.something.graphics.SpriteSheet;
import project.something.graphics.TextureAtlas;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

public class Player extends Entity {

    public static final int	SPRITE_SCALE		= 16;
    public static final int	SPRITES_PER_HEADING	= 2;

    enum Heading {
        NORTH(0 * SPRITE_SCALE, 0 * SPRITE_SCALE, 3 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        EAST(6 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        SOUTH(4 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        WEST(2 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);

        private int	x, y, h, w;

        Heading(int x, int y, int h, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        protected BufferedImage texture(TextureAtlas YT_T) {
            return YT_T.cut(x, y, w, h);
        }
    }

    private Heading					heading;
    private Map<Heading, Sprite>	spriteMap;
    private float					scale;
    private float					speed;

    private int UP;
    private int DOWN;
    private int LEFT;
    private int RIGHT;

    public Player(float x, float y, float scale, float speed, int UP , int DOWN, int LEFT , int RIGHT  , TextureAtlas atlas) {
        super(EntityType.Player, x, y);
        this.UP = UP;
        this.DOWN = DOWN;
        this.LEFT = LEFT;
        this.RIGHT = RIGHT;
        heading = Heading.NORTH;
        spriteMap = new HashMap<Heading, Sprite>();
        this.scale = scale*2;
        this.speed = speed*3;

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
        if (input.getKey(UP)) {
            if (heading != Heading.SOUTH); {
                newY -= speed;
                heading = Heading.NORTH;
            }
        } else if (input.getKey(RIGHT)) {
            if (heading != Heading.WEST); {
                newX += speed;
                heading = Heading.EAST;
            }

        } else if (input.getKey(DOWN)) {
            if (heading != Heading.NORTH); {
                newY += speed;
                heading = Heading.SOUTH;
            }
        } else if (input.getKey(LEFT)) {
            if (heading != Heading.EAST) ;
            {
                newX -= speed;
                heading = Heading.WEST;
            }
        }
        if (newX < 0) {
            newX = 0;
        } else if (newX >= Game.WIDTH - SPRITE_SCALE * this.scale) {
            newX = Game.WIDTH - SPRITE_SCALE * this.scale;
        }

        if (newY < 0) {
            newY = 0;
        } else if (newY >= Game.HEIGHT - SPRITE_SCALE * this.scale) {
            newY = Game.HEIGHT - SPRITE_SCALE * this.scale;
        }
        x = newX;
        y = newY;
    }

    @Override
    public void render(Graphics2D g) {
        spriteMap.get(heading).render(g, x, y);
    }

    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
public float getScale(){
        return this.scale;
}
}