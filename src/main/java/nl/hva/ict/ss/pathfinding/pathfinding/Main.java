package nl.hva.ict.ss.pathfinding.pathfinding;

import nl.hva.ict.ss.pathfinding.tileworld.TileWorldUtil;
import nl.hva.ict.ss.pathfinding.weigthedgraph.EdgeWeightedDigraph;
import org.junit.Test;

/**
 * TODO make sure your code is compliant with the HBO-ICT coding conventions!
 *
 * @author Jim Dekker, Rene Sasbrink & Rowan Visser
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Test om te kijken op hoeveel plekken de knopen zijn onderzocht
        TileWorldUtil.outputDir = "/Users/rowanvi/Desktop/Project/School/Sorting/PathFinding/Images/";
        System.out.printf("ID;Length Dijkstra;Length Floyd;Costs Dijkstra; Costs Floyd\n");
        for (int i = 1; i <= 23; i++) {
            // Read the graph directly from a image
            EdgeWeightedDigraph graafDijkstra = new EdgeWeightedDigraph("i" + i);
            // Get the start and end node
            final int start = graafDijkstra.getStart();
            final int finish = graafDijkstra.getEnd();

            //Run everything
            final Dijkstra dijkstra = new Dijkstra(graafDijkstra, start);
            EdgeWeightedDigraph graafFloyd = new EdgeWeightedDigraph("i" + i);
            FloydWarshall floyd = new FloydWarshall(graafFloyd.createAdjMatrixEdgeWeightedDigraph());

            if (dijkstra.hasPathTo(finish)) {
                System.out.println("I" + i + "|Aantal onderzochte knooppunten Dijkstra : " + dijkstra.getDijkstraCounter());
                System.out.println("I" + i + "|Aantal onderzochte knooppunten Floyd : " + floyd.getFloydCounter());

            } else {
                System.out.printf("i%d;-;-;-;-\n", i);
            }
        }
    }

}
