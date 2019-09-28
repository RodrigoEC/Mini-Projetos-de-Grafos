package test.java;

import static org.junit.Assert.*;

import java.util.HashSet;

import main.java.VendaImoveis;
import org.junit.Before;
import org.junit.Test;

public class VendaImoveisTest2 {

    // Estruturas de dados a serem usadas nos testes
    VendaImoveis v1;
    HashSet<String> imoveis1;


    @Before
    public void init () {
        // Criando Dados de Teste
        v1 = new VendaImoveis("/home/leandra/Mini-Projetos-de-Grafossss/EPG2/src/main/resources/Vizinhanca2.csv");

        imoveis1 = new HashSet<String>();
        imoveis1.add("I1"); imoveis1.add("I2"); imoveis1.add("I3"); imoveis1.add("I4"); imoveis1.add("I5"); imoveis1.add("I6");  imoveis1.add("I7");
        imoveis1.add("I8");imoveis1.add("I9");imoveis1.add("I10"); imoveis1.add("I11"); imoveis1.add("I12");imoveis1.add("I13");
    }

    @Test
    public void testApartamentoMaisProximoAcademia() {
        String imovel = v1.localizaImovel("ACADEMIA",imoveis1);
        assertEquals("I3",imovel);
    }

    @Test
    public void testApartamentoMaisProximoAeroporto() {
        String imovel = v1.localizaImovel("AEROPORTO",imoveis1);
        assertEquals("I3",imovel);
    }

    @Test
    public void testApartamentoMaisProximoAutoEscola() {
        String imovel = v1.localizaImovel("AUTOESCOLA",imoveis1);
        assertEquals("I7",imovel);
    }

    @Test
    public void testApartamentoMaisProximoCinema() {
        String imovel = v1.localizaImovel("CINEMA",imoveis1);
        assertEquals("I13",imovel);
    }

    @Test
    public void testApartamentoMaisProximoEscola() {
        String imovel = v1.localizaImovel("ESCOLA",imoveis1);
        assertEquals("I9",imovel);
    }

    @Test
    public void testApartamentoMaisProximoFeira() {
        String imovel = v1.localizaImovel("FEIRA",imoveis1);
        assertEquals("I3",imovel);
    }

    @Test
    public void testApartamentoMaisProximoHospital() {
        String imovel = v1.localizaImovel("HOSPITAL",imoveis1);
        assertEquals("I6",imovel);

    }
    @Test
    public void testApartamentoMaisProximoIgreja() {
        String imovel = v1.localizaImovel("IGREJA",imoveis1);
        assertEquals("I11",imovel);
    }

    @Test
    public void testApartamentoMaisProximoParque() {
        String imovel = v1.localizaImovel("PARQUE",imoveis1);
        assertEquals("I1",imovel);
    }

    @Test
    public void testApartamentoMaisProximoPraca() {
        String imovel = v1.localizaImovel("PRACA",imoveis1);
        assertEquals("I13",imovel);
    }

    @Test
    public void testApartamentoMaisProximoRodoviaria() {
        String imovel = v1.localizaImovel("RODOVIARIA",imoveis1);
        assertEquals("I6",imovel);
    }

    @Test
    public void testApartamentoMaisProximoUniversidade() {
        String imovel = v1.localizaImovel("UNIVERSIDADE",imoveis1);
        assertEquals("I7",imovel);
    }

    @Test
    public void testApartamentosProximosAEscola () {
        String imovel = v1.localizaImovel("ESCOLA",imoveis1);
        assertTrue(imovel.equals("I9")||imovel.equals("I5"));
    }

    @Test
    public void testApartamentosProximoAQ1() {
        String imovel = v1.localizaImovel("Q1",imoveis1);
        assertEquals("I6",imovel);
    }

    @Test
    public void testApartamentosProximoAQ2() {
        String imovel = v1.localizaImovel("Q2",imoveis1);
        assertEquals("I4",imovel);
    }

    @Test
    public void testApartamentosProximoAQ3() {
        String imovel = v1.localizaImovel("Q3",imoveis1);
        assertEquals("I2",imovel);
    }
    @Test
    public void testApartamentosProximoAQ4() {
        String imovel = v1.localizaImovel("Q4",imoveis1);
        assertEquals("I3",imovel);
    }

    @Test
    public void testApartamentosProximoAQ5() {
        String imovel = v1.localizaImovel("Q5",imoveis1);
        assertEquals("I8",imovel);
    }

    @Test
    public void testApartamentosProximoAQ6() {
        String imovel = v1.localizaImovel("Q6",imoveis1);
        assertEquals("I6",imovel);
    }

    @Test
    public void testApartamentosProximoAQ7() {
        String imovel = v1.localizaImovel("Q7",imoveis1);
        assertEquals("I13",imovel);
    }

    @Test
    public void testApartamentosProximoAQ8() {
        String imovel = v1.localizaImovel("Q8",imoveis1);
        assertEquals("I1",imovel);
    }

    @Test
    public void testApartamentosProximoAQ9() {
        String imovel = v1.localizaImovel("Q9",imoveis1);
        assertEquals("I3",imovel);
    }

    @Test
    public void testApartamentosProximoAQ10() {
        String imovel = v1.localizaImovel("Q10",imoveis1);
        assertEquals("I1",imovel);
    }

    @Test
    public void testApartamentosProximoAQ11() {
        String imovel = v1.localizaImovel("Q11",imoveis1);
        assertEquals("I1",imovel);
    }

    @Test
    public void testApartamentosProximoAQ12() {
        String imovel = v1.localizaImovel("Q12",imoveis1);
        assertEquals("I13",imovel);
    }

    @Test
    public void testApartamentosProximoAQ13() {
        String imovel = v1.localizaImovel("Q13",imoveis1);
        assertEquals("I7",imovel);
    }

    @Test
    public void testApartamentosProximoAQ14() {
        String imovel = v1.localizaImovel("Q14",imoveis1);
        assertEquals("I7",imovel);
    }

    @Test
    public void testApartamentosProximoAQ15() {
        String imovel = v1.localizaImovel("Q15",imoveis1);
        assertEquals("I5",imovel);
    }

    @Test
    public void testApartamentosProximoAQ16() {
        String imovel = v1.localizaImovel("Q16",imoveis1);
        assertEquals("I13",imovel);
    }

    @Test
    public void testApartamentosProximoAQ17() {
        String imovel = v1.localizaImovel("Q17",imoveis1);
        assertEquals("I10",imovel);
    }

    @Test
    public void testApartamentosProximoAQ18() {
        String imovel = v1.localizaImovel("Q18",imoveis1);
        assertEquals("I2",imovel);
    }

    @Test
    public void testApartamentosProximoAQ19() {
        String imovel = v1.localizaImovel("Q19",imoveis1);
        assertEquals("I10",imovel);
    }

    @Test
    public void testApartamentosProximoAQ20() {
        String imovel = v1.localizaImovel("Q20",imoveis1);
        assertEquals("I12",imovel);
    }

    @Test
    public void testApartamentosProximoAQ21() {
        String imovel = v1.localizaImovel("Q21",imoveis1);
        assertEquals("I6",imovel);
    }

    @Test
    public void testApartamentosProximoAQ22() {
        String imovel = v1.localizaImovel("Q22",imoveis1);
        assertEquals("I11",imovel);
    }

    @Test
    public void testApartamentosProximoAQ23() {
        String imovel = v1.localizaImovel("Q23",imoveis1);
        assertEquals("I7",imovel);
    }

    @Test
    public void testApartamentosProximoAQ24() {
        String imovel = v1.localizaImovel("Q24",imoveis1);
        assertEquals("I9",imovel);
    }

    @Test
    public void testPontodeInteresseNulo () {
        String imovel = v1.localizaImovel(null,imoveis1);
        assertNull(imovel);
    }

    @Test
    public void testPontodeInteresseInexistente () {
        String imovel = v1.localizaImovel("invalido",imoveis1);
        assertNull(imovel);
    }

    @Test
    public void testImovelNulo () {
        String imovel = v1.localizaImovel("ESCOLA", null);
        assertNull(imovel);
    }
}
