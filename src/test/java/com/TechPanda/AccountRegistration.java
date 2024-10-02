package com.TechPanda;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AccountRegistration {
	public WebDriver driver;

	@BeforeTest
	public void SetupAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}


	@Test
	public void createAccount() throws InterruptedException {
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.findElement(By.xpath("(//a[.='My Account'])[2]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Create')]")).click();
		String email = "krishna@gmail.com, parvathy@gmail.com";
		driver.findElement(By.id("firstname")).sendKeys("Micha");
		driver.findElement(By.id("middlename")).sendKeys("jh");
		driver.findElement(By.id("lastname")).sendKeys("cornor");
		driver.findElement(By.id("email_address")).sendKeys("tzx1w3@gmail.com");
		driver.findElement(By.id("password")).sendKeys("12ERTyu");
		driver.findElement(By.id("confirmation")).sendKeys("12ERTyu");
		driver.findElement(By.xpath("(//span[.='Register'])[2]")).click();
		WebElement welcomems=	driver.findElement(By.xpath("//p[contains(text(),'Welcome,')]"));
		System.out.println(welcomems.getText());
		
//		Tv Click
		driver.findElement(By.xpath("//a[.='TV']")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'Add to W')])[1]")).click();
		Thread.sleep(1000);
//		driver.findElement(By.id("email")).sendKeys(email);
//		driver.findElement(By.id("pass")).sendKeys("");
//		driver.findElement(By.id("send2")).click();
		driver.findElement(By.xpath("(//span[.='Share Wishlist'])[2]")).click();
		driver.findElement(By.id("email_address")).sendKeys("Vasu@gmail.com, Vasanthi@gmail.com");
		driver.findElement(By.id("message")).sendKeys("vanbu");
		driver.findElement(By.xpath("//button/descendant::span/child::span[contains(text(),'Share W')]")).click();
		String wishList ="Your Wishlist has been shared.";
		String wishList2=driver.findElement(By.xpath("//li[@class='success-msg']/child::ul/li/span")).getText();

		Assert.assertEquals(wishList, wishList2,"Not Shared Wish list");
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
