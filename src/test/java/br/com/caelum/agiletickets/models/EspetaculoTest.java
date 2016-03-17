package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void DeveCadastrarSessaoUnicaDiaria() {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2015, 03, 20),
				new LocalDate(2015, 03, 20), new LocalTime(20, 00), Periodicidade.DIARIA);
		
		assertTrue(sessoes.size() == 1);
	}
	
	@Test
	public void DeveCadastrarMultiplasSessoesDiarias() {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2015, 03, 20),
				new LocalDate(2015, 03, 25), new LocalTime(20, 00), Periodicidade.DIARIA);
		
		assertTrue(sessoes.size() == 6);
	}
	
	@Test
	public void DeveCadastrarMultiplasSessoesSemanais() {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2015, 03, 10),
				new LocalDate(2015, 03, 25), new LocalTime(20, 00), Periodicidade.SEMANAL);
		
		assertTrue(sessoes.size() == 3);
	}
	
	
	@Test
	public void DeveCriarSessoesComDataCorretaDiario() {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2015, 03, 20),
				new LocalDate(2015, 03, 22), new LocalTime(20, 00), Periodicidade.DIARIA);
				
		assertTrue(sessoes.get(0).getDia().equals("20/03/15"));
		assertTrue(sessoes.get(1).getDia().equals("21/03/15"));
		assertTrue(sessoes.get(2).getDia().equals("22/03/15"));
		
	}
	
	@Test
	public void DeveCriarSessoesComDataCorretaSemanal() {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2015, 03, 2),
				new LocalDate(2015, 03, 22), new LocalTime(20, 00), Periodicidade.SEMANAL);
				
		assertTrue(sessoes.get(0).getDia().equals("02/03/15"));
		assertTrue(sessoes.get(1).getDia().equals("09/03/15"));
		assertTrue(sessoes.get(2).getDia().equals("16/03/15"));
		
	}
	
	@Test
	public void SessaoDeveTerEspetaculo() {
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(2015, 03, 2),
				new LocalDate(2015, 03, 22), new LocalTime(20, 00), Periodicidade.SEMANAL);
		
		for (Sessao sessao : sessoes) {
			assertFalse(sessao.getEspetaculo() == null);
		}
	}
	
}
