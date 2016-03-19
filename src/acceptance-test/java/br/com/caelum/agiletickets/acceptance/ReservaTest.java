package br.com.caelum.agiletickets.acceptance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.caelum.agiletickets.acceptance.page.SessaoPage;


public class ReservaTest {

	public static String BASE_URL = "http://localhost:8080";
	private static WebDriver browser;
	private SessaoPage sessao;
	
	@Before
	public void setUp() throws Exception {
		browser = new FirefoxDriver();
		sessao = new SessaoPage(browser);
	}

	@After
	public static void teardown() {
		browser.close();
	}
	
	@Test
	public void aoReservarOsUltimos5IngressosDeveAcrescentar10Porcento(){
		sessao.reservaIngresso("1");
	}

	
	
}
