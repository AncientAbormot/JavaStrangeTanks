package project.something.game.level;

import project.something.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile {

    private BufferedImage image;
    private TileType type;


    protected Tile(BufferedImage sheet, int scale, TileType type) {
        this.type = type;
        this.image = Utils.resize(sheet, sheet.getWidth() * scale, sheet.getHeight() * scale);
    }

    protected void render(Graphics2D g, int x, int y) {
        g.drawImage(image, x, y, null);
    }
    protected TileType type() {
        return type;
    }
}
