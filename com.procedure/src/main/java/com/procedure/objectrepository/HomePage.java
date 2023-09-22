package com.procedure.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	@FindBy(xpath = "//li[@data-testid='activity-SignOut']")
	private WebElement logoutIcon;
	
	@FindBy(xpath = "//li[@data-testid='activity-chat']")
	private WebElement chatIcon;
	
	@FindBy(xpath = "//li[@data-testid='activity-Administrative']")
	private WebElement adminIcon;
	
	@FindBy(xpath = "//span[.='Administrative']")
	private WebElement adminText;
	
	@FindBy(xpath = "//span[text()='Yes']")
	private WebElement yesBtn;
	
	@FindBy(xpath = "//li[@data-testid='activity-chat']/descendant::div[@class='indicator']")
	private WebElement chatIconWithBadge;
	
	@FindBy(xpath = "//div[@class='indicator']")
	private WebElement badge;
	
	@FindBy(xpath = "//li[@data-testid='menu-Users']")
	private WebElement sideUserManu;
	
	@FindBy(xpath = "//h5[.='Patient Master']")
	private WebElement patientMasterText;
	
	public WebElement getYesBtn() {
		return yesBtn;
	}

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getLogoutIcon() {
		return logoutIcon;
	}

	public WebElement getChatIcon() {
		return chatIcon;
	}

	public WebElement getAdminIcon() {
		return adminIcon;
	}

	public WebElement getChatIconWithBadge() {
		return chatIconWithBadge;
	}

	public WebElement getBadge() {
		return badge;
	}

	public WebElement getAdminText() {
		return adminText;
	}

	public WebElement getSideUserManu() {
		return sideUserManu;
	}

	public WebElement getPatientMasterText() {
		return patientMasterText;
	}
	
	
}
