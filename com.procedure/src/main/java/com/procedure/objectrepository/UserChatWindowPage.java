package com.procedure.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserChatWindowPage {
	
	@FindBy(xpath = "//textarea[@placeholder='Type a message']")
	private WebElement messageTextfield;
	
	@FindBy(xpath = "//textarea[@placeholder='Type a message']/following-sibling::BUTTON[@type='button']")
	private WebElement sendButton;
	
	@FindBy(xpath = "(//div[@class='flex-1']/div[@class='ant-typography m-0 d-inline message-color'])[last()]")
	private WebElement latestMessage;
	
	@FindBy(xpath = "//ul[@class='ant-list-items']")
	private WebElement userHistoryList;
	
	@FindBy(xpath = "//SPAN[@class='ant-avatar ant-avatar-circle chat-avatar align-self-center rounded-circle text-white']/preceding-sibling::BUTTON[@type='button']")
	private WebElement backButton;
	
	public UserChatWindowPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public WebElement getMessageTextfield() {
		return messageTextfield;
	}


	public WebElement getSendButton() {
		return sendButton;
	}

	
	public WebElement getLatestMessage() {
		return latestMessage;
	}
	
	public WebElement getUserHistoryList() {
		return userHistoryList;
	}
	
	
	public WebElement getBackButton() {
		return backButton;
	}
	public WebElement getUserChatIcon(WebDriver driver, String user) {
		return driver.findElement(By.xpath("//DIV/DIV[contains(normalize-space(.),'"+user+"')]/.././descendant::SPAN[contains(@class,'ant-avatar ant-avatar-circle chat-avatar align-self-center rounded-circle text-white')]"));
	}
	
	public WebElement getTodaysMessage(WebDriver driver, String message) {
		return driver.findElement(By.xpath("(//small[text()='Today']/../following-sibling::span/descendant::div[contains(.,'"+message+"')])[last()]"));
	}
	
	public WebElement getMessageTime(WebDriver driver, String message) {
		return driver.findElement(By.xpath("(//small[text()='Today']/../following-sibling::span/descendant::div[text()='"+message+"']/following-sibling::div)[last()]"));
	}
	
	public WebElement getOldMessage(WebDriver driver, String date, String message) {
		return driver.findElement(By.xpath("//small[text()='"+date+"']/../following-sibling::span/descendant::div[text()='"+message+"']"));
	}
}
