package com.imdb.testscripts;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.imdb.helper.BaseTest;

public class BasicNavigation extends BaseTest{
	String seriesName="The Walking Dead";
	String seriesUniqueIdentity="Zombie";


	@Test
	public void navigationToDesiredSeries() {
		
		Reporter.log("Move cursor to Movie TV and ShowTime option available");
		homePage.moveToMovieTVMenu();
		
		Reporter.log("Click on Most Popular TV Show");
		homePage.clickOnMostPopularTVShow();
		
		Reporter.log("Click on desired series name");
		homePage.clickOnRequredSeries(seriesName);
		
		Reporter.log("Assert window Title to check user is on The Walking Dead page");
		homePage.assertWindowTitle(seriesName);
		
		Reporter.log("Assert that page containd series title");
		homePage.assertPageBodyForSriesName(seriesName);
		
		Reporter.log("Assert that page containd unique identity that is zombie in body");
		homePage.assertPageBodyForSriesName(seriesUniqueIdentity);
	}

}
