package test;

import Utility.Global;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class BestbuyAutomation extends Utility.Global{
	
	public static WebDriver driver;
	
	public String baseURL = "http://www.bestbuy.ca";

	 public String category_DIGITALCAMERA = baseURL+"/en-ca/category/cameras-camcorders/20005";
	 public String driverPath1 = "C:\\\\Users\\\\User\\\\Downloads\\\\chromedriver_win32\\\\chromedriver.exe";
	 public WebElement element;
	 public WebElement element_1stProduct;
	 public WebElement element_addToCart;
	 public WebElement element_Cart;
	 public WebElement element_CheckOut;
	 public WebElement emailTextBox;
	 public WebElement passwordTextBox;
	 public WebElement button_Signin;
	 public WebElement error_msg_text;
	
 @Test
 public void orderProduct(){
	   //Order item added in cart
	   element_Cart=driver.findElement(By.xpath("//span[contains(text(),'View Cart')]"));
	   element_Cart.click();
	   driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
	   System.out.println("\nCart opened");
	   element_CheckOut= driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div[2]/section/section/section[2]/div[2]"));
	   element_CheckOut.click();
	   driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
	   System.out.println("\nCheckout Initiated");
	   emailTextBox=driver.findElement(By.xpath("//input[@id='email']"));
	   emailTextBox.sendKeys(Global.emailID);
	   System.out.println("\nInvalid Test Email ID entered: ");
	   passwordTextBox=driver.findElement(By.xpath("//input[@id='password']"));
	   passwordTextBox.sendKeys(Global.password);
	   System.out.println("\nInvalid password entered: ");
	   button_Signin= driver.findElement(By.xpath("//span[contains(text(),'Sign In')]"));
	   button_Signin.click();
	   error_msg_text=driver.findElement(By.xpath("//div[@class='_2YH6O']"));
	   System.out.println("Displayed error message: "+error_msg_text.getText());
	   String error_msg_actual= error_msg_text.getText().trim();
	   
	   
	   if (error_msg_actual == Global.error_msg_reference) {
		   System.out.println("Error message verified succesfully");
	   }
	   else
		   System.out.println("Actual error message do not match with reference");
	   
	  }
 
 @BeforeMethod
 public void addProductToCart() {
	  //search and add First item under Top Selling Products
	    driver.navigate().to(category_DIGITALCAMERA);
	    driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
	    System.out.println("\nLanded in camera category titled: "+driver.getTitle());
	    //driver.findElement(By.linkText("Mirrorless Cameras")).click();
	    driver.findElement(By.xpath("//div[@class='container_1-PFH row_1Rbqw']/div[4]/a/div"));
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    System.out.println("\nLanded in camera category titled: "+driver.getTitle());
	    element= driver.findElement(By.xpath("//p[contains(text(),\"Mirrorless cameras.\")]"));
	    //element= driver.findElement(By.xpath("//div[@class='slick-slide slick-active slick-current']"));
	    element.click();
	    driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	    System.out.println("\nLanded in Mirrorless camera category: ");
	    element_1stProduct= driver.findElement(By.xpath("//div[@data-index='0']/div/li/div/a/div/div[@class='productItemRow_hyNOs row_1Rbqw']"));
	    element_1stProduct.click();
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    System.out.println("\nFirst item under Top Selling Products is: "+driver.getTitle());
	    element_addToCart= driver.findElement(By.xpath("//button[@class='button_2Xgu4 primary_oeAKs addToCartButton_1DQ8z addToCartButton regular_cDhX6']"));
	    element_addToCart.click();
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    System.out.println("\nItem added in Cart ");
	    
	   }

 @AfterMethod
 public void afterMethod() {
 }
 
 

 @BeforeTest
 public void openBrowser() {
	 System.setProperty("webdriver.chrome.driver", Global.driverPath);
	 driver= new ChromeDriver();
	 driver.get(Global.baseURL);
	 System.out.println("Landed in Homepage titled: "+driver.getTitle());
	 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
	 driver.manage().window().maximize();
	 }
 

	 

 @AfterTest
 public void afterTest() {
	 driver.quit();
 }

}
