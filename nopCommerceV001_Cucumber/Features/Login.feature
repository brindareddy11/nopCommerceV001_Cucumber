Feature: Login
Background: Below are the common steps for each scenario 
	Given User Launch Chrome browser 
	When User opens URL "https://admin-demo.nopcommerce.com/login" 
	
@sanity	
Scenario: Successful login with Valid Credentials
	And User enters Email as "admin@yourstore.com" and Password as "admin" 
	And Click on Login 
	Then Page Title should be "Dashboard / nopCommerce administration"
	When User click on log out link
	Then Page Title should be "Your store. Login"
	And close browser
@regression	
Scenario Outline: login Data Driven
	And User enters Email as "<email>" and Password as "<password>" 
	And Click on Login 
	Then Page Title should be "Dashboard / nopCommerce administration"
	When User click on log out link
	Then Page Title should be "Your store. Login"
	And close browser
	
	Examples:
	| email	|	password	|
	|	admin@yourstore.com	|	admin	|
	|	admin@yourstore.com	|	admin	|
	