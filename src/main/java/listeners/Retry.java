package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int counter = 0;
	int retryCounter = 1;

	public boolean retry(ITestResult result) {
		if (counter < retryCounter) {
			counter++;
			return true;
		}
		return false;
	}

}
