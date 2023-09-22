package com.procedure.chat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.HomePage;
@Listeners(com.procedure.genericutility.ListenerImplementation.class)
public class testCM003_ValidateNotificationBadgeForUnreadMessages extends BaseClass {
	@Test(groups = "regressionTest")
	public void testValidateNotificationBadgeForUnreadMessages() throws Throwable {
		//Read the data from property file
		String username2 = fLib.readDataFromPropertyFile("./config/commondata.properties","username2");
		String user = fLib.readDataFromPropertyFile("./config/commondata.properties","user");
		String user2 = fLib.readDataFromPropertyFile("./config/commondata.properties","user2");
		String password2 = fLib.readDataFromPropertyFile("./config/commondata.properties","password2");
		String message = fLib.readDataFromPropertyFile("./config/commondata.properties", "message");

		//Step 2, 3, 4, 5: send message to one user and logout
		sendMessageAndVerify(user, message);
		HomePage hp = new HomePage(driver);
		
		//Step 6: login as second user with new browser & Verify chat icon with "badge"
		WebDriver driver1 = new ChromeDriver();
		System.out.println("New browser is launched..!");
		driver1.manage().window().maximize();
		driver1.get(url);
		HomePage hp1 = new HomePage(driver);
		
		loginWithDriverAndCredentials(driver1,username2, password2);
		
		wLib.waitForVisibilityOfEle(driver1, hp1.getChatIconWithBadge());
		
		Assert.assertTrue(hp1.getChatIconWithBadge().isDisplayed());
		Reporter.log("Chat icon is displayed with badge ", true);
		
		logoutWithDriverInstance(driver1);
		driver1.close();
	}
}
