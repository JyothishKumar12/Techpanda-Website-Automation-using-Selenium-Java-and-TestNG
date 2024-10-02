package com.TechPanda;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase7 {

	WebDriver driver;
	
	@BeforeTest
	public void SetupAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	
	@Test
	public void Test7() throws InterruptedException {
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		
		driver.findElement(By.xpath("(//span[.='Account'])[1]")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'My A')])[1]")).click();
		driver.findElement(By.id("email")).sendKeys("tzx1w3@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("12ERTyu");
		driver.findElement(By.id("send2")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'My O')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'View O')]")).click();
		driver.findElement(By.tagName("h1")).getText();
		driver.findElement(By.xpath("//a[contains(text(),'Print O')]")).click();
	}

}
