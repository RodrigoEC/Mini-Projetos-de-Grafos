import org.jgrapht.Graph;
import org.jgrapht.alg.scoring.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class IteracoesFormigas {

    public static Graph<String, DefaultWeightedEdge> importWeightedGraphCSV
            (Graph<String, DefaultWeightedEdge> graph, String filename) {
        // WEIGHTED EDGE LIST
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String sCurrentLine = br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                String[] attributes = sCurrentLine.split(",");
                graph.addVertex(attributes[0]);
                graph.addVertex(attributes[1]);
                DefaultWeightedEdge e = new DefaultWeightedEdge();
                graph.addEdge(attributes[0], attributes[1], e);
                graph.setEdgeWeight(e, new Double(attributes[2]).doubleValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    public static <V, E> double assortativityCoefficient(Graph<V, E> graph) {
        // from: https://github.com/Infeligo/jgrapht-metrics/blob/master/src/main/java/org/jgrapht/metrics/AssortativityCoefficientMetric.java
        double edgeCount = graph.edgeSet().size();
        double n1 = 0, n2 = 0, dn = 0;

        for (E e : graph.edgeSet()) {
            int d1 = graph.degreeOf(graph.getEdgeSource(e));
            int d2 = graph.degreeOf(graph.getEdgeTarget(e));

            n1 += d1 * d2;
            n2 += d1 + d2;
            dn += d1 * d1 + d2 * d2;
        }
        n1 /= edgeCount;
        n2 = (n2 / (2 * edgeCount)) * (n2 / (2 * edgeCount));
        dn /= (2 * edgeCount);

        return (n1 - n2) / (dn - n2);
    }

    public static void main(String[] args) {

        Graph<String, DefaultWeightedEdge> ugraph;

        Graph<String, DefaultWeightedEdge> weightGraph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        ugraph = importWeightedGraphCSV(weightGraph, "/home/leandra/antcolony50.csv");

        BetweennessCentrality<String, DefaultWeightedEdge> bc = new BetweennessCentrality<>(ugraph, true);


        System.out.println("Formigas que melhor atuaram na condução de informações: " + bc.getScores());


        AlphaCentrality<String, DefaultWeightedEdge> ac =
                new AlphaCentrality<>(ugraph, 0.001);
        System.out.println("Formigas mais influentes: " + ac.getScores().keySet());


        ClusteringCoefficient<String, DefaultWeightedEdge> cluster =
                new ClusteringCoefficient<>(ugraph);
        System.out.println("Grupos isolados:  " + cluster.getGlobalClusteringCoefficient());


        if (assortativityCoefficient(ugraph) == 1) {
            System.out.println("Formigas se relacionam com outras com grau de interação semelhantes");

        } else if (assortativityCoefficient(ugraph) == 0) {
            System.out.println("Formigas não se relacionam com outras com grau de interação semelhantes");

        } else if (assortativityCoefficient(ugraph) == -1) {
            System.out.println("Não há nenhuma relação entre as formigas");

        }
    }
}