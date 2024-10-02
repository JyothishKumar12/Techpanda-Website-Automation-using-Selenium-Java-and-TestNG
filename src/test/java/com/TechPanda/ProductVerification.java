package com.TechPanda;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductVerification {



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
		driver.findElement(By.xpath("//a[.='Mobile']")).click();

		//        Add to compare Iphone

		String mobile1 = driver.findElement(By.xpath("(//a[text()='IPhone'])[1]")).getText();
		System.out.println(mobile1);
		driver.findElement(By.xpath("(//a[contains(text(),'Add to')])[2]")).click();
		String mobile2 = driver.findElement(By.xpath("(//a[text()='Sony Xperia'])[1]")).getText();
		System.out.println(mobile2);

		String parentHandle = driver.getWindowHandle();
		parentHandle.trim();
		//        Add to compare Xperia

		driver.findElement(By.xpath("(//a[contains(text(),'Add to')])[6]")).click();
		driver.findElement(By.xpath("(//button[@class='button'])[1]")).click();

		//        Heading of New Windows
		String ExpectedHeading = "COMPARE PRODUCTS";

		Set<String>wHandles =  driver.getWindowHandles();

		//		System.out.println( ((Object)parentHandle).getClass().getSimpleName()  );

		List<String> c = new ArrayList<String>(wHandles);

		driver.switchTo().window(c.get(1));
		String heading =   driver.findElement(By.xpath("//h1[contains(text(),COMPARE)]")).getText();
		softAssert.assertEquals(heading,ExpectedHeading,"New Window Heading is not the Same");

		//		WebElement popUpPage = driver.findElement(By.xpath("//tr[contains(@class,'product-shop-row top')]"));
		//		System.out.println(popUpPage.getText());
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3000)) ;
		WebElement pmobile2 =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[.='Sony Xperia']")));
		WebElement pmobile1 =	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[.='IPhone']")));

		softAssert.assertEquals(mobile1,pmobile1.getText(), "Iphone is equal");
		softAssert.assertEquals(mobile2,pmobile2.getText(),"Sony xperia is equal");
		driver.close();
		softAssert.assertAll();

	}
	
		@AfterTest
		public void tearDown() {
			driver.close();
		}

}
