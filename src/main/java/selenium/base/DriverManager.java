package selenium.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<Logger> logger = new ThreadLocal<>();

    public static void initDriver(String browser) {
        try {
            if (browser.equalsIgnoreCase("grid-chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
            } else if (browser.equalsIgnoreCase("grid-firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
            } else if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");

                // Initialize ChromeDriver
                ChromeOptions options = new ChromeOptions();
                // options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                options.addArguments("--headless");
                // options.addArguments("--disable-gpu");

                options.addArguments("--remote-allow-origins=*");
                driver.set(new ChromeDriver(options));
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(true);
                options.addPreference("app.update.auto", false);
                options.addPreference("app.update.enabled", false);
                driver.set(new FirefoxDriver(options));
            } else {
                throw new IllegalArgumentException("Invalid browser: " + browser);
            }
        }catch (MalformedURLException e){
            System.out.println("Error initializing driver "+e.getMessage());
        }
        String logName = browser + "_Thread-" + Thread.currentThread().getId();
        System.setProperty("log.name", logName);
        logger.set(LoggerFactory.getLogger(logName));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWaitTimeout"))));
        getDriver().manage().window().maximize();


    }

    public static WebDriver getDriver() {
        return driver.get();
    }
    public static Logger getLogger() {
        return logger.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }





}

