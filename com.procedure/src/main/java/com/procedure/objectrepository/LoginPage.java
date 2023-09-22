package com.procedure.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@role='img']")
	private WebElement houseWorksLogo;
	
	@FindBy(xpath = "//input[@placeholder='Enter username']")
	private WebElement unTbx;
	
	@FindAll({@FindBy( id = "username-continue"), @FindBy(xpath = "//button[@data-testid='auth-login-continue']")})
	private WebElement contiueBtn;
	
	@FindBy(xpath = "//input[@type='password']")
	private WebElement pwdTbx;
	
	
	public WebElement getHouseWorksLogo() {
		return houseWorksLogo;
	}

	public WebElement getUnTbx() {
		return unTbx;
	}

	public WebElement getContiueBtn() {
		return contiueBtn;
	}

	public WebElement getPwdTbx() {
		return pwdTbx;
	}
	
}
