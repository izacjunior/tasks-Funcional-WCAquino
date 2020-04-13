package br.ce.wcaquino.tasks.funcional;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaSucesso() {

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
	public void deveSalvarTarefaSemDesscricao() {
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
	public void deveSalvarTarefaSemData() {

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
	public void deveSalvarTarefaComDataPassada() {

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
