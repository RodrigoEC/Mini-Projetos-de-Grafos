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


public class VendaImoveis {
	Graph <String,DefaultWeightedEdge> distrito;

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

	public VendaImoveis(String fileName) {
		Graph<String, DefaultWeightedEdge> weightGraph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.distrito = importWeightedGraphCSV(weightGraph, fileName);

	}

	public String localizaImovel (String pontodeInteresse, Set <String> imoveis) {
		if (this.distrito.containsVertex(pontodeInteresse) && imoveis!= null && imoveis.size() > 0) {

			DijkstraShortestPath<String, DefaultEdge> dij = new DijkstraShortestPath(this.distrito);

			ShortestPathAlgorithm.SingleSourcePaths<String, DefaultEdge> shortPath  = dij.getPaths(pontodeInteresse);

			String imovelMaisPerto = "";
			Double menorDistancia = null;

			for (String imovel: imoveis) {
				Double distancia = shortPath.getPath(imovel).getWeight();
				if (menorDistancia == null || distancia < menorDistancia) {
					menorDistancia = distancia;
					imovelMaisPerto = imovel;
				}
				shortPath.getPath(imovel);

			}
			return imovelMaisPerto;
		} else {
			return null;
		}
	}



}
