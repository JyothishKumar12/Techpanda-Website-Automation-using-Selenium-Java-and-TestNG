package com.TechPanda;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase9 {
	private WebDriver driver;
	private WebDriverWait wait;
	
	@BeforeTest
	public void SetupAll() {
				WebDriverManager.chromedriver().setup();
		        driver = new ChromeDriver();
	}

	@Test
	public void test9() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(3000));
		driver.findElement(By.xpath("//a[.='Mobile']")).click();
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"coupon_code\"]")).sendKeys("GURU50");
		String price = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[5]/span/span")).getText();
		System.out.println(price);
		Double p = Double.valueOf(price.substring(1));
		System.out.println("value of p "+ ": " +p);
		Double discount = ((p*5)/100);
		driver.findElement(By.xpath("//*[@id=\"discount-coupon-form\"]/div/div/div/div/button/span/span")).click();
	String rDiscount=	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span"))).getText();
	Double realDiscount = Double.valueOf(rDiscount.substring(2));
	
	Assert.assertNotEquals(realDiscount, discount,"Generated 5% discount");
	
	}
	
}
