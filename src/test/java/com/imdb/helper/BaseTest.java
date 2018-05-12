package com.imdb.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.imdb.pages.HomePage;
import com.imdb.pages.UserRegistrationPage;

public abstract class BaseTest {

	protected HomePage homePage;
	protected UserRegistrationPage registrationPage;

	private WebDriver driver;

	@BeforeTest
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", Utilities.getPath() + "/resources/browsers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to(Utilities.readApplicationFile("URL"));
		driver.manage().window().maximize();
		homePage = PageFactory.initElements(driver, HomePage.class);
		registrationPage = PageFactory.initElements(driver, UserRegistrationPage.class);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
