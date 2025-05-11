package selenium.base;

import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.annotations.*;
import selenium.pages.AdminPage;
import selenium.pages.DashboardPage;
import selenium.pages.ForgotLoginPage;
import selenium.pages.LoginPage;
import selenium.utils.ConfigReader;
import selenium.utils.selenium.sessionManager;

import java.time.Duration;
import java.util.Set;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    public String baseUrl;
    public LoginPage loginPage;
    public ForgotLoginPage forgotLoginPage;
    public DashboardPage dashboard;
    public AdminPage adminPage;
    protected Logger logger;


    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("firefox") String browser) {

        DriverManager.initDriver(browser);
        logger = DriverManager.getLogger();
        logger.info("running before method");
        driver = DriverManager.getDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        baseUrl = ConfigReader.getProperty("baseUrl");
        logger.info("🔹 Running login test with base URL: " + baseUrl);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        forgotLoginPage = new ForgotLoginPage(driver);
        dashboard = new DashboardPage(driver);
        adminPage = new AdminPage(driver);


    }

    public void loginFlow() {
        String user = ConfigReader.getProperty("username");
        String pass = ConfigReader.getProperty("password");
        loginPage.waitForLoginForm();
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
        loginPage.submitForm();
        Set<Cookie> cookieSet = driver.manage().getCookies();
        sessionManager.saveCookies(cookieSet);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterClass
    public void tearDown() {
        logger.info("after class");

        // DriverManager.quitDriver();
    }

    @AfterMethod
    public void cleanupSession() {
        logger.info("after test");
        attachScreenshot();
        DriverManager.quitDriver();// Remove session after all test classes in a test
    }


    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot() {
        logger.info("attaching screenshot");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
