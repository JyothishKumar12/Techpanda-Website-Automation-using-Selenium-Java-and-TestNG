package com.TechPanda;
//import static org.junit.Assert.*;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class Testing {




	  private WebDriver driver;
	  private String baseUrl;
	  public String firstName = "BERRY";    // These testdata stuff will be placed    
	  public String lastName = "BERRYSEVEN";  // in a TestData EXCEL file at some stage
	  public String vEmail = "Berry.Berrysix@tpg.com.au";
	  public String vPW = "123456";
	  
	@BeforeTest
	public void setUp() throws Exception {
//		System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    baseUrl = "http://live.techpanda.org/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  
	  @Test
	  public void testTestCase6() throws Exception {
		
		// 1. Go to http://live.techpanda.org                                         
	    driver.get(baseUrl); 
	    
	    
	    // 2. Click on my account link                                             
	    driver.findElement(By.linkText("MY ACCOUNT")).click();
	    
	    Thread.sleep(3000);  
	    
	    // switching to new window                                                    
	    for (String handle : driver.getWindowHandles()) {
	    	driver.switchTo().window(handle);
	    	}
	    
	    // 3. Login in application using previously created credential                  
	    driver.findElement(By.id("email")).clear();	   
	    driver.findElement(By.id("email")).sendKeys(vEmail); 
	    driver.findElement(By.id("pass")).clear();	    
	    driver.findElement(By.id("pass")).sendKeys(vPW);
	    driver.findElement(By.id("send2")).click();	 // this is the Login button   
	   
	    Thread.sleep(3000);  
	    
	 // switching to new window                                                                               
	    for (String handle : driver.getWindowHandles()) {
	    	driver.switchTo().window(handle);
	    	}
	    
	    // 4. Click on MY WISHLIST link                                                                              
	    driver.findElement(By.linkText("MY WISHLIST")).click();
	
	    Thread.sleep(3000);    
	    
	    // switching to new window                                                                             
	    for (String handle : driver.getWindowHandles()) {
	    	driver.switchTo().window(handle);
	    	}
	    
	    // 5. In next page, Click ADD TO CART link                                                                     
	    driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
	 
	    Thread.sleep(3000);   
	    
	    // switching to new window                                                                                  
	    for (String handle : driver.getWindowHandles()) {                                             
	    	driver.switchTo().window(handle);
	    	} 
	    
	    // 6. Enter general shipping country, state/province and zip for the shipping cost estimate                
	    new Select(driver.findElement(By.xpath("//select[@id='country']"))).selectByIndex(14);
	    driver.findElement(By.id("region")).sendKeys("New South Wales");
	    driver.findElement(By.id("postcode")).sendKeys("2000");
	    
	    // 7. Click Estimate                                                                                                       
	    driver.findElement(By.xpath(".//*[@id='shipping-zip-form']/div/button")).click(); // this is the ESTIMATE link
	    
	    
	    // 8. Verify Shipping cost generated                                                                               
	    String sFlatRate = "Flat Rate";
	    String flatRate = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dt")).getText();	
	    try {
	    	System.out.println("sFlatRate = "+sFlatRate);
	    	System.out.println("flatRate = "+flatRate);
	    	assertEquals(sFlatRate, flatRate);
		    } catch (Exception e) {
		    	e.printStackTrace();	    	
		    }	
	    	    
	    String sFlatRatePrice = "Fixed - $5.00";
	    String flatRatePrice = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dd/ul/li/label")).getText();
	    try {	 
	    	System.out.println("sFlatRatePrice = "+sFlatRatePrice);
	    	System.out.println("flatRatePrice = "+flatRatePrice);
	    	assertEquals(sFlatRatePrice, flatRatePrice);
		    } catch (Exception e) {
		    	e.printStackTrace();	    	
		    }	
	    
	    
	    // 9. Select Shipping Cost (already selected as default), Update Total                                              
	    driver.findElement(By.id("s_method_flatrate_flatrate")).click();	 // RADIO button  -  Fixed - $5.00
	    driver.findElement(By.xpath("//button[@title='Update Total']")).click();
	    
	    
	    
	    // 10. Verify shipping cost is added to total                                                           
	    String vFlatRatePrice = "$5.00";
	    String shippingCostIncluded = driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")).getText();
	    
	    try {
	    	System.out.println("vFlatRatePrice = "+vFlatRatePrice);
	    	System.out.println("shippingCostIncluded = "+shippingCostIncluded);
	    	assertEquals(vFlatRatePrice, shippingCostIncluded);
		    } catch (Exception e) {
		    	e.printStackTrace();	    	
		    }
	   
	    
	    // 11. Click PROCEED TO CHECKOUT                                                                       
	    driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click();
	   
	    Thread.sleep(3000);    
	    
	    
	    // switching to new window                                                                                
	    for (String handle : driver.getWindowHandles()) { 
	    	driver.switchTo().window(handle);
	    	}  
	    Thread.sleep(2000); 
}
}
