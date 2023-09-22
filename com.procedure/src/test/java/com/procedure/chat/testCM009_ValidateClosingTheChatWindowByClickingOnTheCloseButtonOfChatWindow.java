package com.procedure.chat;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
public class testCM009_ValidateClosingTheChatWindowByClickingOnTheCloseButtonOfChatWindow extends BaseClass {
	@Test
	public void testValidateClosingTheChatWindowByClickingOnTheCloseButtonOfChatWindow() {
		//Page Instances
		ChatWindowPage cwp = new ChatWindowPage(driver);
		HomePage hp = new HomePage(driver);

		//Step 2: Click on chat icon and verify chat window
		hp.getChatIcon().click();
		Assert.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat Window page is displayed", true);

		//Step 3: Click on close and verify chat window
		cwp.getCloseBtn().click();
		wLib.waitForInvisibilityOfEle(driver, cwp.getChatText());
		Assert.assertTrue(hp.getPatientMasterText().isDisplayed());
		Reporter.log("Patient Master Text is dispalyed after clicking on close button", true);

	}
}
