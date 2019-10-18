import org.jgrapht.Graph;
import org.jgrapht.alg.scoring.*;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.*;

import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;
import java.io.BufferedReader;

import java.util.*;


public class IteracoesFormigas {

    /**
     * Método que eh capaz de importar um grafo no formato GMl a partir de um objeto do tipo Graph e um nome de arquivo
     * em que estão os dados que serao atribuidos ao grafo.
     *
     * @param graph grafo que será usado para representar representar os dados contidos no arquivo.
     * @param filename nome do arquivo
     * @return um grafo novo que representa o arquivo o qual o nome foi passado como parâmetro.
     */
    public static Graph<String, DefaultEdge> importDefaultGraphGML (Graph<String,DefaultEdge> graph, String filename) {
        VertexProvider<String> vp1 = (label, attributes) -> label;
        EdgeProvider<String, DefaultEdge> ep1 = (from, to, label, attributes) -> new DefaultEdge();
        GmlImporter<String, DefaultEdge> gmlImporter = new GmlImporter<>(vp1, ep1);
        try {
            gmlImporter.importGraph(graph, readFile(filename));
        } catch (ImportException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }

    /**
     * Meotdo responsavel por ler um arquivo.
     *
     * @param filename nome do arquivo
     * @return um objeto do tipo Reader
     */
    static Reader readFile(String filename) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringReader readergml = new StringReader(contentBuilder.toString());
        return readergml;
    }

    /**
     * Metodo que mede o grau de assertatividade de um grafo, esse grau vai de 1 até -1, onde 1 significa que existe um grau
     * de interação entre os vertices extremamente semelhante, 0 se não ah relação entre os vertices e -1 se o grau de relação entre
     * os vértices for extremamente diferente.
     *
     * @param graph grafo que sera avaliado
     * @param <V> vertice
     * @param <E> aresta
     * @return um numero que representa o grau de relação entre os vertices do grafo.
     */
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

    /**
     * Método que seleciona as 5 principais formigas em um set de formigas.
     *
     * @param formigas conjunto de formigas
     * @return uma lista com as 5 formigas principais.
     */
    private static ArrayList cincoPrincipais(Set formigas){
        ArrayList saida = new ArrayList();

        int contador = 0;

        for (Object formiga : formigas) {
            saida.add(formiga);
            contador++;
            if (contador >= 5){
                break;
            }
        }

        return saida;
    }

    /**
     * Om método main eh reponsavel por rodar os algoritmos:
     * - BetweennessCentrality: Para selecionar as 5 formigas que melhor atuaram na condução de informações;
     *
     * - AlphaCentrality: Para selecioanr as 5 formigas mais influentes
     *
     * - ClusteringCoefficient: Para verificarmos se existe ou não a formação de grupos isolados ou nãi de formigas.
     *
     * - AssertativutyCoefficient: Esse algoritmo retorna um double que representa o grau das relacões das formigas, caso
     * seja retornado um double maior que 0 e menor que 1 (que eh o maximo) significa que as formigas se relacionam com
     * outras com grau de interação semelhantes, caso o retorno seja -1 Não há nenhuma relação entre as formigas e se o retorno
     * for menor que 0, mas maior que 1 então as Formigas não se relacionam com outras com grau de interação semelhantes.
     *
     */
    public static void main(String[] args) {

        Graph<String, DefaultEdge> ugraph = new SimpleWeightedGraph<>(DefaultEdge.class);
        ugraph = importDefaultGraphGML(ugraph,".\\src\\antcolony1000.gml");

        BetweennessCentrality<String, DefaultEdge> bc = new BetweennessCentrality<>(ugraph, true);
        System.out.println("Formigas que melhor atuaram na condução de informações: " + cincoPrincipais(bc.getScores().keySet()));


        AlphaCentrality<String, DefaultEdge> ac = new AlphaCentrality<>(ugraph, 0.001);
        System.out.println("Formigas mais influentes: " +  cincoPrincipais(ac.getScores().keySet()));


        ClusteringCoefficient<String, DefaultEdge> cluster =
                new ClusteringCoefficient<>(ugraph);
        System.out.println("Grupos isolados:  " + cluster.getGlobalClusteringCoefficient());

        if (assortativityCoefficient(ugraph) > 0) {
            System.out.println("Formigas se relacionam com outras com grau de interação semelhantes");

        } else if (assortativityCoefficient(ugraph) == -1) {
            System.out.println("Não há nenhuma relação entre as formigas");

        } else if (assortativityCoefficient(ugraph) < 0) {
            System.out.println("Formigas não se relacionam com outras com grau de interação semelhantes");

        }
    }
}
