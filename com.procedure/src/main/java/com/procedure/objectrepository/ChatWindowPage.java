package com.procedure.objectrepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatWindowPage {
	@FindBy(xpath = "//div[text()='Chat']")
	private WebElement chatText;
	
	@FindBy(xpath = "//input[@placeholder='Search & select to start a chat']")
	private WebElement searchTbx;
	
	@FindBy(xpath = "//button[@aria-label='Close']")
	private WebElement closeBtn;
	
	@FindBy(xpath = "(//li[@role='button'])[1]")
	private WebElement firstChatSuggetion;
	
	@FindBy(xpath = "//span[@class='ant-spin-dot ant-spin-dot-spin']")
	private WebElement loader;
	
	@FindBy(xpath = "//ul[@class='ant-list-items']")
	private WebElement searchResultSuggetion;
	
	@FindBy(xpath = "//p[text()='No Data']")
	private WebElement noDataText;
	
	public ChatWindowPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getChatText() {
		return chatText;
	}

	public WebElement getSearchTbx() {
		return searchTbx;
	}

	public WebElement getCloseBtn() {
		return closeBtn;
	}
	
	public WebElement getLoader() {
		return loader;
	}

	public WebElement getFirstChatSuggetion() {
		return firstChatSuggetion;
	}

	public WebElement getSearchResultSuggetion() {
		return searchResultSuggetion;
	}
	
	
	public WebElement getNoDataText() {
		return noDataText;
	}

	public List<WebElement> getAllSuggestions(WebDriver driver){
		return driver.findElements(By.xpath("//ul[@class='ant-list-items']/descendant::span[@class='ant-typography ant-typography-ellipsis ant-typography-ellipsis-single-line room-title mb-0']"));
	}
	
	public WebElement getUnreadMessageCount(WebDriver driver, String user) {
		return driver.findElement(By.xpath("//div[contains(.,'"+user+"')]/../../descendant::span[@class='d-inline-block align-self-end mt-1 text-white bg-primary rounded-circle text-center']"));
	}
	
	
}
