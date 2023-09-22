package com.procedure.chat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
import com.procedure.objectrepository.UserChatWindowPage;
@Listeners(com.procedure.genericutility.ListenerImplementation.class)
public class FailToCheckScreenshot extends BaseClass {
	@Test
	public void testFailToCheckScreenshot() throws Throwable {
		//Read Data from property file
		String message = fLib.readDataFromPropertyFile("./config/commondata.properties", "message");
		String user = fLib.readDataFromPropertyFile("./config/commondata.properties", "user");
		wLib.waitForElementEmplicitly(driver);

		//Page Instances
		HomePage hp = new HomePage(driver);
		ChatWindowPage cwp = new ChatWindowPage(driver);
		UserChatWindowPage ucwp = new UserChatWindowPage(driver);

		//Step 2: click on chat icon and verify chat window is displayed
		hp.getChatIcon().sendKeys("Hi");
		wLib.waitForVisibilityOfEle(driver, cwp.getChatText());
		Assert.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat window is displayed.",true);
		
		
	}
}
