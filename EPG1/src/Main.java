
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        ArrayList<Pair<String, String>> listaAmigos = new ArrayList<Pair<String, String>>();
        listaAmigos.add(new Pair<String, String>("a","b"));
        listaAmigos.add(new Pair<String, String>("b","c"));
        listaAmigos.add(new Pair<String, String>("c","a"));
        listaAmigos.add(new Pair<String, String>("c","b"));


        System.out.println(CirculoAmigos.retornaCirculos(listaAmigos));

    }


}
