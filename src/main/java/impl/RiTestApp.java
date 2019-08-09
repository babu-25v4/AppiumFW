package impl;

import apps.ritestapp.PushIO;

public class RiTestApp {
	
	PushIO pushio = new PushIO();

	public void generatePushConversionEvents(){		
		pushio.triggerConversionEvents();		
	}
	
	public void navigateToPushAppTab(String tabName){
		pushio.navigateToPushAppTab(tabName);
	}
	
	public void settingsTabEnableCategories(){
		pushio.settingsTabEnableCategories();
	}
	
}
