package core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import base.Android;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

//written manually
import static java.time.Duration.ofSeconds;
import java.time.Duration;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;




public class UiObject {

	private String locator;
	private String objectName;
	
	UiObject(String locator){
		this.locator = locator;
		MyLogger.log.debug("Created new UiObject: "+this.locator);
	}
	
	private boolean isXpath(){
		return !locator.contains("UiSelector");
	}
		
	public boolean exist(){
		WebElement element = getElement(locator);
		boolean isVisible = false;
		if(element != null){
			try{
				isVisible = element.isDisplayed();
				Report.pass("'" +locator+ "'" + " is exist");
			} catch(NoSuchElementException e){
				Report.info("'" +locator+ "'" + " is NOT exist");
			}
		}else{
			Report.info("'" +locator+ "'" + " is NOT exist");
		} return isVisible;
	}
	/*public boolean exist(){
		WebElement element;
		if(isXpath())element = Android.driver.findElementByXPath(locator);
		else element = Android.driver.findElementByAndroidUIAutomator(locator);
		return element.isDisplayed();
	}*/

	
	public WebElement getElement(String locator){
		try {
			if(isXpath()) return Android.driver.findElementByXPath(locator);
			else return Android.driver.findElementByAndroidUIAutomator(locator);
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public boolean isChecked(){
		WebElement element = getElement(locator);
		boolean isElementChecked = false;
		if(element != null){
			try{
				isElementChecked = element.getAttribute("checked").equalsIgnoreCase("true");
				Report.pass("'" +locator+ "'" + " is checked");
			} catch(NoSuchElementException e){
				Report.info("'" +locator+ "'" + " is NOT checked");
			}
		}else{
			Report.info("'" +locator+ "'" + " is NOT checked");
		} return isElementChecked;	
	}	
	/*public boolean isChecked(){
		try{
			WebElement element;
			if(isXpath()) element = Android.driver.findElementByXPath(locator);
			else element = Android.driver.findElementByAndroidUIAutomator(locator);
			return element.getAttribute("checked").equalsIgnoreCase("true");
		}catch(NoSuchElementException e){
			return false;
		}
	}*/
	
	public boolean checkable(){
		WebElement element = getElement(locator);
		boolean isCheckable = false;
		if(isCheckable){
			try {
				isCheckable = element.getAttribute("checkable").equalsIgnoreCase("true");
				Report.pass("'" +locator+ "'" + " is checkable");
			} catch (Exception e) {
				Report.info("'" +locator+ "'" + " is NOT checkable");
			}
		}else{
			Report.info("'" +locator+ "'" + " is NOT checkable");
		} return isCheckable;		
	}	
	/*public boolean checkable(){
		try{
			WebElement element;
			if(isXpath()) element = Android.driver.findElementByXPath(locator);
			else element = Android.driver.findElementByAndroidUIAutomator(locator);
			return element.getAttribute("checkable").equalsIgnoreCase("true");
		}catch(NoSuchElementException e){
			return false;
		}
	}*/
	
	
	public boolean clickable(){
		WebElement element = getElement(locator);
		boolean isClickable = false;
		try{
			if(element != null){
				isClickable = element.getAttribute("clickable").equalsIgnoreCase("true");
				Report.pass("'" +locator+ "'" + " is clickable");
			}else{
				Report.info("'" +locator+ "'" + " is NOT clickable");
			}				
		}catch(NoSuchElementException e){
			Report.info("'" +locator+ "'" + " is NOT clickable");
		} return isClickable;
	}	
	/*public boolean clickable(){
		try{
			WebElement element;
			if(isXpath()) element = Android.driver.findElementByXPath(locator);
			else element = Android.driver.findElementByAndroidUIAutomator(locator);
			return element.getAttribute("clickable").equalsIgnoreCase("true");
		}catch(NoSuchElementException e){
			return false;
		}
	}*/
	
	
	public boolean isEnabled(){
		WebElement element = getElement(locator);
		boolean isEnabled = false;
		try{
			if(element != null){
				isEnabled = element.getAttribute("enabled").equalsIgnoreCase("true");
				Report.pass("'" +locator+ "'" + " is clickable");
			}else{
				Report.info("'" +locator+ "'" + " is enabled");
			}			
		}catch(NoSuchElementException e){
			Report.info("'" +locator+ "'" + " is enabled");
		} return isEnabled;
	}
	/*public boolean isEnabled(){
		try{
			WebElement element;
			if(isXpath()) element = Android.driver.findElementByXPath(locator);
			else element = Android.driver.findElementByAndroidUIAutomator(locator);
			return element.getAttribute("enabled").equalsIgnoreCase("true");
		}catch(NoSuchElementException e){
			return false;
		}
	}*/
	
	public boolean isFocusable(){
		WebElement element = getElement(locator);
		boolean isFocusable = false;
		try{
			if(element != null){
				isFocusable = element.getAttribute("focusable").equalsIgnoreCase("true");
				Report.pass("'" +locator+ "'" + " is focusable");
			}else{
				Report.info("'" +locator+ "'" + " is NOT focusable");
			}				 
		}catch(NoSuchElementException e){
			Report.info("'" +locator+ "'" + " is NOT focusable");
		} return isFocusable;
	}
	/*public boolean isFocusable(){
		try{
			WebElement element;
			if(isXpath()) element = Android.driver.findElementByXPath(locator);
			else element = Android.driver.findElementByAndroidUIAutomator(locator);
			return element.getAttribute("focusable").equalsIgnoreCase("true");
		}catch(NoSuchElementException e){
			return false;
		}
	}*/
	
	
	public boolean isFocused(){
		WebElement element = getElement(locator);
		boolean isFocused = false;
		try{
			if(element != null){
				isFocused = element.getAttribute("focused").equalsIgnoreCase("true");
				Report.pass("'" +locator+ "'" + " is focused");
			}else{
				Report.info("'" +locator+ "'" + " is NOT focused");
			}			 
		}catch(NoSuchElementException e){
			Report.info("'" +locator+ "'" + " is NOT focused");
		}return isFocused;
	}
	/*public boolean isFocused(){
		try{
			WebElement element;
			if(isXpath()) element = Android.driver.findElementByXPath(locator);
			else element = Android.driver.findElementByAndroidUIAutomator(locator);
			return element.getAttribute("focused").equalsIgnoreCase("true");
		}catch(NoSuchElementException e){
			return false;
		}
	}*/
	
	public boolean isScrollable(){
		WebElement element = getElement(locator);
		boolean isScrollable = false;
		try{
			if(element != null){
				isScrollable = element.getAttribute("scrollable").equalsIgnoreCase("true");
				Report.pass("'" +locator+ "'" + " is scrollable");
			}else{
				Report.info("'" +locator+ "'" + " is NOT scrollable");
			}			 
		}catch(NoSuchElementException e){
			Report.info("'" +locator+ "'" + " is NOT scrollable");
		}return isScrollable;
	}
	/*public boolean isScrollable(){
		try{
			WebElement element;
			if(isXpath()) element = Android.driver.findElementByXPath(locator);
			else element = Android.driver.findElementByAndroidUIAutomator(locator);
			return element.getAttribute("scrollable").equalsIgnoreCase("true");
		}catch(NoSuchElementException e){
			return false;
		}
	}*/
	
	
	public boolean isLongClickable(){
		WebElement element = getElement(locator);
		boolean isLongClickable = false;
		try{
			if(element != null){
				isLongClickable = element.getAttribute("longClickable").equalsIgnoreCase("true");
				Report.pass("'" +locator+ "'" + " is longClickable");
			}else{
				Report.info("'" +locator+ "'" + " is NOT longClickable");
			}			 
		}catch(NoSuchElementException e){
			Report.info("'" +locator+ "'" + " is NOT longClickable");
		} return isLongClickable;
	}
	/*public boolean isLongClickable(){
		try{
			WebElement element;
			if(isXpath()) element = Android.driver.findElementByXPath(locator);
			else element = Android.driver.findElementByAndroidUIAutomator(locator);
			return element.getAttribute("longClickable").equalsIgnoreCase("true");
		}catch(NoSuchElementException e){
			return false;
		}
	}*/
	
	
	public boolean isSelected(){
		WebElement element = getElement(locator);
		boolean isSelected = false;
		try{
			if(element != null){
				element.getAttribute("selected").equalsIgnoreCase("true");
				Report.pass("'" +locator+ "'" + " is selected");
			}else{
				Report.info("'" +locator+ "'" + " is NOT selected");
			}			 
		}catch(NoSuchElementException e){
			Report.info("'" +locator+ "'" + " is NOT selected");
		} return isSelected;
	}
	/*public boolean isSelected(){
		try{
			WebElement element;
			if(isXpath()) element = Android.driver.findElementByXPath(locator);
			else element = Android.driver.findElementByAndroidUIAutomator(locator);
			return element.getAttribute("selected").equalsIgnoreCase("true");
		}catch(NoSuchElementException e){
			return false;
		}
	}*/
	
	
	public Point getLocation(){
		WebElement element = getElement(locator);
		Point location = null;
		if(element != null){
			try {
				location = element.getLocation();
				Report.pass("'" +locator+ "'" + " location is found");
			} catch (Exception e) {
				Report.info("'" +locator+ "'" + " location is NOT found");
			}
		}else{
			Report.info("'" +locator+ "'" + " location is NOT found");
		} return location; 
	}
	/*public Point getLocation(){
		WebElement element;
		if(isXpath()) element = Android.driver.findElementByXPath(locator);
		else element = Android.driver.findElementByAndroidUIAutomator(locator);
		return element.getLocation();
	}*/
	
	
	
	public String getText(){
		WebElement element = getElement(locator);
		String text = null;
		if(element != null){
			try {
				text = element.getAttribute("name").trim();
				Report.pass("'" +locator+ "'" + " text is found");
			} catch (Exception e) {
				Report.info("'" +locator+ "'" + " text is NOT found");
			}			
		}else{
			Report.info("'" +locator+ "'" + " text is NOT found");
		} return text; 
	}
	/*public String getText(){
		WebElement element;
		if(isXpath()) element = Android.driver.findElementByXPath(locator);
		else element = Android.driver.findElementByAndroidUIAutomator(locator);
		return element.getAttribute("name");
	}*/
	
	
	public String getResourceId(){
		WebElement element = getElement(locator);
		String resourceId = null;
		if(element != null){
			try {
				resourceId = element.getAttribute("resourceId");
				Report.pass("'" +locator+ "'" + " resourceId is found");
			} catch (Exception e) {
				Report.info("'" +locator+ "'" + " resourceId is NOT found"); 
			}
		}else{
			Report.info("'" +locator+ "'" + " resourceId is NOT found");
		} return resourceId; 
	}
	/*public String getResourceId(){
		WebElement element;
		if(isXpath()) element = Android.driver.findElementByXPath(locator);
		else element = Android.driver.findElementByAndroidUIAutomator(locator);
		return element.getAttribute("resourceId");
	}*/
	
	
	public String getClassName(){
		WebElement element = getElement(locator);
		String className = null;
		if(element != null){
			try {
				className = element.getAttribute("className");
				Report.pass("'" +locator+ "'" + " className is found");
			} catch (Exception e) {
				Report.info("'" +locator+ "'" + " className is NOT found");
			}			
		}else{
			Report.info("'" +locator+ "'" + " className is NOT found");
		} return className; 
	}
	/*public String getClassName(){
		WebElement element;
		if(isXpath()) element = Android.driver.findElementByXPath(locator);
		else element = Android.driver.findElementByAndroidUIAutomator(locator);
		return element.getAttribute("className");
	}*/
	
	public String getContentDes(){
		WebElement element = getElement(locator);
		String desc = null;
		if(element != null){
			try {
				desc = element.getAttribute("contentDesc");
				Report.pass("'" +locator+ "'" + " content description is found");
			} catch (Exception e) {
				Report.info("'" +locator+ "'" + " content description is NOT found");
			}
		}else{
			Report.info("'" +locator+ "'" + " content description is NOT found");
		} return desc; 
	}
	/*public String getContentDes(){
		WebElement element;
		if(isXpath()) element = Android.driver.findElementByXPath(locator);
		else element = Android.driver.findElementByAndroidUIAutomator(locator);
		return element.getAttribute("contentDesc");
	}*/
	
	
	public UiObject clearText(){
		WebElement element = getElement(locator);
		if(element != null){
			try {
				element.clear();
				Report.pass("'" +locator+ "'" + " text is cleared");
			} catch (Exception e) {
				Report.info("'" +locator+ "'" + " text is NOT cleared"); 
			}
		}else{
			Report.info("'" +locator+ "'" + " text is NOT cleared");
		} return this;
	}
	/*public UiObject clearText(){
		if(isXpath()) Android.driver.findElementByXPath(locator).clear();
		else Android.driver.findElementByAndroidUIAutomator(locator).clear();
		return this;
	}*/

	public UiObject typeText(String value){
		WebElement element = getElement(locator);
		if(element != null){
			try {
				element.sendKeys(value);
				Report.pass("text '" +value+"' is entered on the '" +locator+ "' locator");
			} catch (StaleElementReferenceException e) {
				Report.fail("'" +locator+ "' is NOT found and text '" +value+"' is NOT entered on the '");
			}
		}else{
			Report.fail("'" +locator+ "' is NOT found and text '" +value+"' is NOT entered on the '");
		} return this;
	}
	/*public UiObject typeText(String value){
		if(isXpath()) Android.driver.findElementByXPath(locator).sendKeys(value);
		else Android.driver.findElementByAndroidUIAutomator(locator).sendKeys(value);
		return this;
	}*/
	
	
	
	public UiObject click(){
		WebElement element = getElement(locator);
		if(element != null){
			try {
				element.click();
				Report.pass("'" +locator+ "' is clicked");
			} catch (Exception e) {
				Report.fail("'" +locator+ "' is NOT clicked"); 
			}
		}else{
			Report.fail("'" +locator+ "' is NOT clicked");			
		}return this;
	}
	/*public UiObject click(){
		if(isXpath()) {
			Android.driver.findElementByXPath(locator).click();
		}else {
			Android.driver.findElementByAndroidUIAutomator(locator).click();
		}
		return this;
	}*/
	
	
	
	public void backButton(){
		try {
			Android.driver.pressKeyCode(AndroidKeyCode.BACK);
			Report.pass("Andorid BACK button is clicked");
		} catch (Exception e) {
			Report.pass("Andorid BACK button is NOT clicked"); 
		}		
	}
		
	public void horizontalSwipeByPercentage(double startPercentage, double endPercentage, double anchorPercentage) {
		Dimension size = Android.driver.manage().window().getSize();
		int anchor = (int) (size.height * anchorPercentage);
		int startPoint = (int) (size.width * startPercentage);
		int endPoint = (int) (size.width * endPercentage);
 
		new TouchAction(Android.driver).press(point(startPoint, anchor)).waitAction(waitOptions(ofSeconds(2)))
				.moveTo(point(endPoint, anchor)).release().perform();
	}
	
	
	public void swipeLeft(int timeInSeconds) {
		try {
			Dimension size = Android.driver.manage().window().getSize();
			int startPoint = (int) (size.width * 0.99);
			int endPoint = (int) (size.width * 0.15);
			int ScreenPlace =(int) (size.height*0.40);          
			int y=(int)size.height*20;

			TouchAction ts = new TouchAction(Android.driver);
			ts.press(PointOption.point(startPoint,ScreenPlace ))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000*timeInSeconds)))
			.moveTo(PointOption.point(endPoint,ScreenPlace )).release().perform();
			Report.pass("screen swiped to left");
		} catch (Exception e) {
			Report.fail("screen is NOT swiped to left");
		}
	}

	public void swipeRight(int timeduration) {

		Dimension size = Android.driver.manage().window().getSize();
		int startPoint = (int) (size.width * 0.15);
		int endPoint = (int) (size.width * 0.99);
		int ScreenPlace =(int) (size.height*0.40);          
		int y=(int)size.height*20;

		TouchAction ts = new TouchAction(Android.driver);
		ts.press(PointOption.point(startPoint,ScreenPlace ))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		.moveTo(PointOption.point(endPoint,ScreenPlace )).release().perform();
	}
		
	
	/*public UiObject scrollTo(){
		
		if(!locator.contains("text")) throw new RuntimeException("ScrollTo menthod can only be used with text attribute and current locator "+locator+" does not contain any text attribute");
		if(isXpath()) Android.driver.scrollTo(locator.substring(locator.indexOf("@text=\""), locator.indexOf("\"]")).replace("@text=\"", ""));
		else{
			String text;
			if(locator.contains("textContains"))text = locator.substring(locator.indexOf(".textContains(\""), locator.indexOf("\"")).replace(".textContains(\"", "");
			else text = locator.substring(locator.indexOf(".text(\""), locator.indexOf("\")")).replace(".text(\"", "");
			Android.driver.scrollTo(text);
		}
		return this;
	}*/
	
	public UiObject waitToAppear(int seconds){
		Timer timer = new Timer();
		timer.start();
		while(!timer.expired(seconds)) if(exist()) break;
		if(timer.expired(seconds) && !exist()) throw new AssertionError("Element "+locator+" failed to appear within "+seconds+" seconds");
		return this;			
	}
	
	public UiObject waitToDisappear(int seconds){
		Timer timer = new Timer();
		timer.start();
		while(!timer.expired(seconds)) if(!exist()) break;
		if(timer.expired(seconds) && exist()) throw new AssertionError("Element "+locator+" failed to disappear within "+seconds+" seconds");
		return this;			
	}
	
}
