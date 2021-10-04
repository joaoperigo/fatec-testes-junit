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

public class REQ01MantemLivroTests {
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

	//ED08
	//EX 1
	
	@Test
	public void cadastrarlivro() {
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().setSize(new Dimension(945, 1020));
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();

		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1234");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("George Orwell");
		driver.findElement(By.id("titulo")).sendKeys("Revolução dos Bichos");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();

		driver.findElement(By.cssSelector("tr:nth-child(1)")).click();https://ts-scel-web.herokuapp.com/sig/livros

		assertTrue(driver.getPageSource().contains("1234"));
		assertTrue(driver.getPageSource().contains("George Orwell"));
		
		//no caso da lista o exemplo da aula apaga todos os livros? pois á multiplos <a> com excluir na pagina
		driver.findElement(By.linkText("Excluir")).click();
	}
	 @Test
	  public void consultarLivro() {
		 
		 //cria o livro de novo para garantir que tem livro
		 driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().setSize(new Dimension(945, 1020));
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();

		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1234");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("George Orwell");
		driver.findElement(By.id("titulo")).sendKeys("Revolução dos Bichos");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();

		driver.findElement(By.cssSelector("td:nth-child(2)")).click();

		 
		 
	    driver.get("https://ts-scel-web.herokuapp.com/login");
	    driver.manage().window().setSize(new Dimension(945, 1020));
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
	    driver.findElement(By.linkText("Livros")).click();
	    
	    driver.findElement(By.linkText("Lista de Livros")).click();
	    driver.findElement(By.cssSelector("td:nth-child(2)")).click();
	    
	  }
	 @Test
	  public void atualizarLivro() {
	    driver.get("https://ts-scel-web.herokuapp.com/login");
	    driver.manage().window().setSize(new Dimension(945, 1020));
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
	    driver.findElement(By.linkText("Livros")).click();
	    
	    driver.findElement(By.linkText("Lista de Livros")).click();
	    driver.findElement(By.cssSelector("tr:nth-child(1) .btn-primary")).click();
	    driver.findElement(By.name("autor")).click();
	    driver.findElement(By.name("autor")).sendKeys("Jorge");
	    driver.findElement(By.cssSelector(".btn-primary")).click();
	  }
	 
	 @Test
	  public void excluirLivro() {
		    driver.get("https://ts-scel-web.herokuapp.com/login");
		    driver.manage().window().setSize(new Dimension(945, 1020));
		    driver.findElement(By.name("username")).click();
		    driver.findElement(By.name("username")).sendKeys("jose");
		    driver.findElement(By.name("password")).sendKeys("123");
		    driver.findElement(By.cssSelector("button")).click();
		    driver.findElement(By.linkText("Livros")).click();
		    
		    driver.findElement(By.linkText("Lista de Livros")).click();
		    //aqui não usei  driver.findElement(By.linkText("Excluir")).click(); pois é para excluir livro e não livros
		    driver.findElement(By.cssSelector(".btn-danger")).click();
	  }
	//ED08
	//EX 2

	@Test
	public void cadastrarlivroComAutorVazio() {
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().setSize(new Dimension(945, 1020));
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();

		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1234");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("");
		driver.findElement(By.id("titulo")).sendKeys("Revolução dos Bichos");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
	}

	@Test
	public void cadastrarlivroComAutorExcessoCaracteres() {
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().setSize(new Dimension(945, 1020));
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();

		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1234");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eros quam, tincidunt eu lacinia eget");
		driver.findElement(By.id("titulo")).sendKeys("Revolução dos Bichos");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
	}

	@Test
	public void cadastrarlivroComTituloVazio() {
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().setSize(new Dimension(945, 1020));
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();

		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1234");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("George Orwell");
		driver.findElement(By.id("titulo")).sendKeys("");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();

	}
	
	@Test
	public void cadastrarlivroComTituloExcessoCaracteres() {
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().setSize(new Dimension(945, 1020));
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();
		
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1234");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("George Orwell");
		driver.findElement(By.id("titulo")).sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eros quam, tincidunt eu lacinia eget");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		
	}
	
	
	@Test
	public void cadastrarlivroComISBNVazio() {
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().setSize(new Dimension(945, 1020));
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();
		
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("George Orwell");
		driver.findElement(By.id("titulo")).sendKeys("Grande Irmão");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();

	}
	
	
	@Test
	public void cadastrarlivroComISBNAlfanumerico() {
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().setSize(new Dimension(945, 1020));
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();
		
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("l2bA");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("George Orwell");
		driver.findElement(By.id("titulo")).sendKeys("Grande Irmão");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();

	}
	
	
	@Test
	public void cadastrarlivroComISBNExcessoCaracteres() {
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().setSize(new Dimension(945, 1020));
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();
		
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("12345");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("George Orwell");
		driver.findElement(By.id("titulo")).sendKeys("Grande Irmão");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		
	}
	
	
	
	 @Test
	  public void consultarLivroComISBNInvalido() {
	    driver.get("https://ts-scel-web.herokuapp.com/login");
	    driver.manage().window().setSize(new Dimension(945, 1020));
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
	    driver.findElement(By.linkText("Livros")).click();
	    driver.findElement(By.linkText("Lista de Livros")).click();
	    driver.findElement(By.cssSelector("td:nth-child(2)")).click();
	  }
	 
	
	 @Test
	  public void consultarLivroComISBNRepetido() {
			driver.get("https://ts-scel-web.herokuapp.com/login");
			driver.manage().window().setSize(new Dimension(945, 1020));
			driver.findElement(By.name("username")).click();
			driver.findElement(By.name("username")).sendKeys("jose");
			driver.findElement(By.name("password")).sendKeys("123");
			driver.findElement(By.cssSelector("button")).click();
			driver.findElement(By.linkText("Livros")).click();
			espera();

			driver.findElement(By.id("isbn")).click();
			driver.findElement(By.id("isbn")).sendKeys("2374");
			driver.findElement(By.id("autor")).click();
			driver.findElement(By.id("autor")).sendKeys("Paulo");
			driver.findElement(By.id("titulo")).sendKeys("Macarte");
			driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();

			driver.findElement(By.cssSelector("td:nth-child(2)")).click();

			assertTrue(driver.getPageSource().contains("1234"));
			assertTrue(driver.getPageSource().contains("George Orwell"));
			
			driver.findElement(By.linkText("Excluir")).click();
	  }


	public void espera() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
