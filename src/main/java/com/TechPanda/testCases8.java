package com.TechPanda;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testCases8 {
	WebDriver driver;

	@BeforeTest
	public void SetupAll() {
				WebDriverManager.chromedriver().setup();
//		System.setProperty("webdriver.chrome.driver","./Driver/chromedriver.exe");
		driver = new ChromeDriver();
	}


	@Test
	public void Test7() throws InterruptedException {
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3000));
		driver.findElement(By.xpath("(//span[.='Account'])[1]")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'My A')])[1]")).click();
		driver.findElement(By.id("email")).sendKeys("tzx1w3@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("12ERTyu");
		driver.findElement(By.id("send2")).click();


		driver.findElement(By.xpath("(//a[.='Reorder'])[1]")).click();

		String subTotal = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='price'])[4]"))).getText();
		System.out.println(subTotal +"subtotal");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/input"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/input"))).sendKeys("2");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span/ancestor::button[@name='update_cart_action'])[4]"))).click();

		String grandTotal=	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong/child::span[@class='price']"))).getText();
		System.out.println(grandTotal +"grand total");

		try {
			Assert.assertNotSame(subTotal, grandTotal,"Value is updated");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Proceed to')]/ancestor::button)[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button/descendant::span[.='Continue'])[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[.='Continue']/ancestor::button)[3]"))).click();


		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"p_method_checkmo\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"payment-buttons-container\"]/button"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"review-buttons-container\"]/button"))).click();

		String orderNo = 	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/p[1]/a"))).getText();;


		System.out.println(orderNo);
		try {
			Assert.assertTrue(orderNo!=null,"Orerno is generated"+orderNo);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
