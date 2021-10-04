package testeDeSistema;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class REQ02MantemAlunoTests {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void ct01_cadastrar_aluno_com_sucesso() {
		// *********************************************************************************
		// dado que o aluno não esta cadastrado
		// *********************************************************************************
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Alunos")).click();
		espera();
		// quando o usuario cadastrar um aluno
		driver.findElement(By.id("ra")).click();
		driver.findElement(By.id("ra")).sendKeys("1111");
		driver.findElement(By.id("nome")).click();
		driver.findElement(By.id("nome")).sendKeys("jose");
		driver.findElement(By.id("email")).sendKeys("jose@gmail.com");
		driver.findElement(By.id("cep")).sendKeys("04280130");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		// entao apresenta as informacoes do aluno
		assertEquals("Rua Frei João", driver.findElement(By.cssSelector("td:nth-child(6)")).getText());
		assertEquals(("Lista de alunos"), driver.findElement(By.id("titulopagina")).getText());
		assertEquals("https://ts-scel-web.herokuapp.com/sig/alunos", driver.getCurrentUrl());
		assertTrue(driver.getPageSource().contains("1111"));
		// *********************************************************************************
		// teardown - exclusao do registro
		// *********************************************************************************
		driver.findElement(By.linkText("Excluir")).click();
	}

	@Test
	public void ct02_atualiza_aluno_com_sucesso() {
		// ***********************************************************************************
		// dado que o aluno esta cadastrado
		// ***********************************************************************************
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Alunos")).click();
		espera();
		driver.findElement(By.id("ra")).click();
		driver.findElement(By.id("ra")).sendKeys("1111");
		driver.findElement(By.id("nome")).sendKeys("jose");
		driver.findElement(By.id("email")).sendKeys("jose@gmail.com");
		driver.findElement(By.id("cep")).sendKeys("04280130");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		espera();
		assertEquals("Rua Frei João", driver.findElement(By.cssSelector("td:nth-child(6)")).getText());
		// **********************************************************************************
		// quando o usuario altera o CEP do endereco
		// **********************************************************************************
		driver.findElement(By.linkText("Editar")).click();
		driver.findElement(By.cssSelector(".form-group:nth-child(2)")).click();
		driver.findElement(By.id("cep")).clear();
		driver.findElement(By.id("cep")).sendKeys("08545160");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		// **********************************************************************************
		// entao o sistema apresenta as informações do aluno com o CEP alterado
		// **********************************************************************************
		assertTrue(driver.getPageSource().contains("Rua João Soliman"));
		// **********************************************************************************
		// teardown - exclusao do registro
		// **********************************************************************************
		driver.findElement(By.linkText("Excluir")).click();
	}

	public void espera() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ct03cadastraraluno_ja_cadastrado() {
		// ******************************************************************************
		// dado que o aluno esta cadastrado
		// ******************************************************************************
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Alunos")).click();
		espera();
		driver.findElement(By.id("ra")).click();
		driver.findElement(By.id("ra")).sendKeys("1111");
		driver.findElement(By.id("nome")).click();
		driver.findElement(By.id("nome")).sendKeys("jose");
		driver.findElement(By.id("email")).sendKeys("jose@gmail.com");
		driver.findElement(By.id("cep")).sendKeys("04280130");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		// asserts com objetivo de dupuracao
		assertEquals("Rua Frei João", driver.findElement(By.cssSelector("td:nth-child(6)")).getText());
		assertEquals(("Lista de alunos"), driver.findElement(By.id("titulopagina")).getText());
		assertEquals("https://ts-scel-web.herokuapp.com/sig/alunos", driver.getCurrentUrl());
		assertTrue(driver.getPageSource().contains("1111"));
		// ******************************************************************************
		// quando tenta cadastrar um aluno ja cadastrado
		// ******************************************************************************
		driver.findElement(By.linkText("Voltar")).click();
		driver.findElement(By.linkText("Alunos")).click();
		espera();
		driver.findElement(By.id("ra")).click();
		driver.findElement(By.id("ra")).sendKeys("1111");
		driver.findElement(By.id("nome")).sendKeys("jose");
		driver.findElement(By.id("email")).sendKeys("jose@gmail.com");
		driver.findElement(By.id("cep")).sendKeys("04280130");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		// *******************************************************************************
		// entado retorna dados invalidos
		// *******************************************************************************
		assertEquals("Dados invalidos", driver.findElement(By.cssSelector(".text-danger")).getText());
		// ******************************************************************************
		// teardown - exclusao do registro
		// ******************************************************************************
		vars.put("window_handles", driver.getWindowHandles());
		driver.findElement(By.cssSelector(".btn:nth-child(2)")).click(); // botao consultar
		vars.put("win7071", waitForWindow(2000));
		driver.switchTo().window(vars.get("win7071").toString());
		driver.findElement(By.cssSelector(".delete")).click();
	}

	public String waitForWindow(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) vars.get("window_handles");
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);
		}
		return whNow.iterator().next();
	}
}
