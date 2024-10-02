package com.TechPanda;

import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.observer.entity.MediaEntity.MediaEntityBuilder;
//import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.TakesScreenshot;
import io.github.bonigarcia.wdm.WebDriverManager;
public class MobileListPage {
	private WebDriver driver;
	private ExtentReports eReports;
	private ExtentTest test;
	ExtentSparkReporter exSpark;

	@BeforeSuite
	public void setUpAll() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();


		eReports = new ExtentReports();
		exSpark = new ExtentSparkReporter("./ReportGuru/Mobilelist.html");
		eReports.attachReporter(exSpark);
	}



	@Test(testName ="techPanda Page")
	public void verifyMList() throws IOException {


		test = eReports.createTest("Mobile verification");
		test.info("Navigate to techpanda site");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.get("http://live.techpanda.org/index.php/");

		String actualHomeTitle ="THIS IS DEMO SITE";
		String hometitle = driver.getTitle();

		//		Home Title

		if(actualHomeTitle.equals(hometitle)) {
			System.out.println("Verified Home Title");
			test.pass("Home page Title 'THIS IS DEMO SITE' verified ..");
		}else {
			System.out.println("Test failed");
			test.fail("Hompage title is "+ hometitle);
		}

		test.info("Clicking Mobile Section");

		//		Click mobile title
		driver.findElement(By.xpath("//a[.='Mobile']")).click();
		String actualMobtitle = "MOBILE";
		String mobtitle = driver.getTitle();

		if(actualMobtitle.equals(mobtitle)) {
			test.pass("Title is 'MOBILE' ");
		}else {
			System.out.println("Test failed");
			test.fail("Title is "+actualMobtitle);
		}

		//	System.out.println(mobtitle);


		WebElement mobList = driver.findElement(By.xpath("//label[.='Sort By']/following-sibling::select[@title='Sort By']"));
		Select dropdwn = new Select(mobList);
		dropdwn.selectByVisibleText("Name");

//		test.addScreenCaptureFromPath(takeScreenshots()).info("Mobile List with names....");
//		test.log(Status.PASS).fail(MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshots()).build());addScreenCaptureFromPath(takeScreenshots()).build()
		eReports.createTest("Screen shot Test2","In log level").info("yts").fail((Markup) com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshots()));
		
		
		List<WebElement>mobileList =driver.findElements(By.xpath("//*[@class='category-products']/child::ul[@class='products-grid products-grid--max-4-col first last odd']"));
		for (WebElement wbelements : mobileList) {
			System.out.println(wbelements.getText());
			System.out.println("--------------");

			System.out.println(wbelements.getAttribute("title"));
			System.out.println("-----------------");
		}
	}


	@AfterTest
	public void tearDown() {
		//		driver.close();
		eReports.flush();
	}


	public String takeScreenshots() throws IOException {
		TakesScreenshot tc = (TakesScreenshot) driver;
		File file =	tc.getScreenshotAs(OutputType.FILE);
		File fDestination = new File("./ScreenShots/MobilesList.png");
		FileUtils.copyFile(file, fDestination);
		return fDestination.getAbsolutePath();
	}
}
