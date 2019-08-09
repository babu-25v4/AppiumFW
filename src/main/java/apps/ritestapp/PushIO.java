package apps.ritestapp;

import org.openqa.selenium.NoSuchElementException;
import core.Report;
import core.UiSelector;

public class PushIO {
	
	public void setUserID(String userID){
		
	}
	
	public void triggerConversionEvents(){		
		new UiSelector().resourceId("com.pushio.testharness12:id/iap").makeUiObject().click();
		new UiSelector().makeUiObject().backButton();
		new UiSelector().text("SOCIAL").makeUiObject().click();
		new UiSelector().makeUiObject().backButton();
		new UiSelector().resourceId("com.pushio.testharness12:id/premium").makeUiObject().click();
		new UiSelector().makeUiObject().backButton();
		new UiSelector().text("OTHER").makeUiObject().click();
		new UiSelector().makeUiObject().backButton();		
	}
	
	public void navigateToPushAppTab(String tabName){		
		try {
			boolean elementPresent = new UiSelector().text(tabName).makeUiObject().exist();
			if(!elementPresent){
				while(true){
					new UiSelector().makeUiObject().swipeLeft(2);
					elementPresent = new UiSelector().text(tabName).makeUiObject().exist();
					if(elementPresent){
						Report.pass("Push Tab '"+tabName+"' is present");
						break;
					}
				}
			}new UiSelector().text(tabName).makeUiObject().click();			
		} catch (NoSuchElementException e) {
			Report.fail("Push Tab '"+tabName+"' is NOT found ");
		}
	}
	
	public void settingsTabEnableCategories(){		
			new UiSelector().resourceId("com.pushio.testharness12:id/cb1").makeUiObject().waitToAppear(3).click();
			new UiSelector().resourceId("com.pushio.testharness12:id/cb2").makeUiObject().waitToAppear(3).click();			
	}
		
	
}
