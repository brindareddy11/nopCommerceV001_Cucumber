package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchPage;

public class Steps extends BaseClass {
	public WebDriver driver;
	public LoginPage lp;
	// cucumber annotations methods ex: @Before , @Given and so on.... can't be extends to another class---thats the reason setup method is not defined in base class
	@Before  //its hook concept from cucumber---this method execute one time before test methods start run-- all the configration will be loaded and then rest of the methods run as per the config.
	public void setup() throws IOException
	{
		logger=Logger.getLogger("nopComemrce"); // added logger--name of the project
		PropertyConfigurator.configure("Log4j.properties"); // added logger
		
		// reading properties
		configProp=new Properties();
		//load properties file
		FileInputStream configPropfile=new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
		
		String br=configProp.getProperty("browser");
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver =new ChromeDriver();
		}
		
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver =new FirefoxDriver();
		}
		else if (br.equals("ie"))
		{
			System.setProperty("webdriver.edge.driver", configProp.getProperty("iepath"));
			driver =new EdgeDriver();
		}
		
		
		
		logger.info("****Launching browser******");
		
	}
	
	
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		
		lp=new LoginPage(driver);
		
		
		
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		
		driver.get(url);
		driver.manage().window().maximize();
	    
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String pwd) {
		logger.info("Providing email and password");
		lp.setEmail(email);
	   lp.setPassword(pwd);
	}

	@When("Click on Login")
	public void click_on_login() {
	    lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void Page_title_should_be(String title) {
	   if(driver.getPageSource().contains("Login was unsuccessful."))
	   {
		   logger.info("Login Failed------");
		   driver.close();
		   Assert.assertTrue(false);
	   }
	   else
	   {
		   logger.info("Login Passed------");
		   Assert.assertEquals(title, driver.getTitle());
	   }
		
		
		
		
	}

	@When("User click on log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
		logger.info("Click on Logout------");
		lp.clickLogout();
	    Thread.sleep(3000);
	}


	@Then("close browser")
	public void close_browser() {
	    driver.quit();
	}
	
	// Customer add page
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() throws InterruptedException {
		addCust=new AddCustomerPage(driver);
	
	 Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());   
	}

	@When("User click on customer menu")
	public void user_click_on_customer_menu() throws InterruptedException {
		Thread.sleep(5000);
		addCust.clickOnCustomersMenu();
	   
	}

	@When("click on customer menu Item")
	public void click_on_customer_menu_item() throws InterruptedException {
		Thread.sleep(5000);
		addCust.clickOnCustomersMenuItem();
	    
	}

	@When("click on add new button")
	public void click_on_add_new_button() {
	    addCust.clickOnAddnew();
	}

	@Then("User can view add new cutomer page")
	public void user_can_view_add_new_cutomer_page() {
		logger.info("Adding new customer------");
		 Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());  
	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
		
		logger.info("providing new customer details------");
	    String email = randomstring()+"@gmail.com";
		addCust.setEmail(email);
		addCust.setGender("Male");
		addCust.setFirstName("Sudhak");
		addCust.setLastName("redd");
		addCust.setDob("7/05/1985");
		addCust.setCompanyName("busyQA");
		addCust.setAdminContent("This is for Testing");
		
		
		
	}

	@When("click on Save button")
	public void click_on_save_button() {
		
		logger.info("saving new customer------");
	   addCust.clickOnSave();
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		
		String name=driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissable']")).getText();
		System.out.println("sudhakar text"+name+ "Sudhakar text");
		 
		if(name.contains(msg))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
		//Assert.assertEquals("msg",name );   
	   
	}
	// steps for searching a customer using email
	
	@When("Enter customer email")
	public void enter_customer_email() {
	    sp=new SearchPage(driver);
	    logger.info("Searching new customer by email------");
	    sp.setEmail("victoria_victoria@nopCommerce.com");
	    
	}
	@When("click on search button")
	public void click_on_search_button() throws InterruptedException {
	    sp.clickSearch();
	    Thread.sleep(2000);
	}
	@Then("user should found email in the search table")
	public void user_should_found_email_in_the_search_table() {
	    boolean status=sp.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		
		Assert.assertTrue(status);
		
	}
	
	// steps for searching a customer using name
	
	@When("Enter Customer Firstname")
	public void enter_customer_firstname() throws InterruptedException {
		sp=new SearchPage(driver);
		sp.setFirstName("Brenda");
	}
	@When("Enter cusromer LastName")
	public void enter_cusromer_last_name() {
	    sp.setLastName("Lindgren");
	}
	@Then("user should found name in the search table")
	public void user_should_found_name_in_the_search_table() {
	    boolean status =sp.searchCustomerByName("Brenda Lindgren");
	    Assert.assertTrue(status);
	}



	

}
