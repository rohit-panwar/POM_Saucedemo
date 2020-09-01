package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.base.TestBase;
import com.qa.utils.TestUtils;

public class TestListener extends TestBase implements ITestListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		String dt;
		try {
			dt = TestUtils.getCurrentDateTime();
			TestUtils.takeScreenShot(dt + "_" + result.getMethod().getMethodName());
			testInfo.addScreenCaptureFromPath(
					"C:\\Users\\rohit_panwar\\eclipse-workspace\\SeleniumFrameWorkUsingJava\\src\\test\\resources\\Error\\"
							+ dt + "_" + result.getMethod().getMethodName() + ".jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}
}
