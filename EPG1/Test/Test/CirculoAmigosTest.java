import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.jgrapht.alg.util.Pair;
import org.junit.Test;
import sun.util.resources.cldr.zh.CalendarData_zh_Hans_HK;


public class CirculoAmigosTest {


    @Test
    public void test1() {
        ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
        listaAmigos.add(new Pair<String, String>("a","b"));
        listaAmigos.add(new Pair<String, String>("c","d"));
        listaAmigos.add(new Pair<String, String>("e","f"));
        listaAmigos.add(new Pair<String, String>("f","g"));
        listaAmigos.add(new Pair<String, String>("i","j"));

        // Circulos que devem ser encontrados
        Set <String> c1 = new HashSet<String> (); c1.add("a"); c1.add("b");
        Set <String> c2 = new HashSet<String> (); c2.add("c"); c2.add("d");
        Set <String> c3 = new HashSet<String> (); c3.add("e"); c3.add("f"); c3.add("g");
        Set <String> c4 = new HashSet<String> (); c4.add("i"); c4.add("j");

        // Calculando c�rculos
        List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

        assertTrue(circuloAmigos.contains(c1));
        assertTrue(circuloAmigos.contains(c2));
        assertTrue(circuloAmigos.contains(c3));
        assertTrue(circuloAmigos.contains(c4));
        assertEquals(circuloAmigos.size(),4);
    }

    @Test
    public void test2 () {
        ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
        listaAmigos.add(new Pair<String, String>("a","b"));
        listaAmigos.add(new Pair<String, String>("c","b"));
        listaAmigos.add(new Pair<String, String>("e","a"));
        listaAmigos.add(new Pair<String, String>("f","b"));
        listaAmigos.add(new Pair<String, String>("f","a"));

        // Circulos que devem ser encontrados
        Set <String> c1 = new HashSet<String> (); c1.add("a"); c1.add("b"); c1.add("c"); c1.add("e"); c1.add("f");

        // Calculando c�rculos
        List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

        assertTrue(circuloAmigos.contains(c1));
        assertEquals(circuloAmigos.size(),1);
    }

    @Test
    public void test3 () {
        ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();

        // Calculando c�rculos
        List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

        assertEquals(circuloAmigos.size(),0);
    }

    @Test
    public void TestCirculosdeAmigosTodosSeConhecem(){
        ArrayList<Pair<String, String>> listaAmigos = new ArrayList<>();
        listaAmigos.add(new Pair<String, String>("a","b"));
        listaAmigos.add(new Pair<String, String>("b","c"));
        listaAmigos.add(new Pair<String, String>("c","d"));
        listaAmigos.add(new Pair<String, String>("d","a"));
        listaAmigos.add(new Pair<String, String>("d","b"));
        listaAmigos.add(new Pair<String, String>("c","a"));

        Set<String> conjunto1 = new HashSet<>();
        conjunto1.add("a");
        conjunto1.add("b");
        conjunto1.add("c");
        conjunto1.add("d");

        List<Set<String>> arraySaida = new ArrayList<>();
        arraySaida.add(conjunto1);

        assertEquals(arraySaida, CirculoAmigos.retornaCirculos(listaAmigos));

        List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
        assertTrue(circuloAmigos.contains(conjunto1));

        assertEquals(circuloAmigos.size(),1);

    }

    @Test
    public void TesteCirculoVazio(){
        ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
        listaAmigos.add(new Pair<String, String>("",""));

        List<Set<String>> arraySaida = new ArrayList<>();
        Set<String> conjunto1 = new HashSet<>();

        assertEquals(arraySaida, CirculoAmigos.retornaCirculos((listaAmigos)));

        List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
        assertFalse(circuloAmigos.contains(conjunto1));

        assertEquals(circuloAmigos.size(),0);
    }

    @Test
    public void TesteCiclosDisjuntos(){
        ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
        listaAmigos.add(new Pair<String, String>("a","b"));
        listaAmigos.add(new Pair<String, String>("c","d"));
        listaAmigos.add(new Pair<String, String>("e","a"));
        listaAmigos.add(new Pair<String, String>("f","b"));
        listaAmigos.add(new Pair<String, String>("f","a"));

        Set <String> conjunto1 = new HashSet<String> ();
        conjunto1.add("a");
        conjunto1.add("b");
        conjunto1.add("e");
        conjunto1.add("f");
        conjunto1.add("e");
        conjunto1.add("f");

        Set <String> conjunto2 = new HashSet<String> ();
        conjunto2.add("c");
        conjunto2.add("d");

        List<Set<String>> arraySaida = new ArrayList<>();
        arraySaida.add(conjunto1);
        arraySaida.add(conjunto2);

        assertEquals(arraySaida, CirculoAmigos.retornaCirculos(listaAmigos));

        List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

        assertTrue(circuloAmigos.contains(conjunto1));
        assertTrue(circuloAmigos.contains(conjunto2));
        assertEquals(circuloAmigos.size(),2);
    }

    @Test
    public void TesteAmigosSemAmigosEmComumCirculosDisjuntos(){
        ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
        listaAmigos.add(new Pair<String, String>("a","b"));
        listaAmigos.add(new Pair<String, String>("c","d"));
        listaAmigos.add(new Pair<String, String>("e","f"));

        Set<String> conjunto1 = new HashSet<>();
        conjunto1.add("a");
        conjunto1.add("b");

        Set<String> conjunto2 = new HashSet<>();
        conjunto2.add("c");
        conjunto2.add("d");

        Set<String> conjunto3 = new HashSet<>();
        conjunto3.add("e");
        conjunto3.add("f");

        List <Set<String>> arraySaida = new ArrayList<>();
        arraySaida.add(conjunto1);
        arraySaida.add(conjunto2);
        arraySaida.add(conjunto3);

        assertEquals(arraySaida, CirculoAmigos.retornaCirculos(listaAmigos));

        List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
        assertTrue(circuloAmigos.contains(conjunto1));
        assertTrue(circuloAmigos.contains(conjunto2));
        assertTrue(circuloAmigos.contains(conjunto3));

        assertEquals(circuloAmigos.size(),3);

    }

    @Test
    public void TesteTresCirculosDisjuntos(){
        ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
        listaAmigos.add(new Pair<String, String>("a","b"));
        listaAmigos.add(new Pair<String, String>("b","c"));
        listaAmigos.add(new Pair<String, String>("c","d"));
        listaAmigos.add(new Pair<String, String>("d","a"));

        listaAmigos.add(new Pair<String, String>("e","f"));
        listaAmigos.add(new Pair<String, String>("f","g"));
        listaAmigos.add(new Pair<String, String>("g","h"));
        listaAmigos.add(new Pair<String, String>("h","e"));

        listaAmigos.add(new Pair<String, String>("i","j"));
        listaAmigos.add(new Pair<String, String>("j","k"));
        listaAmigos.add(new Pair<String, String>("k","l"));
        listaAmigos.add(new Pair<String, String>("l","i"));

        Set<String> conjunto1 = new HashSet<>();
        conjunto1.add("a");
        conjunto1.add("b");
        conjunto1.add("c");
        conjunto1.add("d");

        Set <String> conjunto2 = new HashSet<>();
        conjunto2.add("e");
        conjunto2.add("f");
        conjunto2.add("g");
        conjunto2.add("h");

        Set<String> conjunto3 = new HashSet<>();
        conjunto3.add("i");
        conjunto3.add("j");
        conjunto3.add("k");
        conjunto3.add("l");

        List<Set<String>> arraySaida = new ArrayList<>();
        arraySaida.add(conjunto1);
        arraySaida.add(conjunto2);
        arraySaida.add(conjunto3);
        assertEquals(arraySaida, CirculoAmigos.retornaCirculos(listaAmigos));

        List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

        assertTrue(circuloAmigos.contains(conjunto1));
        assertTrue(circuloAmigos.contains(conjunto2));
        assertTrue(circuloAmigos.contains(conjunto3));

        assertEquals(circuloAmigos.size(),3);

    }

    @Test
    public void TesteDoisCiclosDisjuntos(){
        ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
        listaAmigos.add(new Pair<String, String>("a",""));
        listaAmigos.add(new Pair<String, String>("c","d"));
        listaAmigos.add(new Pair<String, String>("d","e"));
        listaAmigos.add(new Pair<String, String>("e","f"));

        Set<String> conjunto1 = new HashSet<>();
        conjunto1.add("a");

        Set<String> conjunto2 = new HashSet<>();
        conjunto2.add("c");
        conjunto2.add("d");
        conjunto2.add("e");
        conjunto2.add("f");

        List<Set<String>> arraySaida= new ArrayList<>();
        arraySaida.add(conjunto1);
        arraySaida.add(conjunto2);

        assertEquals(arraySaida, CirculoAmigos.retornaCirculos(listaAmigos));

        List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
        assertTrue(circuloAmigos.contains(conjunto1));
        assertTrue(circuloAmigos.contains(conjunto2));

        assertEquals(circuloAmigos.size(),2);
    }

    @Test
    public void TesteNaoExisteCirculoDeAmigos(){
        ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
        listaAmigos.add(new Pair<String, String>("a",""));
        listaAmigos.add(new Pair<String, String>("b",""));
        listaAmigos.add(new Pair<String, String>("c",""));
        listaAmigos.add(new Pair<String, String>("d",""));

        Set<String> conjunto1 = new HashSet<>();
        conjunto1.add("a");

        Set<String> conjunto2 = new HashSet<>();
        conjunto2.add("b");

        Set<String> conjunto3 = new HashSet<>();
        conjunto3.add("c");

        Set<String> conjunto4 = new HashSet<>();
        conjunto4.add("d");

        List<Set<String>> arraySaida = new ArrayList<>();
        arraySaida.add(conjunto1);
        arraySaida.add(conjunto2);
        arraySaida.add(conjunto3);
        arraySaida.add(conjunto4);

        assertEquals(arraySaida, CirculoAmigos.retornaCirculos(listaAmigos));

        List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
        assertTrue(circuloAmigos.contains(conjunto1));
        assertTrue(circuloAmigos.contains(conjunto2));
        assertTrue(circuloAmigos.contains(conjunto3));
        assertTrue(circuloAmigos.contains(conjunto4));

        assertEquals(circuloAmigos.size(),4);
    }


    @Test
    public void TestPessoaSemAmigos(){
        ArrayList<Pair<String,String>> listaAmigos = new ArrayList <Pair<String,String>> ();
        listaAmigos.add(new Pair<String, String>("a",""));

        Set<String> conjunto1 = new HashSet<>();
        conjunto1.add("a");

        List<Set<String>> arraySaida = new ArrayList<>();
        arraySaida.add(conjunto1);

        assertEquals(arraySaida, CirculoAmigos.retornaCirculos(listaAmigos));

        List <Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);
        assertTrue(circuloAmigos.contains(conjunto1));

        assertEquals(circuloAmigos.size(),1);

    }
}