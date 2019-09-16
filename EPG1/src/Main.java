
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author henri
 *Aluno 1: Rodrigo Eloy
 *Aluno 2:Jesse Monteiro
 *Aluno 3:Leandra
 *Aluno 4:Paulo Henrique Ribeiro
 */

/**
 * 
 * Classe Main do Projeto
 *
 */
public class Main {
	
    public static void main(String[] args) {
    	/**
    	 * cria uma lista de strings do tipo "pair"
    	 */
        ArrayList<Pair<String, String>> listaAmigos = new ArrayList<Pair<String, String>>();
        /**
         *adiciona os pares "a","b" a lista
         */
        listaAmigos.add(new Pair<String, String>("a","b"));
        /**
         *adiciona os pares "b","c" a lista
         */
        listaAmigos.add(new Pair<String, String>("b","c"));
        /** 
         *adiciona os pares "c","a" a lista
         */
        listaAmigos.add(new Pair<String, String>("c","a"));
        /**
         *adiciona os pares "c","b" a lista
         */
        listaAmigos.add(new Pair<String, String>("c","b"));

        /**Imprime o grafo
         * 
         */
        System.out.println(CirculoAmigos.retornaCirculos(listaAmigos));

    }


}
