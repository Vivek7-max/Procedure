package com.procedure.chat;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
import com.procedure.objectrepository.UserChatWindowPage;

@Listeners(com.procedure.genericutility.ListenerImplementation.class)
public class CM001_testMessageIsReceivedAsSentForSenderPointOfView extends BaseClass{	
	@Test(groups = "smokeTest")
	public void testMessageIsReceivedAsSentForSenderPointOfView() throws Throwable {
		//Read Data from property file
		String message = fLib.readDataFromPropertyFile("./config/commondata.properties", "message");
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
		
		//Step 3: Enter user and verify search results are displayed
		cwp.getSearchTbx().sendKeys(user);
		wLib.waitForInvisibilityOfEle(driver, cwp.getLoader());
		Assert.assertTrue(cwp.getSearchResultSuggetion().isDisplayed());
		Reporter.log("Search result suggetion is displayed",true);

		
		//Step 4: Click on user and verify user chat window is displayed
		cwp.getFirstChatSuggetion().click();
		wLib.waitForVisibilityOfEle(driver, ucwp.getUserChatIcon(driver, user));
		Assert.assertTrue(ucwp.getUserChatIcon(driver, user).isDisplayed());
		Reporter.log("User chat window is displayed.",true);
		
		//Step 5: Send message to user and verify the message
		ucwp.getMessageTextfield().sendKeys(message);
		ucwp.getSendButton().click();
		wLib.waitForVisibilityOfEle(driver, ucwp.getTodaysMessage(driver, message));
		Assert.assertTrue(ucwp.getTodaysMessage(driver, message).isDisplayed());
		Reporter.log("Today's Sent Message is displayed",true);
		String sentMessage = ucwp.getTodaysMessage(driver, message).getText();
		Reporter.log("sentMessage: "+sentMessage, true);
		String sentMessageTime = ucwp.getMessageTime(driver, sentMessage).getText();
		Reporter.log("sentMessageTime: "+sentMessageTime,true);
		Reporter.log("System Time: "+jLib.getSystemTime(),true);

		cwp.getCloseBtn().click();
	}
}
