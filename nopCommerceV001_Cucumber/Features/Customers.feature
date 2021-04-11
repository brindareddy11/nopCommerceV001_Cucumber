Feature: Customers

# background will execute before every scenario run

Background: Below are the common steps for each scenario 
	Given User Launch Chrome browser 
	When User opens URL "https://admin-demo.nopcommerce.com/login" 
	And User enters Email as "admin@yourstore.com" and Password as "admin" 
	And Click on Login 
	Then User can view Dashboard 
	
@sanity	
Scenario: Add new Customer
	
	When User click on customer menu
	And click on customer menu Item
	And click on add new button
	Then User can view add new cutomer page
	When User enter customer info
	And click on Save button
	Then User can view confirmation message "The new customer has been added successfully."
	And close browser
	
@regression	
Scenario: Search customer by email
	
	When User click on customer menu
	And click on customer menu Item
	And Enter customer email
	When click on search button
	Then user should found email in the search table
	Then close browser

@regression	
Scenario: Search customer by name
	
	When User click on customer menu
	And click on customer menu Item
	And Enter Customer Firstname
	And Enter cusromer LastName
	When click on search button
	Then user should found name in the search table
	Then close browser
	