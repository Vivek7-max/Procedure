package com.procedure.chat;

import org.junit.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
import com.procedure.objectrepository.UserChatWindowPage;

public class CM004_ValidateNotificationBadgeWhenThereAreNoUnreadMessages extends BaseClass {
	@Test(groups = "regressionTest")
	public void testValidateNotificationBadgeWhenThereAreNoUnreadMessages() throws Throwable {
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

		//Page Instances
		ChatWindowPage cwp = new ChatWindowPage(driver);
		UserChatWindowPage ucwp = new UserChatWindowPage(driver);
		
		//Step 6: login as second user and verify badge chat icon
		loginWithCredentials(username2, password2);
		wLib.waitForVisibilityOfEle(driver, hp.getChatIconWithBadge());
	
		Assert.assertTrue(hp.getChatIconWithBadge().isDisplayed());
		Reporter.log("Chat icon is displayed with badge ", true);
		
		//Step 7: Click on chat icon and verify unread message count
		hp.getChatIconWithBadge().click();
		wLib.waitForVisibilityOfEle(driver, cwp.getUnreadMessageCount(driver, user2));
		Assert.assertTrue(cwp.getUnreadMessageCount(driver, user2).isDisplayed());
		Reporter.log("Unread message count is displayed", true);
		
		String unreadMsgCount = cwp.getUnreadMessageCount(driver, user2).getText();
		System.out.println("unreadMsgCount: "+unreadMsgCount);
		
		//Step 8: Open user chat verify badge chat icon
		cwp.getUnreadMessageCount(driver, user2).click();
		wLib.waitForVisibilityOfEle(driver, ucwp.getUserChatIcon(driver, user2));
		Assert.assertTrue(ucwp.getUserChatIcon(driver, user2).isDisplayed());
		Reporter.log("User chat window is displayed", true);
		cwp.getCloseBtn().click();
		boolean check = true;
		try {
			hp.getBadge().isDisplayed();
		} catch (Exception e) {
			check = false;
		}
		
		Assert.assertFalse(check);
		Reporter.log("Badge is not visible for chat icon after viewing message", true);

	}
}
