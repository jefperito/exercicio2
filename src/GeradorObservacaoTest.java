import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class GeradorObservacaoTest {

    @Test
    public void deveRetornarObservacaoVaziaCasoListaForVazia() {
        assertEquals("", new GeradorObservacao().geraObservacao(new ArrayList<String>()));
    }

    @Test
    public void deveRetornarObservacaoContendoTextoDeFaturaSimplesRemessaCasoPossuirUmaFaturaNaLista() {
        List<String> lista = new ArrayList();
        lista.add("10000");
        assertEquals("Fatura da nota fiscal de simples remessa: 10000.", new GeradorObservacao().geraObservacao(lista));
    }

    @Test
    public void deveRetornarObservacaoContendoTextoDeFaturasSimplesRemessasCasoPossuirDuasFaturasOuMaisNaLista() {
        List<String> lista = new ArrayList();
        lista.add("10000");
        lista.add("12000");
        assertEquals("Fatura das notas fiscais de simples remessa: 10000 e 12000.", new GeradorObservacao().geraObservacao(lista));
    }

    @Test
    public void deveAdicionarPorVirgulaParaSepararAsFaturasCasoPossuirTresOuMaisFaturas() {
        List<String> lista = new ArrayList();
        lista.add("10000");
        lista.add("12000");
        lista.add("12000");
        assertEquals("Fatura das notas fiscais de simples remessa: 10000, 12000 e 12000.", new GeradorObservacao().geraObservacao(lista));
    }

    @Test
    public void deveGerarObservacaoVazioCasoFaturasSejaVazia() {
        List<Fatura> faturas = new ArrayList<>();
        assertEquals("", new GeradorObservacao().geraObservacaoFatura(faturas));
    }

    @Test
    public void deveObterObservacaoReferenteAUmItemNaLista() {
        List<Fatura> faturas = new ArrayList<>();
        faturas.add(new Fatura("000001", new BigDecimal("2.90")));

        assertEquals("Fatura da nota fiscal de simples remessa: 000001 cujo valor é R$ 2,90. Total = 2,90.", new GeradorObservacao().geraObservacaoFatura(faturas));
    }

    @Test
    public void deveObterObservacaoReferenteADoisItensNaLista() {
        List<Fatura> faturas = new ArrayList<>();
        faturas.add(new Fatura("000001", new BigDecimal("2.90")));
        faturas.add(new Fatura("000002", new BigDecimal("3.00")));

        assertEquals("Fatura das notas fiscais de simples remessa: 000001 cujo valor é R$ 2,90 e 000002 cujo valor " +
                "é R$ 3,00. Total = 5,90.", new GeradorObservacao().geraObservacaoFatura(faturas));
    }

    @Test
    public void deveObterValorComNumeroEvalorCasoSejaUmaListaDeFaturasComTotal() {
        List<Fatura> faturas = new ArrayList<>();
        faturas.add(new Fatura("000001", new BigDecimal("2.90")));
        faturas.add(new Fatura("000002", new BigDecimal("3.00")));
        faturas.add(new Fatura("000003", new BigDecimal("5.00")));

        assertEquals("Fatura das notas fiscais de simples remessa: 000001 cujo valor é R$ 2,90, 000002 cujo valor " +
                "é R$ 3,00 e 000003 cujo valor é R$ 5,00. Total = 10,90.", new GeradorObservacao().geraObservacaoFatura(faturas));
    }
}
