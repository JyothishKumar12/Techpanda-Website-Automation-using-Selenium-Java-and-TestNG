package com.TechPanda;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobilePriceVerification {
	
	private WebDriver driver;

	@BeforeSuite
	public void setUpAll() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		
		
	}
	
	@Test
	public void mobilePriceVerification() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
	WebElement sonyXperia =	driver.findElement(By.xpath("//span[@id='product-price-1']/child::span"));
	String price = sonyXperia.getText();
	System.out.println(price);
	driver.findElement(By.xpath("//a[@title='Xperia']")).click();
	
	WebElement sonyXperia2 = driver.findElement(By.xpath("//span[@id='product-price-1']"));
	
	Assert.assertEquals(sonyXperia,sonyXperia2);
	
//	if(sonyXperia == sonyXperia2) {
//		System.out.println("Both product List and Details page price are same");
//	}
	}
	
	
	
	@AfterTest
	public void tearDown() {
	driver.close();	
	}
}
