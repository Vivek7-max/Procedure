package com.procedure.chat;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
import com.procedure.objectrepository.UserChatWindowPage;
public class CM006_ValidateSearchWhenNoResultsAreDisplayed extends BaseClass {
	@Test(priority = -1)
	public void testValidateSearchWhenNoResultsAreDisplayed() throws Throwable {
		//Read the data from property file
		String randomString = RandomStringUtils.randomAlphabetic(15)+" ";
		wLib.waitForElementEmplicitly(driver);

		//Page Instances
		HomePage hp = new HomePage(driver);
		ChatWindowPage cwp = new ChatWindowPage(driver);
		UserChatWindowPage ucwp = new UserChatWindowPage(driver);

		//Step 2: click on chat icon and verify chat window is displayed
		hp.getChatIcon().click();
		wLib.waitForVisibilityOfEle(driver, cwp.getChatText());
		Assert.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat window is displayed.",true);
		
		//Step 3: Enter Random String into search textbox
		cwp.getSearchTbx().sendKeys(randomString);
		wLib.waitForInvisibilityOfEle(driver, cwp.getLoader());
		wLib.waitForVisibilityOfEle(driver, cwp.getNoDataText());
		Assert.assertTrue(cwp.getNoDataText().isDisplayed());
		Reporter.log("No data text is displayed", true);
		
		cwp.getCloseBtn().click();
		
		

	}
	@Test
	public void test() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("Test method for regional regression testing");
	}
}
