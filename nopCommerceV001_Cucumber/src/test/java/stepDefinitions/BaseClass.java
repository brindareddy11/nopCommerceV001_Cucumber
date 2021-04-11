package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchPage;

public class BaseClass {
	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public SearchPage sp;
	public static Logger logger;
	public Properties configProp;

// generate unique data for email id 
public static String randomstring() {
	
	String generatedString1 = RandomStringUtils.randomAlphabetic(5);
	return (generatedString1);
}






}
