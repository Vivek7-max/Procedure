package com.procedure.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
import com.procedure.objectrepository.UserChatWindowPage;

public class CM014_ValidateTheLatestMessageOfEachChatShouldBeDisplayedInChatHistoryListForThatChat extends BaseClass {
	@Test
	public void testValidateTheLatestMessageOfEachChatShouldBeDisplayedInChatHistoryListForThatChat() throws Throwable {
		//Page Instances
		HomePage hp = new HomePage(driver);
		ChatWindowPage cwp = new ChatWindowPage(driver);
		UserChatWindowPage ucwp = new UserChatWindowPage(driver);

		//Step 2: click on chat icon and verify chat window is displayed
		hp.getChatIcon().click();
		wLib.waitForVisibilityOfEle(driver, cwp.getChatText());
		Assert.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat window is displayed.",true);
		Thread.sleep(5000);
		
		//Step 3: List of chat history
		List<WebElement> allUsers = driver.findElements(By.xpath("//div[@class='ant-typography ant-typography-ellipsis ant-typography-ellipsis-single-line room-title mb-0']"));
		List<String> allUserNames = new ArrayList<>();
		for (WebElement user : allUsers) {
			String userName = user.getText();
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
		
		//Step 4: Click on first user and get latest message
		allUsers.get(0).click();
		wLib.waitForVisibilityOfEle(driver, ucwp.getLatestMessage());
		String latestMessage = ucwp.getLatestMessage().getText();
		ucwp.getBackButton().click();
		
		Assert.assertTrue(ucwp.getUserHistoryList().isDisplayed());
		Reporter.log("User history list is displayed after clicking back", true);
		
		List<WebElement> allHistoryMessages = driver.findElements(By.xpath("//div[@class='ant-typography ant-typography-ellipsis ant-typography-ellipsis-single-line last-room-message mb-0 font-weight-normal']"));
		String firstHistoryMessage = allHistoryMessages.get(0).getText();
		System.out.println("latestMessage: "+latestMessage);
		System.out.println("firstHistoryMessage: "+firstHistoryMessage);
		Assert.assertTrue(latestMessage.contains(firstHistoryMessage));
		Reporter.log("Latest message is displayed in chat history", true);
		
		cwp.getCloseBtn().click();
	}
}
