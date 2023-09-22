package com.procedure.chat;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
import com.procedure.objectrepository.UserChatWindowPage;
public class testCM013_ValidateDraftMessageIsMaintainedAfterReopeningTheChat extends BaseClass {
	@Test
	public void testValidateDraftMessageIsMaintainedAfterReopeningTheChat() throws Throwable {
		//Read Data from files
		String user = fLib.readDataFromPropertyFile("./config/commondata.properties", "user");
		String message = fLib.readDataFromPropertyFile("./config/commondata.properties", "message");
		String date = eLib.readDataFromExcel("./testdata/testCaseData.xlsx", "Chat", "TC_CM012", "Date");
		String testMessage = eLib.readDataFromExcel("./testdata/testCaseData.xlsx", "Chat", "TC_CM012", "Message");
		
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
		System.out.println("Date: "+date);
		System.out.println("testMessage : "+testMessage);
		WebElement oldMsg = ucwp.getOldMessage(driver, date, testMessage);
		wLib.scrollTillElementIsVisible(driver, oldMsg);
		Assert.assertTrue(oldMsg.isDisplayed());
		Reporter.log("Old message is displayed", true);

		//Step 4: Type message into message text field
		ucwp.getMessageTextfield().sendKeys(message);
		String messageTyped = ucwp.getMessageTextfield().getText();
		Assert.assertTrue(messageTyped.equals(message));
		Reporter.log("Message is displayed after typing", true);

		//Step 5 : Click escape button from keyboard
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ESCAPE);
		wLib.waitForVisibilityOfEle(driver, hp.getPatientMasterText());
		Assert.assertTrue(hp.getPatientMasterText().isDisplayed());
		Reporter.log("Patient Master Text is dispalyed after pressing Escape button", true);

		//Step 6: Click on chat icon
		wLib.waitForVisibilityOfEle(driver, hp.getChatIcon());
		hp.getChatIcon().click();
		Assert.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat window is displayed after reopening",true);
		wLib.waitForVisibilityOfEle(driver, oldMsg);
		Assert.assertTrue(oldMsg.isDisplayed());
		Reporter.log("Old message is displayed after reopening", true);
		String messageAfterReopen = ucwp.getMessageTextfield().getText();
		Assert.assertTrue(messageAfterReopen.equals(messageTyped));
		Reporter.log("Same typed message is displayed after reopening", true);
		
		//Close the chat window
		cwp.getCloseBtn().click();
		
	}
}
