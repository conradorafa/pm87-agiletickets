package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		TipoDeEspetaculo tipoDoEspetaculo = sessao.getEspetaculo().getTipo();
		if(tipoDoEspetaculo.equals(TipoDeEspetaculo.CINEMA) || tipoDoEspetaculo.equals(TipoDeEspetaculo.SHOW)) {
			if(calculaPercentagemDeIngressosDisponiveis(sessao) <= 0.05) { 
				preco = calculaPrecoComAdicional(sessao, 0.10);
			} else {
				preco = sessao.getPreco();
			}
		} else if(tipoDoEspetaculo.equals(TipoDeEspetaculo.BALLET)) {
			if(calculaPercentagemDeIngressosDisponiveis(sessao) <= 0.50) { 
				preco = calculaPrecoComAdicional(sessao, 0.20);
			} else {
				preco = sessao.getPreco();
			}
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} else if(tipoDoEspetaculo.equals(TipoDeEspetaculo.ORQUESTRA)) {
			if(calculaPercentagemDeIngressosDisponiveis(sessao) <= 0.50) { 
				preco =  calculaPrecoComAdicional(sessao, 0.20);
			} else {
				preco = sessao.getPreco();
			}

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		}  else {
			
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calculaPrecoComAdicional(Sessao sessao, double percentual) {
		return sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(percentual)));
	}

	private static double calculaPercentagemDeIngressosDisponiveis(Sessao sessao) {
		return (sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue();
	}

}