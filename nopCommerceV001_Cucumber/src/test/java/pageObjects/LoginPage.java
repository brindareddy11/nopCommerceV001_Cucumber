package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;

	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
		@FindBy(xpath="//input[@id='Email']")
		@CacheLookup
		WebElement txtEmailAddress;
		
		@FindBy(xpath="//input[@id='Password']")
		@CacheLookup
		WebElement txtPassword;
		
		@FindBy(xpath="//button[normalize-space()='Log in']")
		WebElement btnLogin;
		
		@FindBy(xpath="//a[normalize-space()='Logout']")
		WebElement btnLogout;
		
		
		@FindBy(xpath="//h2[text()='My Account']")
		WebElement msgHeading;
		
	
		
		public void setEmail(String email)
		{
			txtEmailAddress.clear();
			txtEmailAddress.sendKeys(email);
		}
		public void setPassword(String pwd)
		{
			txtPassword.clear();
			txtPassword.sendKeys(pwd);
		}
		
		public void clickLogin()
		{
			btnLogin.click();
		}
		
		public void clickLogout()
		{
			btnLogout.click();
		}
		
		

	}
