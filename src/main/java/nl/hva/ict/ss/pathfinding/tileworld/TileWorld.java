package nl.hva.ict.ss.pathfinding.tileworld;

import java.awt.Graphics;

/**
 * Interface describing what a tile world can do.
 *
 * @author Dennis Breuker
 * @author Nico Tromp
 */
public interface TileWorld {

    /**
     * The default size of a block (including the border) on the screen. One
     * block represents one pixel of the image. This constant is used in method
     * show().
     */
    int DEFAULT_BLOCK_SIZE = 20;

    /**
     * Reads a tile world from a file. If an error occurred world is null.
     *
     * @param fileName The name of the file containing the bitmap image.
     */
    void load(String fileName);

    /**
     * Writes the tile world to a file.
     *
     * @param fileName The name of the file where the bitmap image is written.
     * NOTE: The name should not contain an extension. The extension is
     * automatically appended
     */
    void save(String fileName);

    /**
     * Draws the tile world on the graphics object using its paint() method. The
     * world is a magnified version of the image. Every pixel in the image has a
     * width and height of DEFAULT_BLOCK_SIZE (including the border). NOTE: do
     * not call this method. If you want to show a tile world in a GUI, use the
     * method show().
     *
     * @param g The graphics object.
     */
    void paint(Graphics g);

    /**
     * Gets the width of the tile world.
     *
     * @return The width.
     */
    int getWidth();

    /**
     * Gets the height of the tile world.
     *
     * @return The height.
     */
    int getHeight();

    /**
     * Gets the tile type from a given tile.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return The tile type. If an error occurred UNKNOWN is returned.
     */
    TileType getTileType(int x, int y);

    /**
     * Sets the tile type for a given tile.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @param type The tile type of the tile.
     */
    void setTileType(int x, int y, TileType type);

    /**
     * Sets the block size. One block represents one pixel of the image.
     *
     * @param blockSize The block size.
     */
    void setBlockSize(int blockSize);

    /**
     * Converts a two-dimensional index (x,y) to a one-dimensional index.
     * Examples: (0,0) converts to 0, (1,0) converts to 1, (0,1) converts to
     * 'width' (maximum x value), etc. This method can be used for generating
     * the correct row index or column index in the adjacency matrix. For
     * example, if the size of the tile world is 40 by 30, the adjacency matrix
     * will be 1200 (40*30) by 1200. The column index in the adjacency matrix
     * for cell (x, y) is calculated by twoDimIndexToOneDimIndex(x,y).
     *
     * @param x The x-coordinate of the index of the two-dimensional array.
     * @param y The y-coordinate of the index of the two-dimensional array.
     * @return The index of the one-dimensional array.
     */
    public int twoDimIndexToOneDimIndex(int x, int y);

    /**
     * Converts a one-dimensional index (index) to a two-dimensional index
     * (x,y). Only the x-coordinate is returned. Examples: 0 converts to (0,0),
     * thus 0 is returned, 1 converts to (1,0), thus 1 is returned, 'width'
     * (maximum x value) converts to (0,1), thus 0 is returned.
     *
     * @param index The one-dimensional index.
     * @return The x-coordinate of the corresponding two-dimensional index
     */
    public int oneDimToTwoDimXCoordinate(int index);

    /**
     * Converts a one-dimensional index (index) to a two-dimensional index
     * (x,y). Only the y-coordinate is returned. Examples: 0 converts to (0,0),
     * thus 0 is returned, 1 converts to (1,0), thus 0 is returned, 'width'
     * (maximum x value) converts to (0,1), thus 1 is returned.
     *
     * @param index The one-dimensional index.
     * @return The y-coordinate of the corresponding two-dimensional index
     */
    public int oneDimToTwoDimYCoordinate(int index);

    /**
     * Shows the current tile world using the default block size in a new JPanel
     * in a new frame.
     *
     * @param title The title of the frame.
     * @param x The x-coordinate of the frame.
     * @param y The y-coordinate of the frame.
     */
    public void show(String title, int x, int y);

    /**
     * Shows the current tile world using a specific block size in a new JPanel
     * in a new frame.
     *
     * @param title The title of the frame.
     * @param blockSize The block size (size of pixel on screen).
     * @param x The x-coordinate of the frame.
     * @param y The y-coordinate of the frame.
     */
    public void show(String title, int blockSize, int x, int y);

    /**
     * Searches the tile world for a start tile.
     *
     * @return The one-dimensional index of the start tile. If it has not been
     * found, NO_INDEX is returned.
     */
    public int findStartIndex();

    /**
     * Searches the tile world for an end tile.
     *
     * @return The one-dimensional index of the end tile. If it has not been
     * found, NO_INDEX is returned.
     */
    public int findEndIndex();
}
