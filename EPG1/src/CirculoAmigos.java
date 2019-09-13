
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;


public class CirculoAmigos {
	
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
