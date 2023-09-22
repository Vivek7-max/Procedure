package com.procedure.chat;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;

public class CM010_ValidateClosingTheChatWindowByClickingOnTheEscapeButtonFromKeyboard extends BaseClass {
	@Test
	public void testValidateClosingTheChatWindowByClickingOnTheEscapeButtonFromKeyboard() throws AWTException, Throwable {
		//Page Instances
		ChatWindowPage cwp = new ChatWindowPage(driver);
		HomePage hp = new HomePage(driver);

		//Step 2: Click on chat icon and verify chat window
		hp.getChatIcon().click();
		wLib.waitForVisibilityOfEle(driver, cwp.getChatText());
		Assert.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat Window page is displayed", true);

		//Step 3: Press Escape button and verify chat window
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ESCAPE);
		wLib.waitForInvisibilityOfEle(driver, cwp.getChatText());
		
		Assert.assertTrue(hp.getPatientMasterText().isDisplayed());
		Reporter.log("Patient Master Text is dispalyed after pressing Escape button", true);

	}
}
