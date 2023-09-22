package com.procedure.chat;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.procedure.genericutility.BaseClass;
import com.procedure.genericutility.FileUtility;
import com.procedure.genericutility.JavaUtility;
import com.procedure.genericutility.WebActionUtility;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
import com.procedure.objectrepository.UserChatWindowPage;

@Listeners(com.procedure.genericutility.ListenerImplementation.class)
public class CM005_ValidateSearchWhenResultsAreDisplayed extends BaseClass {
	@Test
	public void testValidateSearchWhenResultsAreDisplayed() throws Throwable {
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
		Assert.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat window is displayed.",true);

		//Step 3: Enter user and verify loader
		cwp.getSearchTbx().sendKeys("Chai Sra");
		Assert.assertTrue(cwp.getLoader().isDisplayed());
		Reporter.log("Loader is displayed",true);
		wLib.waitForInvisibilityOfEle(driver, cwp.getLoader());

		//Verification of each user should contain entered keyword

		List<WebElement> allSuggestions = cwp.getAllSuggestions(driver);
		List<String> l = new ArrayList();
		for (WebElement suggestion : allSuggestions) {
			String data = suggestion.getText();
			l.add(data);
		}
		System.out.println("Number of Suggestions: "+l.size());
		System.out.println("All suggestions: "+l);
		String str = "Chai Sra";
		String [] strArr = null;
		boolean result=false;
		if (str.contains(" ")) {
			strArr=str.split(" ");
			for (String ele : l) {
				for (int i = 0; i < strArr.length; i++) {
					if (ele.contains(strArr[i])) {
						result = true;
						break;
					}
					else {
						result = false;
					}
				}
			}
			if (result) {
				System.out.println("Each Element Contains "+str);
			}else {
				System.out.println("Each Element not Contains "+str);
			}
		}else {
			for (String ele : l) {
				if (ele.contains(str)) {
					result = true;
					break;
				}
				else {
					result = false;
				}
			}
			if (result) {
				System.out.println("Each Element Contains "+str);
			}else {
				System.out.println("Each Element not Contains "+str);
			}
		}
		
		cwp.getCloseBtn().click();
	}
}


//Time for which loader is displayed :
//		long start = System.currentTimeMillis();
//		long end = 0;
//		for(;;) {
//			try {
//				cwp.getLoader().isDisplayed();
//				
//			} catch (Exception e) {
//				end = System.currentTimeMillis();
//				break;
//			}
//		}
//		System.out.println("Start : "+start);
//		System.out.println("End : "+end);
//		System.out.println("time : "+(end-start));




