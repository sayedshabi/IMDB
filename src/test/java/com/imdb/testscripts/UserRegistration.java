package com.imdb.testscripts;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.imdb.helper.BaseTest;
import com.imdb.helper.Utilities;

public class UserRegistration extends BaseTest{
	
	String userName = Utilities.generateRandomString(10);
	String mailinator = "@mailinator.com";
	String emailAddress = userName + mailinator;
	String emailForExcel = userName;
	String password = Utilities.generateRandomString(10);
	
	@Test
	public void registerUser() {
		
		Reporter.log("Click on Other sign in options available");
		registrationPage.clickOnSignInOption();
		
		Reporter.log("Click on Create new account");
		registrationPage.clickOnCreateNewAccount();
		
		Reporter.log("Enter user name");
		registrationPage.enterUserName(userName);
		
		Reporter.log("Enter email address");
		registrationPage.enterEmailAddress(emailAddress);
		
		Reporter.log("Enter Password");
		registrationPage.enterPassword(password);
		
		Reporter.log("Enter same password again");
		registrationPage.reEnterPassword(password);
		
		Reporter.log("Click on create an account link");
		registrationPage.clickOnCreateAccount();
		
		Reporter.log("Verify logged in User");
		registrationPage.verifyLoggedInUser(userName);
				
		Reporter.log("Log out from the application");
		registrationPage.logOutFromApplication();
	
		Reporter.log("Verify signout state");
		registrationPage.verifySignOutState();
		
	}
	
}
