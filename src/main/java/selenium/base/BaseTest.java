package selenium.base;

import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import selenium.pages.LoginPage;
import selenium.utils.ConfigReader;
import selenium.utils.selenium.sessionManager;

import java.util.Set;

@Listeners({AllureTestNg.class})
public class BaseTest {

    public void loginFlow(){
        String user=ConfigReader.getProperty("username");
        String pass=ConfigReader.getProperty("password");
        LoginPage loginPage=DriverManager.getPage(LoginPage.class);
        loginPage.waitForLoginForm();
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
        loginPage.submitForm();
        Set<Cookie> cookieSet=DriverManager.getDriver().manage().getCookies();
        sessionManager.saveCookies(cookieSet);
    }
}
