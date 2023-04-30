package project.something.game.level;

import project.something.game.Game;
import project.something.graphics.TextureAtlas;
import project.something.utils.Utils;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class Level {

           //tmp ho

    public static final int TILE_SCALE = 8;
    public static final int TILE_IN_GAME_SCALE = 3;
    public static final int SCALED_TILE_SIZE = TILE_SCALE * TILE_IN_GAME_SCALE;
    public static final int TILES_IN_WIDTH = Game.WIDTH / SCALED_TILE_SIZE;
    public static final int TILES_IN_HEIGHT = Game.HEIGHT / SCALED_TILE_SIZE;

    private Integer[][] tileMap;
    private Map<TileType, Tile> tiles;

    public Level(TextureAtlas B_T) {
        tileMap = new Integer[TILES_IN_WIDTH][TILES_IN_HEIGHT];
        tiles = new HashMap<TileType, Tile>();
        tiles.put(TileType.BRICK, new Tile(B_T.cut(0 * TILE_SCALE, 0 * TILE_SCALE, TILE_SCALE, TILE_SCALE) , TILE_IN_GAME_SCALE, TileType.BRICK));
        tiles.put(TileType.METAL, new Tile(B_T.cut(0 * TILE_SCALE, 1 * TILE_SCALE, TILE_SCALE, TILE_SCALE) , TILE_IN_GAME_SCALE, TileType.METAL));
        tiles.put(TileType.WATER, new Tile(B_T.cut(1 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE) , TILE_IN_GAME_SCALE, TileType.WATER));
        tiles.put(TileType.GRASS, new Tile(B_T.cut(1 * TILE_SCALE, 1 * TILE_SCALE, TILE_SCALE, TILE_SCALE) , TILE_IN_GAME_SCALE, TileType.GRASS));
        tiles.put(TileType.ICE, new Tile(B_T.cut(0 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE) , TILE_IN_GAME_SCALE, TileType.ICE));
        tiles.put(TileType.EMPTY, new Tile(B_T.cut(3 * TILE_SCALE, 2 * TILE_SCALE, TILE_SCALE, TILE_SCALE) , TILE_IN_GAME_SCALE, TileType.EMPTY));

        tileMap = Utils.lvlParser("res\\leveR");
    }
    public void update() {

    }
    public void render(Graphics2D g) {
        for(int i=0; i<tileMap.length; i++) {
            for (int j=0; j<tileMap[i].length; j++){
                tiles.get(TileType.FromNumeric(tileMap[i][j])).render(g, j * SCALED_TILE_SIZE, i * SCALED_TILE_SIZE);
            }
        }
    }
}
