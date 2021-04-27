package es.urjc.code.daw.library.Selenium;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import es.urjc.code.daw.library.Application;
import io.github.bonigarcia.wdm.WebDriverManager;

	@SpringBootTest
	(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
	class SeleniumTest {

		@LocalServerPort
	    int port;

		WebDriver driver;
		
		@BeforeAll
		public static void setupClass() {
			WebDriverManager.chromedriver().setup();
		}
		
		@BeforeEach
		public void setup() {
			driver = new ChromeDriver();
		}
		
		@AfterEach
		public void teardown() {
			if(driver != null) {
				driver.quit();
			}
		}
		
		@Test
		void addBook() throws InterruptedException {
			driver.get("http://localhost:"+this.port+"/");

			driver.findElement(By.id("NewBook")).click();
			
			driver.findElement(By.name("title")).sendKeys("El señor de los anillos");
			driver.findElement(By.name("description")).sendKeys("Es una novela de fantasía épica escrita por el filólogo y escritor británico J. R. R. Tolkien");
			//Se rellena los campos del nuevo libro
			driver.findElement(By.id("Save")).click();

			
			driver.findElement(By.id("AllBooks")).click();

			assertNotNull(driver.findElement(By.partialLinkText("El señor de los anillos")));
			//Se comprueba que se ha creado el libro
		}
		
		@Test
		void deleteBook() throws InterruptedException {
			driver.get("http://localhost:"+this.port+"/");
			
			driver.findElement(By.linkText("SUEÑOS DE ACERO Y NEON")).click();
			//Se encuentra el libro a borar
			
			driver.findElement(By.id("Remove")).click();
			//Se borra el libro
			
			assertThrows(NoSuchElementException.class,()-> {
				driver.findElement(By.partialLinkText("NEON"));
				});
			//Se comprueba que aparezca el libro borrado
			
		}
}
