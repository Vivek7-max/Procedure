package com.procedure.chat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
import com.procedure.objectrepository.UserChatWindowPage;
public class CM015_ValidateDateAndTimeOnChatHistoryListIsDisplayedAsExpected extends BaseClass {
	@Test
	public void testValidateDateAndTimeOnChatHistoryListIsDisplayedAsExpected() throws Throwable {
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
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat window is displayed.",true);
		Thread.sleep(5000);

		//Step 3: List of chat history
		List<WebElement> allUsers = driver.findElements(By.xpath("//div[@class='ant-typography ant-typography-ellipsis ant-typography-ellipsis-single-line room-title mb-0']"));
		List<String> allUserNames = new ArrayList<>();
		for (WebElement eachUser : allUsers) {
			String userName = eachUser.getText();
			allUserNames.add(userName);
		}
		System.out.println(allUserNames);
		List<WebElement> allTimes = driver.findElements(By.xpath("//div[@class='small']"));
		List<String> allUserTimes = new ArrayList<>();
		Set<String> set = new HashSet<>();
		for (WebElement time : allTimes) {
			String userTime = time.getText();
			allUserTimes.add(userTime);
			set.add(userTime);
		}
		System.out.println(allUserTimes);
		System.out.println(set);


		List<String> finalList = new ArrayList();
		for (int i = 0; i < allUserNames.size(); i++) {
			finalList.add(allUserNames.get(i)+" - "+allUserTimes.get(i));
		}
		System.out.println(finalList);
		
		cwp.getCloseBtn().click();
	}
}
