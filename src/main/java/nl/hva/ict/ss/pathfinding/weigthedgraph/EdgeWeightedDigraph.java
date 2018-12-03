package nl.hva.ict.ss.pathfinding.weigthedgraph;

import java.util.LinkedList;

import nl.hva.ict.ss.pathfinding.tileworld.TileType;
import nl.hva.ict.ss.pathfinding.tileworld.TileWorldUtil;

/**
 *  The {@code EdgeWeightedDigraph} class represents a edge-weighted
 *  digraph of vertices named 0 through <em>V</em> - 1, where each
 *  directed edge is of type {@link DirectedEdge} and has a real-valued weight.
 *  It supports the following two primary operations: add a directed edge
 *  to the digraph and iterate over all of edges incident from a given vertex.
 *  It also provides
 *  methods for returning the number of vertices <em>V</em> and the number
 *  of edges <em>E</em>. Parallel edges and self-loops are permitted.
 *  <p>
 *  This implementation uses an adjacency-lists representation, which 
 *  is a vertex-indexed array of @link{Bag} objects.
 *  All operations take constant time (in the worst case) except
 *  iterating over the edges incident from a given vertex, which takes
 *  time proportional to the number of such edges.
 *  <p>
 *  For additional documentation,
 *  see <a href="http://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * 
 * Overgenomen uit de library algs4-package.jar van Robert Sedgwick
 * Aanpassingen specifiek t.b.v. PO2 Sorting&amp;Searching
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Eric Ravestein
 * @author Nico Tromp
 */
public class EdgeWeightedDigraph {

    private final int V;
    private int E;
    private final int startIndex;
    private final int endIndex;

    private final LinkedList<DirectedEdge>[] adj;
    private final TileWorldUtil t;
    private final int breedte;
    private final int hoogte;
    /**
     * Constructor maakt een Digraph aan met V knooppunten (vertices)
     * Direct overgenomen uit Sedgwick. T.b.v. Floyd-Warshall
     * @param V aantal knooppunten 
     */
    public EdgeWeightedDigraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        hoogte = 0;
        breedte = 0;
        t = null;
        startIndex = 0;
        endIndex = 0;
        adj = (LinkedList<DirectedEdge>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<DirectedEdge>();
        }
    }
    /**
     * Constructor.
     * Leest de TileWorldUtil in van bitmap fileName+".png" en
     * maakt de adjacency list adj
     * 
     * @param fileName filenaam van de bitmap
     */
    public EdgeWeightedDigraph(String fileName) {

        t = new TileWorldUtil(fileName + ".png");

        // Aantal knopen
        hoogte = t.getHeight();
        breedte = t.getWidth();
        this.V = hoogte * breedte;
        // Start- en Eindindex
        this.startIndex = t.findStartIndex();
        this.endIndex = t.findEndIndex();

        this.E = 0;
        adj = (LinkedList<DirectedEdge>[]) new LinkedList[V];
        int v;

        for (v = 0; v < V; v++) {
            adj[v] = findAdjacent(v);
        }
    }
    
    /**
     * getter voor het aantal knooppunten
     * @return aantal knooppunten
     */
    public int V() {
        return V;
    }
    /**
     * Bewaart de bitmap onder de naam fileName.
     * @param fileName naam van de bitmap
     */
    public void save(String fileName) {
        t.save(fileName);
    }
    /**
     * Maakt een Digraph met een adjacency matrix in plaats van een 
     * adjacencylist. 
     * Nodig voor Floyd=Warshall algoritme.
     * 
     * @return een Digraph met Adjacencymatrix in plaats van list.
     */
    public AdjMatrixEdgeWeightedDigraph createAdjMatrixEdgeWeightedDigraph() {
        AdjMatrixEdgeWeightedDigraph g;
        Iterable<DirectedEdge> edges;
        g = new AdjMatrixEdgeWeightedDigraph(V);

        edges = edges();

        for (DirectedEdge d : edges) {
            g.addEdge(d);
        }

        return g;
    }
    /**
     * Zoekt alle aanliggende knoopunten van knooppunt v.
     * 
     * @param v het te onderzoeken knooppunt
     * @return LinkedList van alle aanliggende (=bereikbare)
     *          knooppunten met de cost om daar te komen.
     */
    private LinkedList<DirectedEdge> findAdjacent(int v) {
        int x = t.oneDimToTwoDimXCoordinate(v);
        int y = t.oneDimToTwoDimYCoordinate(v);

        LinkedList<DirectedEdge> b = new LinkedList<DirectedEdge>();
        if (x > 0) {
            // naar Links 
            b.add(new DirectedEdge(v,
                    t.twoDimIndexToOneDimIndex(x - 1, y),
                    t.getTileType(x - 1, y).getCost()));
            if (y > 0) {
                // Links onder
                b.add(new DirectedEdge(v,
                        t.twoDimIndexToOneDimIndex(x - 1, y - 1),
                        t.getTileType(x - 1, y - 1).getDiagonalCost()));
            }
            if (y < hoogte - 1) {
                // Links boven
                b.add(new DirectedEdge(v,
                        t.twoDimIndexToOneDimIndex(x - 1, y + 1),
                        t.getTileType(x - 1, y + 1).getDiagonalCost()));
            }
        }
        if (x < breedte - 1) {
            // Rechts
            b.add(new DirectedEdge(v,
                    t.twoDimIndexToOneDimIndex(x + 1, y),
                    t.getTileType(x + 1, y).getCost()));
            if (y > 0) {
                // Rechts onder
                b.add(new DirectedEdge(v,
                        t.twoDimIndexToOneDimIndex(x + 1, y - 1),
                        t.getTileType(x + 1, y - 1).getDiagonalCost()));
            }
            if (y < hoogte - 1) {
                // Rechts boven
                b.add(new DirectedEdge(v,
                        t.twoDimIndexToOneDimIndex(x + 1, y + 1),
                        t.getTileType(x + 1, y + 1).getDiagonalCost()));
            }
        }
        if (y > 0) {
            // Onder
            b.add(new DirectedEdge(v,
                    t.twoDimIndexToOneDimIndex(x, y - 1),
                    t.getTileType(x, y - 1).getCost()));
        }
        if (y < hoogte - 1) {
            // Boven
            b.add(new DirectedEdge(v,
                    t.twoDimIndexToOneDimIndex(x, y + 1),
                    t.getTileType(x, y + 1).getCost()));
        }
        return b;
    }
    /**
     * Geeft de x coordinaat van knooppunt v in de bitmap.
     * 
     * @param v index van knoopunt v
     * @return x coordinaat
     */
    public int getX(int v) {
        return t.oneDimToTwoDimXCoordinate(v);
    }
    
    /**
     * Geeft de y coordinaat van knooppunt v in de bitmap.
     * 
     * @param v index van knoopunt v
     * @return x coordinaat
     */
    public int getY(int v) {
        return t.oneDimToTwoDimYCoordinate(v);
    }
    /**
     * Geeft de index in de adjacency list van het knooppunt dat
 in de TileWorldUtil t een coordinaat (x,y) heeft.
     * @param x x-coordinaat van te onderzoeken punt
     * @param y y-coordinaat van te onderzoeken punt
     * @return  index van dit punt in de adjacencylist adj
     */
    public int getIndex(int x, int y) {
        return t.twoDimIndexToOneDimIndex(x, y);
    }
    
    /**
     * Bepaalt alle aanliggende edges van knooppunt v
     * @param v het te onderzoeken knooppunt
     * @return lijst van edges die beginnen of eindigen in v
     */
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }
    /**
     * Maakt een grote list van alle edges in de Digraph
     * @return LinkedList met alle edges
     */
    public Iterable<DirectedEdge> edges() {
        LinkedList<DirectedEdge> list = new LinkedList<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj[v]) {
                list.add(e);
            }
        }
        return list;
    }
    /**
     * getter voor het Startpunt van het te zoeken pad.
     * 
     * @return index in de adjacency list van het startpunt
     */
    public int getStart() {
        return (startIndex);
    }
    
    /**
     * getter voor het Eindpunt van het te zoeken pad.
     * 
     * @return index in de adjacency list van het startpunt
     */
    public int getEnd() {
        return (endIndex);
    }
    /**
     * Laat de bitmap zien in een jFrame
     * @param filename naam van de bitmap
     * @param title    aanvullende titel ter specificatie van de bitmap
     */
    public void show(String filename, String title) {
        t.show(filename + ".png " + title, 10, 0, 0);
    }
    /**
     * Tekent het pad sp in de juiste kleur in de bitmap.
     * @param sp het te tekenen pad 
     */
    public void tekenPad(Iterable<DirectedEdge> sp) {
        for (DirectedEdge de : sp) {
            int x = t.oneDimToTwoDimXCoordinate(de.from());
            int y = t.oneDimToTwoDimYCoordinate(de.from());
            t.setTileType(x, y, TileType.PATH);
        }
    }
    
    public TileType markAsVisited(int v) {
        int x = t.oneDimToTwoDimXCoordinate(v);
        int y = t.oneDimToTwoDimYCoordinate(v);
        TileType currentType = t.getTileType(x, y);
        t.setTileType(x, y, TileType.UNKNOWN);
        return currentType;
    }

    /**
     * Adds the directed edge e to the edge-weighted digraph.
     *  
     * Uit Sedgwick: tbv Floyd-Warshall
     * @param e the edge
     */
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        adj[v].add(e);
        E++;
    }

}
