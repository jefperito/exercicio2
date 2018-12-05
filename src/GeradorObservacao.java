
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class GeradorObservacao { 

	public String geraObservacao(List<String> numeroNotasFiscais) {
		if (!numeroNotasFiscais.isEmpty()) {
			return constroiObservacao(numeroNotasFiscais);
		}		
		return "";
	}

	public String geraObservacaoFatura(List<Fatura> faturas) {
	    if (!faturas.isEmpty()) {
            String observacaoFaturas = constroiObservacao(faturas.stream().map(Fatura::toString).collect(Collectors.toList()));
            return String.format("%s Total = %,.2f.", observacaoFaturas, calculaValorTotal(faturas));
        }
        return "";
    }

    private BigDecimal calculaValorTotal(List<Fatura> faturas) {
        return faturas.stream()
                .map(Fatura::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_DOWN);
    }

    private String constroiObservacao(List<String> notasFiscais) {
        int quantidadeNotasFiscais = notasFiscais.size();
        StringBuilder stringBuilder = quantidadeNotasFiscais >= 2 ? new StringBuilder("Fatura das notas fiscais de simples remessa: ") :
                new StringBuilder("Fatura da nota fiscal de simples remessa: ");

        for (int indice = 0; indice < quantidadeNotasFiscais; indice++) {
            String prefixo = getPrefixo(indice, quantidadeNotasFiscais);
            String sufixo = getSufixo(indice, quantidadeNotasFiscais);

            stringBuilder.append(prefixo).append(notasFiscais.get(indice)).append(sufixo);
        }
        return stringBuilder.toString();
	}

    private String getSufixo(int indice, int quantidadeNotasFiscais) {
	    if (indice + 1 == quantidadeNotasFiscais) {
	        return ".";
        }
        return "";
    }

    private String getPrefixo(int indice, int quantidadeNotasFiscais) {
	    if (indice == 0) {
	        return "";
        }
        if (indice + 1 < quantidadeNotasFiscais) {
            return ", ";
        }
        return " e ";
    }
}