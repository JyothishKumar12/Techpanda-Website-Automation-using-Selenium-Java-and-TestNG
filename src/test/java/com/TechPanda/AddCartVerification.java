package com.TechPanda;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddCartVerification {


	private WebDriver driver;

	@BeforeTest
	public void SetupAll() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}


	@Test 
	public void cartVerification() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().window().maximize();

        SoftAssert softAssert = new SoftAssert();
		
		String errorMessage="The requested quantity for 'Sony Xperia' is not available";
		String cartMessage = "SHOPPING CART IS EMPTY";

		driver.findElement(By.xpath("//a[.='Mobile']")).click();

		//		Click Sony Xperia Add To Cart

		driver.findElement(By.xpath("(//span[.='Add to Cart']/ancestor::button[@title='Add to Cart'])[3]")).click();

		//		Update no of mobiles with 1000

		driver.findElement(By.xpath("//input[@class='input-text qty']")).sendKeys("1000");
		driver.findElement(By.xpath("//button[@class='button btn-update']")).click();
		WebElement actualError = driver.findElement(By.xpath("//p[@class='item-msg error']"));
		
		softAssert.assertEquals(errorMessage,actualError.getText(),"Error Text message is not Equal");
		//		Click Empty shopping Cart
		driver.findElement(By.xpath("(//span[.='Empty Cart'])[2]"));
		
		WebElement actualCartMessage = driver.findElement(By.xpath("//h1[starts-with(text(),'Shopping')]"));
		
		softAssert.assertEquals(cartMessage,actualCartMessage.getText(),"Text is Same");
		softAssert.assertAll();
	}



	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
