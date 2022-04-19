package org.base;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

	public static WebDriver browserLaunch(String browserName) {

		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
		}
		return driver;
	}

	public static void launchUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	public static void implicityWait(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public static String getCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public static String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	public static void quit() {
		driver.quit();
	}

	public static void close() {
		driver.close();
	}

	public static void sendkeys(WebElement e,String value) {
		e.sendKeys(value);
	}
	public static String getText(WebElement e) {
		String text = e.getText();
		return text;
	}

	public static boolean isEnabled(WebElement e) {
		boolean enabled = e.isEnabled();
		return enabled;
	}

	public static boolean isDisplayed(WebElement e) {
		boolean displayed = e.isDisplayed();
		return displayed;
	}

	public static boolean isSelected(WebElement e) {
		boolean selected = e.isSelected();
		return selected;
	}

	public static String tagName(WebElement e) {
		String tagName = e.getTagName();
		return tagName;
	}

	public static String getAttribute(WebElement e) {
		String attribute = e.getAttribute("value");
		return attribute; 
	}

	public static void clear(WebElement e) {
		e.clear();
	}

	public String getUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public static String getCssValue(WebElement e,String value) {
		String cssValue = e.getCssValue("value");
		return cssValue;
	}


	public static int getHeight(WebElement e) {
		Dimension size = e.getSize();
		int height = size.getHeight(); 
		return height;
	}

	public static int getWidth(WebElement e) {
		Dimension size = e.getSize();
		int width = size.getWidth();
		return width;
	}

	// actions

	public static void buttonClick(WebElement e) {
		e.click();
	}

	public static void clickAndHold(WebElement e) {
		Actions a = new Actions(driver);
		a.clickAndHold(e).perform();
	}

	public static void movetoElement (WebElement e) {
		Actions a = new Actions(driver);
		a.moveToElement(e).perform();
	}

	public static void dragAndDrop(WebElement src,WebElement dst) {
		Actions a = new Actions(driver);
		a.dragAndDrop(src, dst).perform();
	}

	public static void doubleClick(WebElement e) {
		Actions a = new Actions(driver);
		a.doubleClick(e).perform();
	}


	public static void contextClick(WebElement e) {
		Actions a = new Actions(driver);
		a.contextClick(e).perform();
	}

	public static void keyUp(String key) {
		Actions a = new Actions(driver);
		a.keyUp(key).perform();
	}

	public static void keyDown(String key) {
		Actions a = new Actions(driver);
		a.keyDown(key).perform();
	}

	// select 

	public static void selectByIndex(WebElement e,int index) {
		Select s = new Select(e);
		s.selectByIndex(index);
	}

	public static void selectByValue(WebElement e,String value) {
		Select s = new Select(e);
		s.selectByValue(value);
	}

	public static void selectByVisibleText(WebElement e,String value) {
		Select s = new Select(e);
		s.selectByVisibleText(value);
	}

	public static List<WebElement> getOptions(WebElement e) {
		Select s = new Select(e);
		List<WebElement> options = s.getOptions();
		return options;
	}

	public static List<WebElement> getAllSelectedOptions(WebElement e) {
		Select s = new Select(e);
		List<WebElement> options = s.getAllSelectedOptions();
		return options;
	}


	public static WebElement getFirstSelectedOptions(WebElement e) {
		Select s = new Select(e);
		WebElement options = s.getFirstSelectedOption();
		return options;
	}

	// deselect

	public static void deselectAll(WebElement e) {
		Select s = new Select(e);
		s.deselectAll();
	}

	public static void deslectByIndex(WebElement e,int index) {
		Select s = new Select(e);
		s.deselectByIndex(index);
	}

	public static void deslectByValue(WebElement e,String value) {
		Select s = new Select(e);
		s.deselectByValue(value);
	}

	public static void deselectByVisibleText(WebElement e,String value) {
		Select s = new Select(e);
		s.deselectByVisibleText(value);
	}

	// alert

	public static Alert alertSwitch() {
		Alert alert = driver.switchTo().alert();
		return alert;
	}

	public static void alertAccept() {
		driver.switchTo().alert().accept();
	}

	public static void alertDismiss() {
		driver.switchTo().alert().dismiss();
	}

	public static String alertGetText() {
		String text = driver.switchTo().alert().getText();
		return text;
	}

	public static void promptAlert(String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	//screen shot

	public static void screenShot(String name) throws IOException {

		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE); 	
		File dst =new File(System.getProperty("user.dir"+ "\\src\\test\\resources\\screen\\"+name+".png"));
		FileUtils.copyFile(src, dst);
	}

	//java script

	public static void jsSet(WebElement e, String value) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('value','"+value+"')", e);

	}

	public static String jsGet(WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		Object executeScript = js.executeScript("return arguments[0].getAttribute('value')", e);
		String string = executeScript.toString();
		return string;
	}

	public static void scrollDown(WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", e);
	}

	public static void scrollUp(WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false)", e);
	}

	// frame

	public static void frameByIndex(int i) {
		driver.switchTo().frame(i);
	}

	public static void frameById(String id) {
		driver.switchTo().frame(id);
	}

	public static void frameByElement(WebElement e) {
		driver.switchTo().frame(e);
	}

	public static void parentFrame() {
		driver.switchTo().parentFrame();
	}

	public static void returnFrame() {
		driver.switchTo().defaultContent();
	}


	// navigate

	public static void navigateUrl(String url) {
		driver.navigate().to(url);
	}

	public static void navigateRefresh() {
		driver.navigate().refresh();
	}

	public static void navigateForward() {
		driver.navigate().forward();
	}

	public static void navigateBack() {
		driver.navigate().back();
	}

	public static String WindowHandle() {
		String windowHandle = driver.getWindowHandle();
		return windowHandle;
	}




}
