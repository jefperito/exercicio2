import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class FaturaTest {

    @Test
    public void deveObterValorFormatadoStringificadoUsandoSeuValorENumero() {
        assertEquals("000000 cujo valor é R$ 0,00", new Fatura().toString());
    }

    @Test
    public void deveFormatarFaturaCorretamenteCasoTenhaValorENumeroAtribuido() {
        Fatura fatura = new Fatura("123456", new BigDecimal("9.99"));
        assertEquals("123456 cujo valor é R$ 9,99", fatura.toString());
    }
}
