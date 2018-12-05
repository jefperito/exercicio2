import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fatura {
    private String numero = "000000";
    private BigDecimal valor = BigDecimal.ZERO;

    public Fatura(){}

    public Fatura(String numero, BigDecimal valor) {
        this.numero = numero;
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return String.format("%s cujo valor é R$ %,.2f", numero, valor.setScale(2, RoundingMode.HALF_DOWN));
    }
}
