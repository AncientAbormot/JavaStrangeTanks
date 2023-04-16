package project.something.game.bullet;

import project.something.IO.Input;
import project.something.graphics.TextureAtlas;

import java.awt.*;
import java.awt.image.BufferedImage;


    public class Bullet extends AbstractMovableObject {

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

            protected BufferedImage texture(TextureAtlas atlas) {
                return atlas.cut(x, y, w, h);
            }
        }

        public Bullet(int x, int y) {
            super(x, y);
        }

        @Override
        public void draw(Graphics g) {

        }

        public void update(Input input) {
        }
    }
