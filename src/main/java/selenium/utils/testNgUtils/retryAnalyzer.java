package selenium.utils.testNgUtils;

import io.qameta.allure.Allure;
import org.testng.IRetryAnalyzer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import selenium.base.DriverManager;
import selenium.utils.ConfigReader;

public class retryAnalyzer implements IRetryAnalyzer {
    int currentRetry=0;
    int totalRetryCount= Integer.parseInt(ConfigReader.getProperty("retryCount"));

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(currentRetry<totalRetryCount){

            currentRetry++;
            Allure.addAttachment("Retried",iTestResult.getTestName(), String.valueOf(currentRetry));
            DriverManager.getLogger().info("Retrying test: {} | Attempt: {}", iTestResult.getName(), currentRetry + 1);
            return true;
        }
        return false;
    }
}
