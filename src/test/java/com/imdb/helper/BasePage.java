package com.imdb.helper;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public abstract class BasePage {

	static List excelData = new ArrayList();

	private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
	protected static final int DEFAULT_WAIT_4_ELEMENT = 30;

	protected long timeout = 60;
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Click action performed and then wait
	 * 
	 * @param element
	 */
	public void waitAndClick(WebElement element) {
		waitForElement(element);
		element.click();
	}

	/**
	 * Click on an element
	 * 
	 * @param element
	 */
	public void clickOn(WebElement element) {
		element.click();
	}

	/**
	 * Click on an element with Action
	 * 
	 * @param element
	 */
	public void actionClick(WebElement element) {
		waitForElement(element);
		Actions action = new Actions(driver);
		action.click(element).build().perform();
	}

	/**
	 * Providing inputs to the given location
	 * 
	 * @param element
	 * @param text
	 */
	public void inputText(WebElement element, String text) {
		waitForElement(element);
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * Wait for an element to present
	 * 
	 * @param element
	 */
	public void waitForElement(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			logger.info(element.toString() + " is not present on page or not clickable");
		}

	}

	/**
	 * Hover over element
	 * 
	 * @param toBeHovered
	 */
	public void hoverOverElement(WebElement toBeHovered) {
		Actions builder = new Actions(driver);
		builder.moveToElement(toBeHovered).build().perform();

	}

	/**
	 * Click on an element after hovering
	 * 
	 * @param toBeHovered
	 * @param toBeClicked
	 * @return
	 */
	public WebDriver hoverOverElementAndClick(WebElement toBeHovered, WebElement toBeClicked) {
		Actions builder = new Actions(driver);
		builder.moveToElement(toBeHovered).build().perform();
		waitForElementPresent(toBeClicked, DEFAULT_WAIT_4_ELEMENT);
		toBeClicked.click();
		waitForPageLoaded();
		return driver;
	}

	/**
	 * Wait for the presence of an element
	 * 
	 * @param webElement
	 * @param timeOutInSeconds
	 * @return
	 */
	public WebElement waitForElementPresent(WebElement webElement, int timeOutInSeconds) {
		WebElement element;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = wait.until(ExpectedConditions.visibilityOf(webElement));
			return element;
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * Wait for page to load
	 */
	public void waitForPageLoaded() {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
		wait.until(expectation);
	}

	/**
	 * Check that element is present
	 * 
	 * @param element
	 * @return
	 */
	public Boolean isElementPresent(WebElement element) {
		try {
			waitForElementVisible(element);
			element.isDisplayed();
			return true;
		} catch (Exception ex) {
		}
		return false;
	}

	/**
	 * Wait for the element to be visible
	 * 
	 * @param element
	 */
	public void waitForElementVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			logger.info(element.toString() + " is not present on page");
		}
	}

	/**
	 * scroll to element
	 * 
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);
	}

	/**
	 * Verify text of the element
	 * 
	 * @param element
	 * @param text
	 */
	public void verifyElementText(WebElement element, String text) {
		// _waitForJStoLoad();
		Assert.assertTrue(isElementPresent(element), element.toString() + " is not present");
		Assert.assertEquals(element.getText(), text);
	}

	/**
	 * Verify text of the window title
	 * 
	 * @param seriesName
	 */
	public void assertWindowTitle(String seriesName) {

		String windowText = driver.getTitle();
		// Assert.assertEquals("Text not found!", windowText.contains(seriesName));
		Assert.assertEquals(true, windowText.contains(seriesName));
	}

}
