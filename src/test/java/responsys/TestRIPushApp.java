package responsys;
import java.net.MalformedURLException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import base.DriverManager;
import base.ServerManager;
import core.UiSelector;
import impl.RiTestApp;

public class TestRIPushApp {
	
	RiTestApp  riApp;
	
	public TestRIPushApp() {
		riApp = new RiTestApp();
	}
	
	@BeforeSuite
	public void startUp(){
		ServerManager.startAppium();
		ServerManager.startEmulator("Demo1");
		DriverManager.createDriver();
	}
	
	@Test
	public void testRIApp(){

		riApp.generatePushConversionEvents();
		new UiSelector().text("PUSH").makeUiObject().exist();
//		new UiSelector().makeUiObject().swipeLeft(2000);
//		new UiSelector().text("DEVICE").makeUiObject().click();
//		new UiSelector().text("PREFS").makeUiObject().click();
		riApp.navigateToPushAppTab("EVENTS");
		riApp.navigateToPushAppTab("SETTINGS");	
		riApp.settingsTabEnableCategories();
	}
	
	
	
	@AfterSuite
	public void tearDown(){
		DriverManager.killDriver();
		ServerManager.killEmulator();
		ServerManager.stopAppium();
	}
	
}
