import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.annotations.*;
import selenium.base.DriverManager;
import selenium.pages.AdminPage;
import selenium.pages.DashboardPage;
import selenium.pages.ForgotLoginPage;
import selenium.pages.LoginPage;
import selenium.utils.ConfigReader;

import java.time.Duration;

@CucumberOptions(
        features = "src/test/resources/features",  // Path to your feature files
        glue = {"selenium/stepDefs","selenium.utils" }, // Package containing your step definition classes
        plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber-report.json"},  // Reporting and output plugins
        tags = "not @Ignore"
//dryRun=true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests{

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    public String baseUrl;
    public static LoginPage loginPage;
    public ForgotLoginPage forgotLoginPage;
    public DashboardPage dashboard;
    public AdminPage adminPage;
    protected Logger logger= DriverManager.getLogger();

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("firefox") String browser) {
        DriverManager.initDriver(browser);
        logger=DriverManager.getLogger();
        logger.info("running before test");
        driver = DriverManager.getDriver();
        webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(10));
        baseUrl = ConfigReader.getProperty("baseUrl");
        logger.info("🔹 Running login test with base URL: " + baseUrl);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        forgotLoginPage= new ForgotLoginPage(driver);
        dashboard=new DashboardPage(driver);
        adminPage= new AdminPage(driver);


    }


    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterClass
    public void tearDown() {
        logger.info("cucumber after class");

        // DriverManager.quitDriver();
    }

    @AfterMethod
    public void cleanupSession() {
        logger.info("after test");
        attachScreenshot();
       // DriverManager.quitDriver();// Remove session after all test classes in a test
    }


    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot() {
        logger.info("attaching screenshot");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
