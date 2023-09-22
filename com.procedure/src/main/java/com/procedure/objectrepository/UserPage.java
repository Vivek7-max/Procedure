package com.procedure.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage {
	@FindBy(xpath = "//th[.='Email']")
	private WebElement emailHead;
	
	@FindBy(xpath = "//input[@placeholder='Search users']")
	private WebElement searchTbx;
	
	public UserPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getEmailHead() {
		return emailHead;
	}

	public WebElement getSearchTbx() {
		return searchTbx;
	}
	
	public WebElement getSearchedUser(WebDriver driver, String user) {
		return driver.findElement(By.xpath("//span[.='Name']/ancestor::thead/following-sibling::tbody/descendant::td[contains(.,'"+user+"')]"));
	}
	
}
