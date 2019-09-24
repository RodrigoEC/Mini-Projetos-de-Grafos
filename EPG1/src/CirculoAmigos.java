
import 	java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
/**
 * Essa classe representa um ou vários círculos de amigos.
 *
 * Aluno 1: Rodrigo Eloy
 * Aluno 2:	Jesse Monteiro
 * Aluno 3:	Leandra Oliveira
 * Aluno 4:	Paulo Henrique Ribeiro
 *
 *
 */


public class CirculoAmigos {

	/**
	 * Esse metodo eh responsavel por receber uma lista de pares, pares esses que representam os terminais de uma aresta,
	 * ou seja, vértices, e retorna uma lista de conjuntos, onde cada conjunto da lista representa um círculo de amigos.
	 * Caso um dos pares da lista passada como parâmetro tiver uma string vazia ou somente de espaços esse vértice e a aresta
	 * que o tem como terminal não são criados, todavia o vértice considerado valido eh adicionado ao grafo.
	 *
	 */
	public static List <Set <String>> retornaCirculos (List<Pair<String,String>> paresAmigos) {
		Graph<String, DefaultEdge> grafo =  new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

		for (int i = 0; i < paresAmigos.size(); i++) {
			if (!"".equals(paresAmigos.get(i).getFirst().trim())) {
				grafo.addVertex(paresAmigos.get(i).getFirst());
			}
			if (!"".equals(paresAmigos.get(i).getSecond().trim())) {
				grafo.addVertex(paresAmigos.get(i).getSecond());
			}

			if (!"".equals(paresAmigos.get(i).getSecond().trim()) && !"".equals(paresAmigos.get(i).getFirst().trim())) {
				grafo.addEdge(paresAmigos.get(i).getFirst(), paresAmigos.get(i).getSecond());
			}
		}

		ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<String, DefaultEdge>(grafo);

		return inspector.connectedSets();
	}

}
