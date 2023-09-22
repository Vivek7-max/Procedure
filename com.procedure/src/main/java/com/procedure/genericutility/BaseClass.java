package com.procedure.genericutility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.procedure.objectrepository.ChatWindowPage;
import com.procedure.objectrepository.HomePage;
import com.procedure.objectrepository.LoginPage;
import com.procedure.objectrepository.UserChatWindowPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;

	public WebActionUtility wLib = new WebActionUtility();
	public FileUtility fLib = new FileUtility();
	public JavaUtility jLib = new JavaUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public String url;

	@BeforeClass(groups = "smokeTest")
	public void openBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Browser Launched..!");

	}

	@BeforeMethod(groups = "smokeTest")
	public void login() throws Throwable {
		String url = fLib.readDataFromPropertyFile("./config/commondata.properties", "url");
		this.url = url;
		String username = fLib.readDataFromPropertyFile("./config/commondata.properties", "username");
		String password = fLib.readDataFromPropertyFile("./config/commondata.properties", "password");
		driver.get(url);
		LoginPage loginPage = new LoginPage(driver);
		wLib.waitForElementEmplicitly(driver);
		
		loginPage.getUnTbx().sendKeys(username);
		loginPage.getContiueBtn().click();
		wLib.waitForVisibilityOfEle(driver, loginPage.getPwdTbx());
		if (loginPage.getPwdTbx().isDisplayed()) {
			System.out.println("Password text field is displayed");
			loginPage.getPwdTbx().sendKeys(password);
			Thread.sleep(1);
			loginPage.getContiueBtn().click();
		}else {
			System.out.println("Passowrd text field is not displayed");
		}
		System.out.println("Logged in successfully..!");
	}

	@AfterMethod(groups = "smokeTest")
	public void logout() throws Throwable {
		HomePage homePage = new HomePage(driver);
		homePage.getLogoutIcon().click();
		wLib.waitForVisibilityOfEle(driver, homePage.getYesBtn());
		homePage.getYesBtn().click();
		System.out.println("Logged out successfully..!");
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
		System.out.println("Browser Closed..");
	}

	public void loginWithCredentials(String username, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		wLib.waitForElementEmplicitly(driver);
		loginPage.getUnTbx().sendKeys(username);
		loginPage.getContiueBtn().click();
		wLib.waitForVisibilityOfEle(driver, loginPage.getPwdTbx());
		if (loginPage.getPwdTbx().isDisplayed()) {
			System.out.println("Password text field is displayed");
			loginPage.getPwdTbx().sendKeys(password);
			Thread.sleep(1000);
			loginPage.getContiueBtn().click();
		}else {
			System.out.println("Passowrd text field is not displayed");
		}
		System.out.println("Logged in successfully..!");
	}

	public void sendMessageAndVerify(String user, String message) throws Throwable {
		wLib.waitForElementEmplicitly(driver);
		//Page Instances
		HomePage hp = new HomePage(driver);
		ChatWindowPage cwp = new ChatWindowPage(driver);
		UserChatWindowPage ucwp = new UserChatWindowPage(driver);

		//Step 2: click on chat icon and verify chat window is displayed
		wLib.waitForVisibilityOfEle(driver, hp.getChatIcon());
		hp.getChatIcon().click();
		SoftAssert sa = new SoftAssert();
		wLib.waitForVisibilityOfEle(driver, cwp.getChatText());
		Assert.assertTrue(cwp.getChatText().isDisplayed());
		Reporter.log("Chat window is displayed.",true);

		//Step 3: Enter user and verify search results are displayed
		cwp.getSearchTbx().sendKeys(user);
		wLib.waitForInvisibilityOfEle(driver, cwp.getLoader());
		Assert.assertTrue(cwp.getSearchResultSuggetion().isDisplayed());
		Reporter.log("Search result suggetion is displayed",true);


		//Step 4: Click on user and verify user chat window is displayed
		wLib.waitForVisibilityOfEle(driver, cwp.getFirstChatSuggetion());
		cwp.getFirstChatSuggetion().click();
		wLib.waitForVisibilityOfEle(driver, ucwp.getUserChatIcon(driver, user));
		Assert.assertTrue(ucwp.getUserChatIcon(driver, user).isDisplayed());
		Reporter.log("User chat window is displayed.",true);

		//Step 5: Send message to user and verify the message
		ucwp.getMessageTextfield().sendKeys(message);
		Thread.sleep(1000);
		ucwp.getSendButton().click();
		wLib.waitForInvisibilityOfEle(driver, cwp.getLoader());
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
	public void loginWithDriverAndCredentials(WebDriver driver,String username, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		wLib.waitForElementEmplicitly(driver);
		loginPage.getUnTbx().sendKeys(username);
		loginPage.getContiueBtn().click();
		wLib.waitForVisibilityOfEle(driver, loginPage.getPwdTbx());
		if (loginPage.getPwdTbx().isDisplayed()) {
			System.out.println("Password text field is displayed");
			loginPage.getPwdTbx().sendKeys(password);
			Thread.sleep(1000);
			loginPage.getContiueBtn().click();
		}else {
			System.out.println("Passowrd text field is not displayed");
		}
		System.out.println("Logged in successfully..!");
	}
	public void logoutWithDriverInstance(WebDriver driver) throws Throwable {
		HomePage homePage = new HomePage(driver);
		homePage.getLogoutIcon().click();
		wLib.waitForVisibilityOfEle(driver, homePage.getYesBtn());
		homePage.getYesBtn().click();
		System.out.println("Logged out successfully..!");
	}

}
//https://github.com/Vivek7-max/com.procedure.git
