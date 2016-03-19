package br.com.caelum.agiletickets.acceptance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ReservaTest {

	
	private static WebDriver browser;

	@Before
	public void setUp() throws Exception {
		browser = new FirefoxDriver();
	}

	@After
	public static void teardown() {
		browser.close();
	}
	
	@Test
	public void deveAdicionar10porcentoNosUltimos5Ingressos(){
		
	}

	
	
}
