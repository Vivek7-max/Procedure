package com.procedure.chat;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
import com.procedure.objectrepository.UserChatWindowPage;
public class CM012_ValidateChatWindowOpenStateIsMaintainedAfterReopeningTheChat extends BaseClass{
	@Test
	public void testValidateChatWindowOpenStateIsMaintainedAfterReopeningTheChat() throws Throwable {
		//Read Data from files
		String date = eLib.readDataFromExcel("./testdata/testCaseData.xlsx", "Chat", "TC_CM012", "Date");
		String message = eLib.readDataFromExcel("./testdata/testCaseData.xlsx", "Chat", "TC_CM012", "Message");
		String user = fLib.readDataFromPropertyFile("./config/commondata.properties", "user");
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

		//Step 3: Enter user and open chat
		cwp.getSearchTbx().sendKeys(user);
		wLib.waitForInvisibilityOfEle(driver, cwp.getLoader());
		cwp.getFirstChatSuggetion().click();
		wLib.waitForVisibilityOfEle(driver, ucwp.getUserChatIcon(driver, user));
		Assert.assertTrue(ucwp.getUserChatIcon(driver, user).isDisplayed());
		Reporter.log("User chat window is displayed.",true);
		wLib.waitForInvisibilityOfEle(driver, cwp.getLoader());
		wLib.randomClick(driver, 1335, 467);
		System.out.println("Message Date : "+date);
		System.out.println("Message : "+message);
		WebElement oldMsg = ucwp.getOldMessage(driver, date, message);
		wLib.scrollTillElementIsVisible(driver, oldMsg);
		Assert.assertTrue(oldMsg.isDisplayed());
		Reporter.log("Old message is displayed", true);
		
		//Step 4: Press Escape Button
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ESCAPE);
		wLib.waitForVisibilityOfEle(driver, hp.getPatientMasterText());
		Assert.assertTrue(hp.getPatientMasterText().isDisplayed());
		Reporter.log("Patient Master Text is dispalyed after pressing Escape button", true);
		
		//Step 5: Click on chat icon
		wLib.waitForVisibilityOfEle(driver, hp.getChatIcon());
		hp.getChatIcon().click();
		Assert.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat window is displayed after reopening",true);
		wLib.waitForVisibilityOfEle(driver, oldMsg);
		Assert.assertTrue(oldMsg.isDisplayed());
		Reporter.log("Old message is displayed after reopening", true);
		
		//Close the chat window
		cwp.getCloseBtn().click();
	}
}
