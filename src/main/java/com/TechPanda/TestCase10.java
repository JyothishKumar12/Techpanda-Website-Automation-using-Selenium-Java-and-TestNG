package com.TechPanda;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase10 {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@BeforeTest
	public void SetupAll() {
				WebDriverManager.chromedriver().setup();
		        driver = new ChromeDriver();
	}

	@Test
	public void test10() {
		driver.get("http://live.techpanda.org/index.php/backendlogin");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(4000));

//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(""))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='username']"))).sendKeys("user01");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='login']"))).sendKeys("guru99com");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='loginForm']/div/div[5]/input"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"message-popup-window\"]/div[1]/a"))).click();
		Actions mH = new Actions(driver);
	  WebElement sales =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nav\"]/li[1]/a/span")));
		mH.moveToElement(sales).build().perform();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nav\"]/li[1]/ul/li[1]/a/span"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"id_baefe14ad071e6aaff2040908242bbb2\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div[2]/div/div/p[1]/em"))).getText();
		}
}
