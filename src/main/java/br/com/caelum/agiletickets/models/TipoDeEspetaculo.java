package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

public enum TipoDeEspetaculo {
	
	CINEMA {
		public BigDecimal calculaPreco(Sessao sessao){
			BigDecimal preco;
			if(calculaPercentagemDeIngressosDisponiveis(sessao) <= 0.05) { 
				preco = calculaPrecoComAdicional(sessao, 0.10);
			} else {
				preco = sessao.getPreco();
			}
			return preco;
		}
	}, 
	SHOW {
		@Override
		public BigDecimal calculaPreco(Sessao sessao) {
			// TODO Auto-generated method stub
			BigDecimal preco;
			if(calculaPercentagemDeIngressosDisponiveis(sessao) <= 0.05) { 
				preco = calculaPrecoComAdicional(sessao, 0.10);
			} else {
				preco = sessao.getPreco();
			}
			return preco;
		}
	}, 
	TEATRO {
		@Override
		public BigDecimal calculaPreco(Sessao sessao) {
			// TODO Auto-generated method stub
			BigDecimal preco;
			preco = sessao.getPreco();
			return preco;
		}
	}, 
	BALLET {
		@Override
		public BigDecimal calculaPreco(Sessao sessao) {
			// TODO Auto-generated method stub
			BigDecimal preco;
			if(calculaPercentagemDeIngressosDisponiveis(sessao) <= 0.50) { 
				preco = calculaPrecoComAdicional(sessao, 0.20);
			} else {
				preco = sessao.getPreco();
			}
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
			return preco;
		}
	}, 
	ORQUESTRA {
		@Override
		public BigDecimal calculaPreco(Sessao sessao) {
			// TODO Auto-generated method stub
			BigDecimal preco;
			if(calculaPercentagemDeIngressosDisponiveis(sessao) <= 0.50) { 
				preco =  calculaPrecoComAdicional(sessao, 0.20);
			} else {
				preco = sessao.getPreco();
			}

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
			return preco;
		}
	};
	
	public abstract BigDecimal calculaPreco(Sessao sessao);
	
	private static BigDecimal calculaPrecoComAdicional(Sessao sessao, double percentual) {
		return sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(percentual)));
	}

	private static double calculaPercentagemDeIngressosDisponiveis(Sessao sessao) {
		return (sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue();
	}
}
