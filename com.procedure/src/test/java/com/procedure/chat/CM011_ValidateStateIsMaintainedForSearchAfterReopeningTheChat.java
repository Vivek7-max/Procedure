package com.procedure.chat;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
public class CM011_ValidateStateIsMaintainedForSearchAfterReopeningTheChat extends BaseClass {
	@Test
	public void testValidateStateIsMaintainedForSearchAfterReopeningTheChat() throws Throwable {
		String user = fLib.readDataFromPropertyFile("./config/commondata.properties", "user");

		//Page Instances
		ChatWindowPage cwp = new ChatWindowPage(driver);
		HomePage hp = new HomePage(driver);

		//Step 2: Click on chat icon and verify chat window
		hp.getChatIcon().click();
		wLib.waitForVisibilityOfEle(driver, cwp.getChatText());
		Assert.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat Window page is displayed", true);

		//Step 3: Enter Search keywords and verify suggestions
		cwp.getSearchTbx().sendKeys(user);
		wLib.waitForInvisibilityOfEle(driver, cwp.getLoader());
		Assert.assertTrue(cwp.getSearchResultSuggetion().isDisplayed());
		Reporter.log("Search result suggetion is displayed",true);
		List<WebElement> allSuggetions = cwp.getAllSuggestions(driver);
		int sizeBeforeRepoen = allSuggetions.size();

		//Step 4: Click on close button and verify chat window
		cwp.getCloseBtn().click();
		wLib.waitForInvisibilityOfEle(driver, cwp.getChatText());
		Assert.assertTrue(hp.getPatientMasterText().isDisplayed());
		Reporter.log("Chat window is closed and Patient Master Text is dispalyed", true);
		
		//Step 5: click on chat icon and verify suggestions
		hp.getChatIcon().click();
		wLib.waitForVisibilityOfEle(driver, cwp.getChatText());
		Assert.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat Window page is displayed after reopen", true);
		List<WebElement> allSuggetions1 = cwp.getAllSuggestions(driver);
		int sizeAfterRepoen = allSuggetions1.size();
		
		Assert.assertTrue(sizeBeforeRepoen==sizeAfterRepoen);
		Reporter.log("Chat results are displayed as it is after reopening", true);
		
		cwp.getCloseBtn().click();
	}
}
