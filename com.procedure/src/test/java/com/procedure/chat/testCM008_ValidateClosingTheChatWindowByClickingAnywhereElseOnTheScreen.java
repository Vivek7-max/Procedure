package com.procedure.chat;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
public class testCM008_ValidateClosingTheChatWindowByClickingAnywhereElseOnTheScreen extends BaseClass {
	@Test
	public void testValidateClosingTheChatWindowByClickingAnywhereElseOnTheScreen() {
		//Page Instances
		ChatWindowPage cwp = new ChatWindowPage(driver);
		HomePage hp = new HomePage(driver);
		
		//Step 2: Click on chat icon and verify chat window
		hp.getChatIcon().click();
		wLib.waitForVisibilityOfEle(driver, cwp.getChatText());
		Assert.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat Window page is displayed", true);
		
		//Step 3: Click anywhere on the screen except chat window and verify chat window
		wLib.randomClick(driver, 0, 0);
		wLib.waitForInvisibilityOfEle(driver, cwp.getChatText());
		Assert.assertTrue(hp.getPatientMasterText().isDisplayed());
		Reporter.log("Patient Master Text is dispalyed after click", true);
		
	}
}
