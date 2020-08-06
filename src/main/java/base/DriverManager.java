package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.openqa.selenium.remote.DesiredCapabilities;
import core.ADB;
import core.MyLogger;
import core.Report;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class DriverManager {

	private static HashMap<String, URL> host;
	private static String unlockPackage = "io.appium.unlock";
	
	private static DesiredCapabilities getCapsForApp(String appPackage, String appActivity){		
		MyLogger.log.debug("Creating Capabilities: ");
		Report.info("Setting Capabilities. ");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
		caps.setCapability("autoGrantPermissions", true);
		caps.setCapability("appPackage", appPackage);
		caps.setCapability("appActivity", appActivity);
		Report.info("Done Setting Capabilities. ");
		return caps;
	}
	
	private static URL host(String deviceID) throws MalformedURLException{
		if(host == null){
			host = new HashMap<>();
			host.put("emulator-5554", new URL("http://127.0.0.1:4723/wd/hub"));
		}return host.get(deviceID);
	}

	private static ArrayList<String> getAvaliableDevices(){
		MyLogger.log.debug("Checking for available devices");
		ArrayList<String> availableDevices = new ArrayList<>();
		ArrayList connectedDevices = ADB.getConnectedDevices();
		for(Object connectedDevice: connectedDevices){
			String device = connectedDevice.toString();
			ArrayList apps = new ADB(device).getInstalledPackages();
			if(apps.contains(unlockPackage)) availableDevices.add(device);
			else MyLogger.log.debug("Device "+device+" has "+unlockPackage+" installed, assuming it is under testing");
		}
		if(availableDevices.size() == 0) throw new RuntimeException("Not a single devie is available for testing at this time");
		return availableDevices;
	}

	public static void createDriver(){
		try{
			Report.info("Trying to create new Android Driver...");
			Android.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), getCapsForApp("com.pushio.testharness12", "com.pushio.testharness.MainActivity"));
			Report.info("Android Driver is created for the device: " +ADB.getConnectedDevices().get(0).toString());
//			Android.adb = new ADB(ADB.getConnectedDevices().get(0).toString());
		}catch (MalformedURLException e) {
			Report.fail("FAILED to create Android Driver, please check the logs for more details: "+e);							
		}catch (Exception e) {
			Report.fail("FAILED to create Android Driver, please check the logs for more details: "+e);							
		}
	}

	public static void killDriver(){
		if(Android.driver != null){
			try {
				Report.info("Killing Android Driver...");
				Android.driver.quit();
				Report.info("Android Driver is killed successfuly");
			} catch (Exception e) {
				Report.info("FAILED to kill Android Driver, please check log for more details");
			}			
		}else Report.info("Android driver is not initialized, nothing to kill");
	}
}
