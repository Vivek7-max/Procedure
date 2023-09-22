package com.procedure.genericutility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.bouncycastle.crypto.KeyEncoder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActionUtility {
	public void waitForElementEmplicitly(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void waitForVisibilityOfEle(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void waitForInvisibilityOfEle(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void doubleClickOnEle(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.doubleClick(ele);
	}
	
	public void moveMouseCurserOnEle(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();;
	}
	
	public void scrollTillElementIsVisible(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.scrollToElement(ele).perform();
	}
	
	public void randomClick(WebDriver driver, int xIndex, int yIndex) {
		Actions action =new Actions(driver);
		action.moveByOffset(xIndex, yIndex).click().perform();
	}
	
}
