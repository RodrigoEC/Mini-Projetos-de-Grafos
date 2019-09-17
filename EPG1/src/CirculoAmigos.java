
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
/**
 * 
 * @author
 *Aluno 1: Rodrigo Eloy
 *Aluno 2:Jesse Monteiro
 *Aluno 3:Leandra Oliveira
 *Aluno 4:Paulo Henrique Ribeiro
 */


public class CirculoAmigos {
	/**
	 * O metodo retornaCirculos recebe como parametro uma lista com elementos do tipo String que representam os vertices .
	 * Apos isso o primeiro e segundo elementos sao selecionados por estruturas condicionais que os validam e caso os pares sejam nao vazios, 
	 * sao adicionados como vertices de um grafo "g" atraves do metodo "addVertex" ,
	 *  em seguida eh selecionado um par de elementos a cada dois vertices para montar uma aresta atraves da classe "addEdge"
	 * 
	 * Apos montar o grafo g, eh usada a classe ConnectivityInspector, que tem um metodo chamado connectedSets, 
	 * esse metodo retorna uma lista de sets, e esses sets representam os vértices que estão conectados.
	 * 
	 * 
	 * Por fim ele retorna uma lista de conjuntos, em que cada conjunto eh composto por um circulo de amigos
	 */
	public static List <Set <String>> retornaCirculos (List<Pair<String,String>> paresAmigos) {
	
		Graph<String, DefaultEdge> g =  new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	
		for (int i = 0; i < paresAmigos.size(); i++) {
			
		
			if (!"".equals(paresAmigos.get(i).getFirst().trim())) {
		
				g.addVertex(paresAmigos.get(i).getFirst());
			}
	
			if (!"".equals(paresAmigos.get(i).getSecond().trim())) {
			
				g.addVertex(paresAmigos.get(i).getSecond());
			}
	
			if (!"".equals(paresAmigos.get(i).getSecond().trim()) && !"".equals(paresAmigos.get(i).getFirst().trim())) {
			
				g.addEdge(paresAmigos.get(i).getFirst(), paresAmigos.get(i).getSecond());
			}
		}

		ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<String, DefaultEdge>(g);
		List<Set<String>> closeFriendsList = inspector.connectedSets();

		return closeFriendsList;
	}

}
