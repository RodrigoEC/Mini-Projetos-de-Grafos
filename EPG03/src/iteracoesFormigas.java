import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.alg.scoring.AlphaCentrality;
import org.jgrapht.alg.scoring.BetweennessCentrality;
import org.jgrapht.alg.scoring.ClusteringCoefficient;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

/**
 * Alunos:
 * Rodrigo Eloy
 * Leandra Oliveira
 * Jesse Monteiro
 */

public class iteracoesFormigas {

    public static  Graph <String, DefaultEdge> ugraph;

    /**
     * O método main eh reponsavel por rodar os algoritmos.
     */

    public static void main(String[] args) {
        importaGrafo("/home/leandra/Mini-Projetssos-de-Grafos/EPG03/src/antcolony1000.gml");

        System.out.println();
        System.out.println("Primeira Questão:");
        System.out.println("Formigas que melhor atuaram na condução de informações: ");
        primeiraQuestao();
        System.out.println();

        System.out.println();
        System.out.println("Segunda Questão:");
        System.out.println("Formigas mais influentes: ");
        segundaQuestao();
        System.out.println();

        System.out.println();
        System.out.println("Terceira Questão:");
        terceiraQuestao();
        System.out.println();

        System.out.println("Quarta Questão:");
        quartaQuestao();

    }


    /**
     * Método responsavel por importar o grafo que sera processado.
     * @param fileName nome do arquivo.
     */

    public static void importaGrafo(String fileName) {
        ugraph = new SimpleWeightedGraph<>(DefaultEdge.class);
        ugraph = importDefaultGraphGML(ugraph, fileName);
    }

    /**
     * Método que eh capaz de importar um grafo no formato GMl a partir de um objeto do tipo Graph e um nome de arquivo
     * em que estão os dados que serao atribuidos ao grafo.
     *
     * @param graph grafo que será usado para representar representar os dados contidos no arquivo.
     * @param filename nome do arquivo
     * @return um grafo novo que representa o arquivo o qual o nome foi passado como parâmetro.
     */

    private static Graph<String, DefaultEdge>
    importDefaultGraphGML (Graph<String,DefaultEdge> graph, String filename) {

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
     * Metodo responsavel por ler um arquivo.
     *
     * @param filename nome do arquivo
     * @return um objeto do tipo Reader
     */

    private static StringReader readFile(String filename) {
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
     * Metodo responsavel por ultilizar a metrica BetweennessCentrality,para selecionar
     * as formigas que melhor atuaram na condução de informações;
     * O método também possui a funcionalidade de selecionar quais são as 5 formigas
     * que melhor atuaram na condução de informações;
     */

    public static void primeiraQuestao() {
        BetweennessCentrality <String, DefaultEdge> bc =
                new BetweennessCentrality <> (ugraph, true);

        Set<String> setFormigas = new HashSet<String>();
        for(int i = 0; i < 5; i++) {

            String aux = "1";
            for(String formiga : bc.getScores().keySet()) {
                if(bc.getVertexScore(formiga) >= bc.getVertexScore(aux)
                        &&	!setFormigas.contains(formiga)) {

                    aux = formiga;
                }
            }

            setFormigas.add(aux);
        }

        for(String formiga : setFormigas) {
            System.out.print(formiga + " ");
        }
    }

    /**
     * Metodo responsavel por ultilizar a metrica AlphaCentrality para selecionar as formigas mais influentes.
     * O método também possui a funcionalidade de selecionar quais são as 5 formigas mais influentes.
     */

    public static void segundaQuestao() {
        AlphaCentrality <String, DefaultEdge> coefficients =
                new AlphaCentrality<>(ugraph, 0.001);

        Set<String> setFormigas = new HashSet<String>();
        for(int i = 0; i < 5; i++) {

            String aux = "1";
            for(String formiga : coefficients.getScores().keySet()) {
                if(coefficients.getVertexScore(formiga) >= coefficients.getVertexScore(aux)
                        &&	!setFormigas.contains(formiga)) {

                    aux = formiga;
                }
            }

            setFormigas.add(aux);
        }

        for(String formiga : setFormigas) {
            System.out.print(formiga + " ");
        }
    }

    /**
     * Metodo responsavel por ultilizar a metrica ClusteringCoefficient para verificarmos
     * se existe ou não a formação de grupos isolados ou não de formigas.
     */

    public static void terceiraQuestao() {
        ClusteringCoefficient <String, DefaultEdge> cluster =
                new ClusteringCoefficient <>(ugraph);

        System.out.println("Coeficiente: " + cluster.getGlobalClusteringCoefficient());

        if (cluster.getGlobalClusteringCoefficient() >= 0 || cluster.getGlobalClusteringCoefficient() <= 1){
            System.out.println("É possivel observar a formação de grupos isolados. ");
        }

        else if (cluster.getGlobalClusteringCoefficient() >= 0 || cluster.getGlobalClusteringCoefficient() <= -1){
            System.out.println("Não é possivel observar a formação de grupos isolados. ");

        }
    }

    /**
     *  Metodo responsavel por ultilizar a metrica AssertativutyCoefficient.
     *  Esse algoritmo retorna um double que representa o grau das relacões das formigas, caso
     * seja retornado um double maior que 0 e menor que 1 (que eh o maximo) significa que as formigas se relacionam com
     * outras com grau de interação semelhantes, caso o retorno seja -1 Não há nenhuma relação entre as formigas e se o retorno
     * for menor que 0, mas maior que 1 então as Formigas não se relacionam com outras com grau de interação semelhantes.v
     *
     */

    public static void quartaQuestao() {

        System.out.println("Coeficiente " + assortativityCoefficient(ugraph));


        if (assortativityCoefficient(ugraph) > 0) {
            System.out.println("Formigas se relacionam com outras com grau de interação semelhantes.");

        } else if (assortativityCoefficient(ugraph) == -1) {
            System.out.println("Não há nenhuma relação entre as formigas.");

        } else if (assortativityCoefficient(ugraph) < 0) {
            System.out.println("Formigas não se relacionam com outras com grau de interação semelhantes.");

        }

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

    private static <V,E> double assortativityCoefficient (Graph <V, E> graph) {
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
}