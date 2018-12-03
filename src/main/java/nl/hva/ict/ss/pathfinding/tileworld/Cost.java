package nl.hva.ict.ss.pathfinding.tileworld;

/**
 * Defines constants for costs.
 *
 * @author Dennis Breuker
 * @author Nico Tromp
 */
public interface Cost {

    /**
     * The normal and diagonal costs for all walkable types.
     */
    double ROAD_COST = 10;
    double ROAD_DIAGONAL_COST = 14;
    double SAND_COST = 14;
    double SAND_DIAGONAL_COST = 20;
    double WATER_COST = 20;
    double WATER_DIAGONAL_COST = 28;
    double MOUNTAIN_COST = 24;
    double MOUNTAIN_DIAGONAL_COST = 34;
}
