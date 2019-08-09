package base;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import core.Report;

public class ServerManager {

	private static String os;
	private static String ANDROID_HOME;
	private static String ADB_PATH = getAndroidHome()+ File.separator+"platform-tools" + File.separator + "adb";
	
	public static String getAndroidHome(){
		if(ANDROID_HOME == null){
			ANDROID_HOME = System.getenv("ANDROID_HOME");
			if(ANDROID_HOME == null) throw new RuntimeException("Failed to find ANDROID_HOME, make sure environment variable is set");
		}
		return ANDROID_HOME;
	}
	
	public static String getOS(){
		if(os == null) os = System.getenv("os.name");
		return os;
	}
	
	public static boolean isWindows(){
		return getOS().startsWith("Windows");
	}
	
	public static boolean isMac(){
		return getOS().startsWith("Mac");
	}
	
	public static String runCommand(String command){
		String output = null;
		try {
			Scanner scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
			if(scanner.hasNext()) output = scanner.next();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return output;
	}
	
	public static void startAppium() {
		Runtime runtime = Runtime.getRuntime();
		try {
			if(!isAppiumRunnning(4723)){
				Report.info("Starting Appium Server...");
				runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
				Thread.sleep(10000);
				Report.info("Started Appium Server on Port - " + 4723);
			}else{
				Report.info("Appium Server is already running on Port - " + 4723);
			}
		} catch (IOException | InterruptedException e) {
			Report.fail("FAILED to start Appium Server, please check logs for more detials");
		}
	}
	
	public static void stopAppium() {
		Runtime runtime = Runtime.getRuntime();
		try {
			if(isAppiumRunnning(4723)){
				Report.info("Stopping Appium Server...");
				runtime.exec("taskkill /F /IM node.exe");
				runtime.exec("taskkill /F /IM cmd.exe");
				Report.info("Appium Server is Stoped successfuly.");
			}else{
				Report.info("Appium Server is not Started, nothing to stop");
			}
		} catch (IOException e) {
			Report.error("FAILED to stop Appium Server, please check the log for more detail.");
		}
	}
	
	public static boolean isAppiumRunnning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			//If the control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}return isServerRunning;
	}	
	
	public static void startEmulator(String nameOfAVD){
		String emulatorPath = ServerManager.getAndroidHome()+ File.separator  + "tools" + File.separator + "emulator";
		Report.info("Starting emulator '" + nameOfAVD + "' ...");
		String[] aCommand = new String[] { emulatorPath, "-avd", nameOfAVD };
		try {
			Process process = new ProcessBuilder(aCommand).start();
			process.waitFor(40, TimeUnit.SECONDS);
			Report.info("Started Emulator: '"+nameOfAVD+"' successfully!");
		} catch (Exception e) {
			Report.fail("FAILED to start Emulator: '"+nameOfAVD+"', please check log for more details!");
		}
	}
	
	public static void killEmulator() {
		Report.info("Killing emulator...");
		String[] aCommand = new String[] { ADB_PATH, "emu", "kill" };
		try {
			Process process = new ProcessBuilder(aCommand).start();
			process.waitFor(1, TimeUnit.SECONDS);
			Report.info("Emulator is killed successfully!");
		} catch (Exception e) {
			Report.error("FAILED to kill Emulator"+ e);
		}
	}
		
		
}
