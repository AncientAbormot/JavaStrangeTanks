package project.something.game.level;

public enum TileType {
    EMPTY(0), BRICK(1), METAL(2), WATER(3), GRASS(4), BARREL(5), BRIDGE(6);

    private int n;

    TileType(int n) {
        this.n = n;
    }
    public int numeric() {
        return n;
    }
    public static TileType FromNumeric(int n) {
        switch (n) {
            case 1:
                return BRICK;
            case 2:
                return METAL;
            case 3:
                return WATER;
            case 4:
                return GRASS;
            case 5:
                return BARREL;
            case 6:
                return BRIDGE;
            default:
                return EMPTY;
        }
    }
}
