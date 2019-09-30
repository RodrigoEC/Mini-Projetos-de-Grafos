package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;


/**
 * Essa classe representa um processo de procura da casa perfeita para determinado cliente, levando em conta o ponto de interesse do
 * cliente e as casas que estao disponíveis para aluguel ao redor da cidade.
 *
 * Alunos:
 * - Rodrigo Eloy
 * - Leandra Oliveira
 * - Jesse Monteiro
 * - Henrique Ribeiro
 */
public class VendaImoveis {
	Graph <String,DefaultWeightedEdge> distrito;

	/**
	 * O metodo vendaImoveis eh responsavel por atribuir ao atributo "distrito" o grafo que improtado pelo metodo
	 * "importWeightedGraphCSV"
	 *
	 * @param fileName nome do arquivo que será utilizado pra iniciar o atributo "distrito"
	 */
	public VendaImoveis(String fileName) {
		Graph<String, DefaultWeightedEdge> weightGraph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.distrito = importWeightedGraphCSV(weightGraph, fileName);

	}

	/**
	 * O método localizaImovel eh responsavel por localizar o imovel mais perto de um ponto de interesse. Atraves do
	 * metodo getPaths da classe DijkstraShortestPath eh selecionado o menor caminho entre cada vertice do grafo e o ponto
	 * de interesse. Por fim, eh selecionado o imovel do set de imoveis que possui o menor caminho, dentre os ja selecionados,
	 * entre ele e o ponto de interesse. Caso o ponto de interesse passado não seja um vertice do grafo ou o conjunto de
	 * imoveis seja null ou nao exista nenhum elemento no set passado o metodo retorna null.
	 *
	 *
	 *
	 * @param pontodeInteresse Ponto de interesse.
	 * @param imoveis Conjunto de imoveis disponiveis para aluguel.
	 * @return O nome do imovel alugavel mais perto do ponto de interesse.
	 */
	public String localizaImovel (String pontodeInteresse, Set <String> imoveis) {
		if (!this.distrito.containsVertex(pontodeInteresse) || imoveis == null || imoveis.size() == 0) {
			return null;
		}

		DijkstraShortestPath<String, DefaultEdge> dij = new DijkstraShortestPath(this.distrito);
		ShortestPathAlgorithm.SingleSourcePaths<String, DefaultEdge> shortPath  = dij.getPaths(pontodeInteresse);

		String imovelMaisPerto = "";
		Double menorDistancia = Double.MAX_VALUE;

		for (String imovel: imoveis) {
			Double distancia = shortPath.getPath(imovel).getWeight();

			if (distancia < menorDistancia) {
				menorDistancia = distancia;
				imovelMaisPerto = imovel;
			}
		}

		return imovelMaisPerto;
	}

	/**
	 * Metodo retirado da classe MyJgraphTUtil disponibilizado pela professora Patricia para os alunos. O metodo tem a funcao de
	 * importar um grafo cujo nome do arquivo que o contem eh passado como parametro.
	 *
	 * @param graph grafo que tera os vertices e arestas iguais ao do arquivo.
	 * @param filename nome do arquivo.
	 * @return um grafo igual ao grafo ccujo nome foi passado como parametro
	 */
	public static Graph<String,DefaultWeightedEdge> importWeightedGraphCSV
			(Graph<String,DefaultWeightedEdge> graph, String filename) {
		// WEIGHTED EDGE LIST
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine = br.readLine();
			while ((sCurrentLine = br.readLine()) != null) {
				String [] attributes = sCurrentLine.split(",");
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



}
