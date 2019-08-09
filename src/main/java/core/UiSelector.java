package core;

public class UiSelector {

	private String locator = "new UiSelector()";
	
	public UiSelector resourceId(String value){			
		locator += ".resourceId(\""+value+"\")";
//		System.out.println("locator: "+locator);
		return this;
	}
	
	public UiSelector resource_id(String value){			
		locator += ".resource-id(\""+value+"\")";
		return this;
	}
	
	public UiSelector className(String value){			
		locator += ".className(\""+value+"\")";
		return this;
	}
	
	public UiSelector classNameMatches(String regx){			
		locator += ".classNameMatches(\""+regx+"\")";
		return this;
	}
	
	public UiSelector text(String value){			
		locator += ".text(\""+value+"\")";
		return this;
	}
	
	public UiSelector textContains(String value){			
		locator += ".textContains(\""+value+"\")";
		return this;
	}
	
	public UiSelector index(int value){			
		locator += ".index("+value+")";
		return this;
	}
	
	public UiSelector clickable(boolean value){			
		locator += ".clickable("+value+")";
		return this;
	}
	
	public UiSelector checked(boolean value){			
		locator += ".checked("+value+")";
		return this;
	}
	
	public UiSelector enabled(boolean value){			
		locator += ".enabled("+value+")";
		return this;
	}
	
	public UiSelector description(String value){			
		locator += ".description(\""+value+"\")";
		return this;
	}
	
	public UiSelector descriptionContains(String value){			
		locator += ".descriptionContains(\""+value+"\")";
		return this;
	}
	
	public UiSelector descriptionMatches(String regx){			
		locator += ".descriptionMatches(\""+regx+"\")";
		return this;
	}
	
	public UiSelector xPath(String xPath){			
		locator = xPath;
		return this;
	}
		
	public UiObject makeUiObject(){
		return new UiObject(locator);
	}
	
	
}
