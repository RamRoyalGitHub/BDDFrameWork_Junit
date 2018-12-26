package com.test.base;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*import com.testfwk.core.base.BasePage;*/

public class BasePage {
	private final Logger log = LoggerFactory.getLogger(BasePage.class);
	protected WebDriver driver;
	private String msg = " is not visible within 60 seconds.";
	
	public BasePage(WebDriver webDriver) {
		this.driver = webDriver;
	}


	protected String getTextFromEelemnt(WebElement ele) {
		waitFor(ele);
		return ele.getText();
	}

	protected void click(WebElement ele) {
		waitFor(ele, 60);
		ele.click();
		log.info("Click performed on element: {}", ele);
	}

	protected void jsClick(WebElement ele) {
		waitFor(ele, 60);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
		log.info("Click performed on element");
	}

	protected void jsClick(WebElement ele, int time) {
		waitFor(ele, time);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
		log.info("Click performed on element");
	}

	protected void ddSelectionByIndex(WebElement ele, int index) {
		waitFor(ele, 60);
		Select sel = new Select(ele);
		sel.selectByIndex(index);
		log.info("Element selected from dropDown for index {}", ele);
	}

	protected void ddSelectionByValue(WebElement ele, String value) {
		waitFor(ele, 60);
		Select sel = new Select(ele);
		sel.selectByValue(value);
		log.info("Element selected from dropDown for value  {}", ele);
	}

	protected void ddSelectionByVisibleText(WebElement ele, String visibleText) {
		waitFor(ele, 60);
		Select sel = new Select(ele);
		sel.selectByVisibleText(visibleText);
		log.info("Element selected from dropDown for visible text  {}", ele);
	}

	protected void waitAndClick(WebElement ele) {
		waitForPageToLoad();
		try {
			waitForClick(ele);
			ele.click();
		} catch (Exception e) {
			acceptAlert();
			log.info("Exception occured on click of webelement: " + e);
		}
	}

	protected void waitAndClick(WebElement ele, int time) {
		try {
			waitForClick(ele, time);
			ele.click();
		} catch (UnhandledAlertException e) {
			acceptAlert();
			log.info("Exception occured on click of webelement: " + e);
		}
	}

	protected void setValue(WebElement ele, String str) {
		waitFor(ele, 60);
		ele.sendKeys(str);
		log("Value: " + str + " is set to element: " + ele);
	}

	protected void clearAndSetValue(WebElement ele, String str) {
		waitFor(ele, 60);
		ele.clear();
		ele.sendKeys(str);
		log("Value: " + str + " is set to element: " + ele);
	}

	public void log(String str) {
		log.info(str);
	}

	protected boolean isDisplayed(WebElement ele, int time) {
		waitFor(ele, time);
		return ele.isDisplayed();
	}

	protected void waitFor(WebElement ele) {
		waitForPageToLoad();
		log("Waiting 60 seconds for element: " + ele + " to be visible.");
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			log.info("Exception in wait for visible: " + e);
		}
	}

	protected void waitFor(WebElement ele, int time) {
		waitForPageToLoad();
		try {
			new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			log.info("Element is not visible within time: {}", time);
		}
	}

	protected void waitForClick(WebElement ele) {
		waitForPageToLoad();
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ele));
		} catch (Exception e) {
			log.info("Exception in wait for clickable" + e);
		}
	}

	protected void waitForClick(WebElement ele, int time) {
		waitForPageToLoad();
		try {
			new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(ele));
		} catch (Exception e) {
			log.info("Exception in wait for clickable: " + e);
		}
	}

	protected void waitForPage() {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("return document.readyState").equals("complete");
			/*
			 * new WebDriverWait(driver,
			 * 60).until(ExpectedConditions.(ExpectedCondition<Boolean>) wd ->
			 * ((JavascriptExecutor) wd)
			 * .executeScript("return document.readyState").equals("complete"));
			 */
		} catch (Exception e) {
			isAlertPresent();
			log.info("Wait for page to load failed with exception: " + e);
		}
	}

	protected void waitForPageToLoad() {
		waitForPage();
		try {
			waitFor(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(".fa-spinner"))));
		} catch (Exception e) {
			log.info("Exception in invisibility of spinner: " + e);
		}
		waitForPage();
	}

	protected boolean isAlertPresent() {
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (TimeoutException e) {
			log.info("Alert not present: " + e);
		}
		return foundAlert;
	}

	protected void switchToWindowByURL(String urlToSwitch) {
		waitFor(ExpectedConditions.numberOfWindowsToBe(2), 60);
		Set<String> allWindows = driver.getWindowHandles();
		for (String currentWindow : allWindows) {
			if (driver.switchTo().window(currentWindow).getCurrentUrl().contains(urlToSwitch)) {
				driver.switchTo().window(currentWindow);
				return;
			}
		}
	}

	protected void verifyCertificate() {
		if (driver.getTitle().equalsIgnoreCase("Certificate Error: Navigation Blocked"))
			waitAndClick(driver.findElement(By.cssSelector("#overridelink")), 20);
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);
		driver.manage().window().maximize();
	}

	protected void switchToParentWindow() {
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
	}

	protected void switchToSecondWindow() {
		waitFor(ExpectedConditions.numberOfWindowsToBe(2), 60);
		Object window1 = driver.getWindowHandles().toArray()[1];
		driver.switchTo().window((String) window1);
	}

	protected void switchToSecondWindow(int time) {
		waitFor(ExpectedConditions.numberOfWindowsToBe(2), time);
		Object window1 = driver.getWindowHandles().toArray()[1];
		driver.switchTo().window((String) window1);
	}

	protected <T> T waitFor(ExpectedCondition<T> waitCondition, long timeout) {
		return (new WebDriverWait(driver, timeout, (long) 1000)).until(waitCondition);
	}

	public <T> T waitFor(ExpectedCondition<T> waitCondition) {
		return (new WebDriverWait(driver, (long) 120, (long) 1000)).until(waitCondition);
	}

	protected BasePage updateURL() {
		verifyCertificate();
		String url = driver.getCurrentUrl();
		if (!url.contains("qatwswatx532.gsm1900.org")) {
			url = url.replace("qatwswatx532", "qatwswatx532.gsm1900.org");
			driver.get(url);
		}
		if (!url.contains("qatwswatx522.gsm1900.org")) {
			url = url.replace("qatwswatx522", "qatwswatx522.gsm1900.org");
			driver.get(url);
		}
		if (!url.contains("qatwswatx162.gsm1900.org")) {
			url = url.replace("qatwswatx162", "qatwswatx162.gsm1900.org");
			driver.get(url);
		}
		return this;
	}

	protected void setMacIDCookie(String macID, WebDriver driver) {
		Cookie ck = new Cookie("MacId", macID); // 00059a3c7800
		driver.manage().addCookie(ck);
		ck = new Cookie("NetID", macID); // 00059a3c7800
		driver.manage().addCookie(ck);
	}

/*	protected void switchFrame(WebElement ele) {
		try {
			driver.switchTo().defaultContent();
			waitFor(ele);
			driver.switchTo().frame(ele);
		} catch (ElementNotVisibleException e) {
			log.error(e.getMessage() + e);
			MatcherAssert.assertThat(ele + msg, false);
		}
	}

	protected void switchFrameById(String ele) {
		try {
			driver.switchTo().frame(ele);
		} catch (ElementNotVisibleException e) {
			log.error(e.getMessage() + e);
			MatcherAssert.assertThat("", false);
		}
	}*/

	protected void switchFrameInFrame(WebElement frame, WebElement frame2) {
		try {
			driver.switchTo().defaultContent();
			waitFor(frame);
			driver.switchTo().frame(frame).switchTo().frame(frame2);
		} catch (ElementNotVisibleException e) {
			log.error(e.getMessage() + e);
		}
	}

	protected String getValue(WebElement ele, int time) {
		String text = null;
		try {
			waitFor(ele, time);
			text = ele.getText();
		} catch (Exception e) {
			log("Get text failed for " + ele);
		}
		return text;
	}

	protected void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	protected void acceptAlert() {
		try {
			if (isAlertPresent()) {
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				waitForPageToLoad();
			}
		} catch (Exception e) {
			log.info("Exception occured in alert handling." + e);
		}
	}

	protected void dismissAlert() {
		if (isAlertPresent()) {
			driver.switchTo().alert().dismiss();
			driver.switchTo().defaultContent();
			waitForPageToLoad();
		}
	}

	protected boolean isElementPresent(WebElement ele) {
		boolean flag = false;
		try {
			if (ele.isDisplayed())
				flag = true;
		} catch (Exception e) {
			log.error(e.getMessage() + e);
		}
		return flag;
	}

	protected boolean isElementPresentSpecific(WebElement ele) {
		boolean flag = false;
		try {
			if (ele.isDisplayed())
				flag = true;
		} catch (Exception e) {
			log.info("Element is not visible: {}", ele);
		}
		return flag;
	}

	protected void close() {
		driver.close();
	}

}
