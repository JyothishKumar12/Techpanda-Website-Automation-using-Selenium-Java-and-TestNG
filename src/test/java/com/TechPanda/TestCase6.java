package com.TechPanda;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase6 {

	public WebDriver driver;

	@BeforeTest
	public void SetupAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	
	@Test
	public void testCases6() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.findElement(By.xpath("(//span[.='Account'])[1]")).click();
		driver.findElement(By.xpath("//li[@class=' last']")).click();
		
		
		driver.findElement(By.id("email")).sendKeys("tzx1w3@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("12ERTyu");
		driver.findElement(By.id("send2")).click();
		
//	WebElement mywishlistdrop =	driver.findElement(By.xpath("(//div[@class='block-content'])[2]"));
//	Select wlist = new Select(mywishlistdrop);
//	wlist.selectByVisibleText("My Wishlist");	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3000));
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[.='My Wishlist'])[2]"))).click();
//		driver.findElement(By.xpath("//a[.='My Wishlist']")).click();
		driver.findElement(By.xpath("(//span[.='Add to Cart'])[2]")).click();
		
		driver.findElement(By.xpath("(//span[contains(text(),'Proceed to Chec')])[1]")).click();
		
		
//		Address
		driver.findElement(By.id("billing:street1")).sendKeys("abc");
		driver.findElement(By.id("billing:city")).sendKeys("New york");
	WebElement states =	driver.findElement(By.id("billing:region_id"));
	
		Select state = new Select(states);
		state.selectByVisibleText("New York");
		driver.findElement(By.id("billing:postcode")).sendKeys("542896");
		
		driver.findElement(By.id("billing:telephone")).sendKeys("12345678");
		
		driver.findElement(By.xpath("(//span[.='Continue'])[2]")).click();
		String price = "$5";
		WebElement realPrice = driver.findElement(By.xpath("//label/child::span[@class='price']"));
		Assert.assertNotEquals(price,realPrice.getText());
		driver.findElement(By.xpath("(//span[.='Continue'])[6]")).click();
	}
}
