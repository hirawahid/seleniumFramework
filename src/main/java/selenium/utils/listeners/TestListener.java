package selenium.utils.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import selenium.base.BaseTest;
import selenium.base.DriverManager;
import selenium.utils.selenium.ScreenShotUtility;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        DriverManager.getLogger().info("Taking screenshot: on testFailure event ");
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver(); // Get WebDriver from BaseTest

        if (driver != null) {
            ScreenShotUtility.takeScreenShot(driver, result.getName());
        }    }
}
