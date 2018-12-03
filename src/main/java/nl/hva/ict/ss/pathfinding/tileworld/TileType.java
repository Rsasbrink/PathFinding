package nl.hva.ict.ss.pathfinding.tileworld;

import java.awt.Color;

/**
 * TileType is an enumeration representing the values a tile can have. Every
 * tile type has its own color and cost.
 *
 * @author Dennis Breuker
 * @author Nico Tromp
 */
public enum TileType implements RGBColor, Cost {

    ROAD(RGB_WHITE, ROAD_COST, ROAD_DIAGONAL_COST),
    SAND(RGB_YELLOW, SAND_COST, SAND_DIAGONAL_COST),
    WATER(RGB_BLUE, WATER_COST, WATER_DIAGONAL_COST),
    MOUNTAIN(RGB_GREY, MOUNTAIN_COST, MOUNTAIN_DIAGONAL_COST),
    NONWALKABLE(RGB_BLACK, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY),
    START(RGB_RED, ROAD_COST, ROAD_DIAGONAL_COST),
    END(RGB_GREEN, ROAD_COST, ROAD_DIAGONAL_COST),
    PATH(RGB_CYAN, ROAD_COST, ROAD_DIAGONAL_COST),
    /**
     * UNKNOWN is used for colors that were not recognized
     */
    UNKNOWN(RGB_PINK, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

    private final int rgbColor;
    private final double cost;
    private final double diagonalCost;

    TileType(int rgbColor, double cost, double diagonalCost) {
        this.rgbColor = rgbColor;
        this.cost = cost;
        this.diagonalCost = diagonalCost;
    }

    public double getCost() {
        return cost;
    }

    public double getDiagonalCost() {
        return diagonalCost;
    }

    public int getRgbColor() {
        return rgbColor;
    }

    public Color getColor() {
        return new Color(rgbColor);
    }

    /**
     * Converts a RGB color to the corresponding tile
     *
     * @param rgbColor The RGB color to be converted.
     * @return The tile. If no color is matched, UNKNOWN is returned.
     */
    public static TileType rgbColorToTile(int rgbColor) {
        for (TileType tile : TileType.values()) {
            if (tile.getRgbColor() == rgbColor) {
                return tile;
            }
        }
        System.err.printf("Unknown rgbColor in picture: %x ", rgbColor);
        return UNKNOWN;
    }
}
