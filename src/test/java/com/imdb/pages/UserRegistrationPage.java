package com.imdb.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.imdb.helper.BasePage;
import com.imdb.helper.Utilities;;

public class UserRegistrationPage extends BasePage {

	String path = Utilities.getPath();

	public UserRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()='Other Sign in options']")
	private WebElement otherSignInOption;

	@FindBy(xpath = "//a[text()='Create a New Account']")
	private WebElement createANewAccount;

	@FindBy(xpath = "//input[@name='customerName']")
	private WebElement yourNameField;

	@FindBy(xpath = "//input[@type='email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@name='passwordCheck']")
	private WebElement reEnterPasswordField;

	@FindBy(xpath = "//input[@id='continue']")
	private WebElement createAccountLink;

	@FindBy(xpath = "//li[@id='navUserMenu']/p/a")
	private WebElement loggedInUser;

	@FindBy(xpath = "//a[@id='nblogout']")
	private WebElement logOut;

	@FindBy(xpath = "//div[text()='IMDb User Registration']")
	private WebElement registrationEmail;

	@FindBy(xpath = "//input[@placeholder='Check Any Inbox!']")
	private WebElement checkInbox;

	@FindBy(xpath = "//input[@placeholder='Check Any Inbox!']/../span/button")
	private WebElement goButton;

	/**
	 * Click on Other sign in options
	 */
	public void clickOnSignInOption() {
		waitAndClick(otherSignInOption);
	}

	/**
	 * Click on Create New Account
	 */
	public void clickOnCreateNewAccount() {
		waitAndClick(createANewAccount);
	}

	/**
	 * Enter user name
	 */
	public void enterUserName(String userName) {

		inputText(yourNameField, userName);

	}

	/**
	 * Enter email address
	 */
	public void enterEmailAddress(String emailAddress) {
		inputText(emailField, emailAddress);
	}

	/**
	 * Enter valid password
	 */
	public void enterPassword(String password) {
		inputText(passwordField, password);
	}

	/**
	 * Enter same password again
	 */
	public void reEnterPassword(String password) {
		inputText(reEnterPasswordField, password);
	}

	public void clickOnCreateAccount() {
		waitAndClick(createAccountLink);
	}

	/**
	 * Verify logged in user
	 */
	public void verifyLoggedInUser(String userName) {
		String loggedInUserText = loggedInUser.getText();
		Assert.assertEquals(true, loggedInUserText.equals(userName));
	}

	public void logOutFromApplication() {
		waitForPageLoaded();
		hoverOverElement(loggedInUser);
		waitAndClick(logOut);
		waitForPageLoaded();
	}

	/**
	 * Verify sign out state
	 */
	public void verifySignOutState() {
		waitForElementVisible(otherSignInOption);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertEquals(true, bodyText.contains(otherSignInOption.getText()));
	}

	/**
	 * EMail Activation link validation
	 * @param emailForExcel
	 * @throws Exception 
	 */
	public void EmailReaderUtility(String emailForExcel) throws Exception {

		driver.navigate().to(Utilities.readApplicationFile("Mailinator"));
		inputText(checkInbox, emailForExcel);
		waitAndClick(goButton);
		waitForPageLoaded();
		waitAndClick(registrationEmail);

		// Getting all available links
		List<WebElement> linksize = driver.findElements(By.tagName("a"));
		int linksCount = linksize.size();
		System.out.println("Total no of links Available: " + linksCount);
		String[] links = new String[linksCount];

		// Store all links
		for (int i = 0; i < linksCount; i++) {
			links[i] = linksize.get(i).getAttribute("href");
		}

		for (int i = 0; i < linksCount; i++) {
			if (links[i].contains("confirmation")) {
				linksize.get(i).click();
			} else {
				System.out.print("Not a valid link");
			}
		}

	}

}
