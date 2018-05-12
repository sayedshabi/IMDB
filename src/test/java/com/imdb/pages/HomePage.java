package com.imdb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.imdb.helper.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//li[@id='navTitleMenu']")
	private WebElement movieTVMenu;

	@FindBy(xpath = "//a[text()='Most Popular TV Shows']")
	private WebElement mostPopularTV;

	/**
	 * Click on Movie TV & ShowTime main menu item
	 */
	public void moveToMovieTVMenu() {
		hoverOverElement(movieTVMenu);
	}

	/**
	 * Click on Most Popular show
	 */
	public void clickOnMostPopularTVShow() {
		waitAndClick(mostPopularTV);
	}

	public void clickOnRequredSeries(String seriesName) {

		waitForPageLoaded();
		WebElement seriesLocation = driver.findElement(By.xpath("//a[text()='" + seriesName + "']"));
		scrollToElement(seriesLocation);
		hoverOverElementAndClick(seriesLocation, seriesLocation);

	}
	
	public void assertPageBodyForSriesName(String seriesName) {
		
		String bodyText=driver.findElement(By.tagName("body")).getText();
		Assert.assertEquals(true, bodyText.contains(seriesName));
		
	}

}
