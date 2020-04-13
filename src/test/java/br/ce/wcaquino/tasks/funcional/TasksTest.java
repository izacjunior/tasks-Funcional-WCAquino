package br.ce.wcaquino.tasks.funcional;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "C:\\Desenvolvimento\\Tools\\chromedriver.exe");
	
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.6:4444/wd/hub"), cap);
	//	WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://192.168.0.6:8001/tasks");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaSucesso() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();
		try {

			driver.findElement(By.id("addTodo")).click();

			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			driver.findElement(By.id("saveButton")).click();

			String text = driver.findElement(By.id("message")).getText();
			assertEquals(text, "Success!");

		} finally {
			driver.quit();
		}
	}

	@Test
	public void deveSalvarTarefaSemDesscricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {

			driver.findElement(By.id("addTodo")).click();

			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			driver.findElement(By.id("saveButton")).click();

			String text = driver.findElement(By.id("message")).getText();
			assertEquals(text, "Fill the task description");

		} finally {
			driver.quit();
		}
	}

	@Test
	public void deveSalvarTarefaSemData() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();
		try {

			driver.findElement(By.id("addTodo")).click();

			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			driver.findElement(By.id("saveButton")).click();

			String text = driver.findElement(By.id("message")).getText();
			assertEquals(text, "Fill the task description");

		} finally {
			driver.quit();
		}
	}

	@Test
	public void deveSalvarTarefaComDataPassada() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();
		try {

			driver.findElement(By.id("addTodo")).click();

			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

			driver.findElement(By.id("saveButton")).click();

			String text = driver.findElement(By.id("message")).getText();
			assertEquals(text, "Due date must not be in past");

		} finally {
			driver.quit();
		}
	}
}
