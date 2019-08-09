package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;


/**
 * Listener for all the test class.
 * @author bmunegow
 *
 */
public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		Reporter.log("TEST : " + result.getName() + " STARTED.", true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		Reporter.log("TEST : " + result.getName() + " PASSED.", true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		Reporter.log("TEST : " + result.getName() + " FAILED.", true);
		String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
		if (browser == null || browser.isEmpty()) {
//			browser = CommonUtils.getProperty("browserType");
		}
//		DriverUtils.writeScreenshotToFile(Driver.getDriverInstance(browser), result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		Reporter.log("TEST : " + result.getName() + " SKIPPED.");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
}
