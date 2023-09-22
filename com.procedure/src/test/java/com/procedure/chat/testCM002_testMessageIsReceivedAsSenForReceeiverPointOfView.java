package com.procedure.chat;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.procedure.genericutility.BaseClass;
import com.procedure.genericutility.FileUtility;
import com.procedure.genericutility.JavaUtility;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
import com.procedure.objectrepository.UserChatWindowPage;
@Listeners(com.procedure.genericutility.ListenerImplementation.class)
public class testCM002_testMessageIsReceivedAsSenForReceeiverPointOfView extends BaseClass {
	@Test(groups = "smokeTest")
	public void testMessageIsReceivedAsSenForReceeiverPointOfView() throws Throwable {
		//Read the data from property file
		String username2 = fLib.readDataFromPropertyFile("./config/commondata.properties","username2");
		String user = fLib.readDataFromPropertyFile("./config/commondata.properties","user");
		String user2 = fLib.readDataFromPropertyFile("./config/commondata.properties","user2");
		String password2 = fLib.readDataFromPropertyFile("./config/commondata.properties","password2");
		String message = fLib.readDataFromPropertyFile("./config/commondata.properties", "message");

		//Step 2, 3, 4, 5: send message to one user and logout
		sendMessageAndVerify(user, message);
		HomePage hp = new HomePage(driver);
		wLib.waitForVisibilityOfEle(driver, hp.getLogoutIcon());
		logout();
		
		//Step 6: login as second user
		loginWithCredentials(username2, password2);

		//Page Instances
		ChatWindowPage cwp = new ChatWindowPage(driver);
		UserChatWindowPage ucwp = new UserChatWindowPage(driver);

		//Step 7: Click on chat icon and verify chat window is displayed
		hp.getChatIcon().click();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat window is displayed.", true);
		sa.assertAll();
		
		//Step 8: Open chat widow of fist user and verify
		cwp.getSearchTbx().sendKeys(user2);
		wLib.waitForInvisibilityOfEle(driver, cwp.getLoader());
		cwp.getFirstChatSuggetion().click();
		wLib.waitForVisibilityOfEle(driver, ucwp.getTodaysMessage(driver, message));
		Assert.assertTrue(ucwp.getTodaysMessage(driver, message).isDisplayed());
		Reporter.log("Today's Received Message is displayed",true);
		String receivedMessage = ucwp.getTodaysMessage(driver, message).getText();
		Reporter.log("receivedMessage: "+receivedMessage);
		String receivedMessageTime = ucwp.getMessageTime(driver, receivedMessage).getText();
		Reporter.log("receivedMessageTime: "+receivedMessageTime,true);
		cwp.getCloseBtn().click();
		Reporter.log("System Time: "+jLib.getSystemTime(),true);
	}

}
