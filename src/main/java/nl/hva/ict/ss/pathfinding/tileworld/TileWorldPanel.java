package nl.hva.ict.ss.pathfinding.tileworld;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * TileWorldPanel is a javax.swing.JPanel for showing a tile world. A tile world
 * is represented by a two-dimensional array of tile types. This class is
 * inspired by a previous version by Gerke de Boer. You don't need to edit this.
 *
 * @author Dennis Breuker
 */
public class TileWorldPanel extends JPanel {

    /**
     * The tile world that is to be shown.
     */
    TileWorld tileWorld;

    /**
     * Constructs a TitleWorldPanel object. The size of the panel is set using
     * the dimensions of the tile world. The tile world is saved as an
     * attribute.
     *
     * @param tileWorld The tile world to be shown.
     * @param blockSize The size of a tile on the screen.
     */
    public TileWorldPanel(TileWorld tileWorld, int blockSize) {
        setSize(tileWorld.getWidth() * blockSize, tileWorld.getHeight() * blockSize);
        this.tileWorld = tileWorld;
    }

    /**
     * Paints the tile world on the graphics object.
     *
     * @param g The graphics object to be painted on.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        tileWorld.paint(g);
    }
}
