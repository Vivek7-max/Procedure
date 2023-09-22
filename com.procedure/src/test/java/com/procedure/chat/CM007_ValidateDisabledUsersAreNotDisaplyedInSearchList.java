package com.procedure.chat;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.procedure.genericutility.BaseClass;
import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
import com.procedure.objectrepository.UserDetailsPage;
import com.procedure.objectrepository.UserPage;
public class CM007_ValidateDisabledUsersAreNotDisaplyedInSearchList extends BaseClass {
	@Test
	public void testValidateDisabledUsersAreNotDisaplyedInSearchList() throws InterruptedException {
		//Page instances
		HomePage hp = new HomePage(driver);
		ChatWindowPage cwp = new ChatWindowPage(driver);
		UserPage up = new UserPage(driver);
		UserDetailsPage udp = new UserDetailsPage(driver);
		
		//Step 2: Click on Administrative and verify side menu
		hp.getAdminIcon().click();
		Assert.assertTrue(hp.getAdminText().isDisplayed());
		Reporter.log("Administrative text is displayed",true);
		
		//Step 3: Click on user and verify user list view
		hp.getSideUserManu().click();
		wLib.waitForVisibilityOfEle(driver, up.getEmailHead());
		wLib.moveMouseCurserOnEle(driver, up.getEmailHead());
		
		Thread.sleep(3000);
		Assert.assertTrue(up.getSearchTbx().isDisplayed());
		Reporter.log("User detail list is displayed", true);
		
		//Step 4: Open any user and verify 
		String userToBeSearched = "Harsha";
		up.getSearchTbx().sendKeys(userToBeSearched);
		wLib.waitForVisibilityOfEle(driver, up.getSearchedUser(driver, userToBeSearched));
		Thread.sleep(3000);
		up.getSearchedUser(driver, userToBeSearched).click();
		Thread.sleep(3000);
		Assert.assertTrue(udp.getBasicDetailsHeading().isDisplayed());
		Reporter.log("User Details page is displayed", true);
		
		wLib.scrollTillElementIsVisible(driver, udp.getDisableUserBtn());
		
		String buttonText = udp.getDisableUserBtn().getText();
		
		if (buttonText.equals("Enable User")) {
			Reporter.log("User is already Disabled", true);
			String userStatus = udp.getUserStatus().getText();
			Assert.assertTrue(userStatus.equals("Disabled"));
			Reporter.log("User Status is Disabled", true);
			hp.getChatIcon().click();
			cwp.getSearchTbx().sendKeys(userToBeSearched);
			wLib.waitForInvisibilityOfEle(driver, cwp.getLoader());
			Assert.assertTrue(cwp.getNoDataText().isDisplayed());
			Reporter.log("No data text is displayed", true);
			cwp.getCloseBtn().click();
		}else {
			udp.getDisableUserBtn().click();
			Assert.assertTrue(udp.getConfirmBtn().isDisplayed());
			Reporter.log("Confirmation modal is displayed", true);
			udp.getConfirmBtn().click();
			Assert.assertTrue(udp.getToasterMessage().isDisplayed());
			Reporter.log("Toaster Message is displayed", true);
			
			String userStatus = udp.getUserStatus().getText();
			Assert.assertTrue(userStatus.equals("Disabled"));
			Reporter.log("User Status is Disabled", true);
			
			hp.getChatIcon().click();
			cwp.getSearchTbx().sendKeys(userToBeSearched);
			wLib.waitForInvisibilityOfEle(driver, cwp.getLoader());
			Assert.assertTrue(cwp.getNoDataText().isDisplayed());
			Reporter.log("No data text is displayed", true);
			cwp.getCloseBtn().click();
		}
	}
}