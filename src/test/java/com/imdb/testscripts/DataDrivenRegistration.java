package com.imdb.testscripts;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.imdb.helper.BaseTest;
import com.imdb.helper.Utilities;
import com.imdb.helper.ExcelUtils;

public class DataDrivenRegistration extends BaseTest {

	String userName = Utilities.generateRandomString(10);
	String emailAddress = userName;
	String emailForExcel = userName;
	String password = Utilities.generateRandomString(10);
	String path = Utilities.getPath();
	String excelPath = "/resources/TestData/DataFile.xlsx";

	@Test
	public void userRegistrationWithExcelData() throws Exception {

		Reporter.log("Click on Other sign in options available");
		registrationPage.clickOnSignInOption();

		Reporter.log("Click on Create new account");
		registrationPage.clickOnCreateNewAccount();

		Reporter.log("Enter user name");
		String excelUserName = ExcelUtils.getDataFromExcelSheet(path + excelPath, "ImdbUserData", 1, 0);
		userName = userName + excelUserName;
		registrationPage.enterUserName(userName);

		Reporter.log("Enter email address");
		String excelEmailAddress = ExcelUtils.getDataFromExcelSheet(path + excelPath, "ImdbUserData", 1, 1);
		emailAddress = emailAddress + excelEmailAddress;
		registrationPage.enterEmailAddress(emailAddress);

		Reporter.log("Enter Password");
		String excelPassword = ExcelUtils.getDataFromExcelSheet(path + excelPath, "ImdbUserData", 1, 2);
		password = password + excelPassword;
		registrationPage.enterPassword(password);

		Reporter.log("Enter same password again");
		registrationPage.reEnterPassword(password);

		Reporter.log("Click on create an account link");
		registrationPage.clickOnCreateAccount();

		Reporter.log("Verify logged in User");
		registrationPage.verifyLoggedInUser(userName);

		Reporter.log("verify link provided in email");
		registrationPage.EmailReaderUtility(emailAddress);

		Reporter.log("Log out from the application");
		registrationPage.logOutFromApplication();

		Reporter.log("Verify signout state");
		registrationPage.verifySignOutState();

	}
}
