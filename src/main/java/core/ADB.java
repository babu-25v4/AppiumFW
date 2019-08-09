package core;

import java.util.ArrayList;
import java.util.Arrays;
import base.ServerManager;

public class ADB {

	private String ID;

	public ADB(String deviceID){
		ID = deviceID;
	}

	public static String command(String command){
		Report.info("Formating ADB command: "+command);
		if(command.startsWith("adb")) command = command.replace("adb ", ServerManager.getAndroidHome()+"\\platform-tools\\adb ");
		else throw new RuntimeException("This method is designed to run ADB commands only");
		Report.info("Formated ADB command: "+command);
		String output = ServerManager.runCommand(command);
		Report.info("Output of the ADB command: "+output);
		if(output == null) return "";
		else return output;
	}

	public static void startServer(){
		command("adb start-server");
	}

	public static void stopServer(){
		command("adb kill-server");
	}

	public static ArrayList getConnectedDevices(){
		ArrayList devices = new ArrayList();
		String output = command("adb devices");
		for(String line: output.split("\n")){
			line = line.trim();
			if(line.endsWith("device")) devices.add(line.replace("device", "").trim());
		}return devices;
	}

	public String getFourgroundActivity(){
		return command("adb -s "+ID+" shell dumpsys window windows | grep mCurrentFocus");
	}

	public String getAndroidVersionAsString(){
		String output = command("adb -s "+ID+" shell getprop ro.build.version.release");
		if(output.length() == 3) output += ".0";
		return output;
	}

	public int getAndroidVersion(){
		return Integer.parseInt(getAndroidVersionAsString().replaceAll("\\.", ""));
	}

	public ArrayList getInstalledPackages(){
		ArrayList packages = new ArrayList();
		String [] output = command("adb "+ID+" shell pm list packages").split("\n");
		for(String packageID : output) packages.add(packageID.replace("package:", ""));
		return packages;		
	}

	public void openAppActivity(String packageID, String activityID){
		command("adb -s "+ID+" shell am start -c api.android.intent.category.LAUNCHER -a api.android.intent.action.MAIN -n "+packageID+"/"+activityID);
	}

	public void clearAppData(String packageID){
		command("adb -s "+ID+" shell pm clear "+packageID);
	}

	public void forceStopApp(String packageID){
		command("adb -s "+ID+" shell am force-stop "+packageID);
	}

	public void installApp(String apkPath){
		command("adb -s "+ID+" install "+apkPath);
	}

	public void uninstallApp(String packageID){
		command("adb -s "+ID+" uninstall "+packageID);
	}

	public void clearLogBuffer(){
		command("adb -s "+ID+" shell -c");
	}

	public void pushFile(String source, String target){
		command("adb -s "+ID+" push "+source+" "+target);
	}

	public void pullFile(String source, String target){
		command("adb -s "+ID+" pull "+source+" "+target);
	}

	public void deleteFile(String target){
		command("adb -s "+ID+" shell rm "+target);
	}

	public void moveFile(String source, String target){
		command("adb -s "+ID+" shell mv "+source+" "+target);
	}

	public void takeScreenshot(String target){
		command("adb -s "+ID+" shell screencap "+target);
	}

	public void rebootDevice(){
		command("adb -s "+ID+" reboot");
	}

	public String getDeviceModel(){
		return command("adb -s "+ID+" shell getprop ro.product.model");
	}

	public String getDeviceSerialNumber(){
		return command("adb -s "+ID+" shell getprop ro.product.serialno");
	}

	public String getDeviceCarrier(){
		return command("adb -s "+ID+" shell getprop gsm.operator.alpha");
	}

	public ArrayList getLogcatProcesses(){
		String [] output = command("adb -s "+ID+" shell top -n 1 | grep -i 'logcat'").split("\n");
		ArrayList process = new ArrayList();
		for(String line: output){
			process.add(line);
			process.removeAll(Arrays.asList("", null));
		}return process;
	}

	public Object startLogcat(String logID, String grep){
		ArrayList pidBefore = getLogcatProcesses();

		Thread logcat = new Thread(new Runnable() {

			@Override
			public void run() {
				if(grep == null) command("adb -s "+ID+" shell logcat -v threadtime > /sdcard/"+logID+".txt");
				else command("adb -s "+ID+" shell logcat -v threadtime | grep -i '"+grep+"'> /sdcard/"+logID+".txt");				
			}
		});
		logcat.setName(logID);
		logcat.start();
		logcat.interrupt();

		ArrayList pidAfter = getLogcatProcesses();
		Timer timer = new Timer();
		timer.start();
		while(!timer.expired(5)){
			if(pidBefore.size() > 0) pidAfter.removeAll(pidBefore);
			if(pidAfter.size() > 0) break;
			pidAfter = getLogcatProcesses();
		}		
		if(pidAfter.size() == 1) return pidAfter.get(0);
		else if(pidAfter.size() > 1) throw new RuntimeException("Multiple logcat processes were started when only one was expected!");
		else throw new RuntimeException("Failed to start logcat process!");
	}

	public void stopLogcat(Object pid){
		command("adb -s "+ID+" shell kill "+pid);
	}


}
