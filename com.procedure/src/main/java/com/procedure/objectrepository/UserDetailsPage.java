package com.procedure.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserDetailsPage {
	@FindBy(xpath = "//div[.='Basic details' and @class='ant-card-head-title']")
	private WebElement basicDetailsHeading;
	
	@FindBy(xpath = "//button[@data-testid='user-disable']/span")
	private WebElement disableUserBtn;
	
	@FindBy(xpath = "//span[.='Confirm']")
	private WebElement confirmBtn;
	
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement toasterMessage;
	
	@FindBy(xpath = "//div[.='Status']/../descendant::span")
	private WebElement userStatus;
	
	public UserDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getBasicDetailsHeading() {
		return basicDetailsHeading;
	}

	public WebElement getDisableUserBtn() {
		return disableUserBtn;
	}

	public WebElement getConfirmBtn() {
		return confirmBtn;
	}

	public WebElement getToasterMessage() {
		return toasterMessage;
	}

	public WebElement getUserStatus() {
		return userStatus;
	}

}
