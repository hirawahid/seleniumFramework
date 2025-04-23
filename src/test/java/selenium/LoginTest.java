package selenium;

import io.qameta.allure.Feature;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;
import selenium.base.BaseTest;
import selenium.utils.ConfigReader;
import selenium.utils.selenium.sessionManager;

import java.util.Set;

@Feature("Login Feature")
public class LoginTest extends BaseTest {

    public void forgotLoginFlow(String user){

        loginPage.forgotLogin();
        forgotLoginPage.verifyForgotLoginPage();
        forgotLoginPage.enterUsername(user);
        forgotLoginPage.clickresetPasswordButton();
    }

    public void loginFlow(String user, String pass){

        loginPage.waitForLoginForm();
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
        loginPage.submitForm();
        Set<Cookie> cookieSet=driver.manage().getCookies();
        sessionManager.saveCookies(cookieSet);
    }

    @Test(priority = 2) //retryAnalyzer = retryAnalyzer.class
    public void test_valid_login() {

       String user=ConfigReader.getProperty("username");
       String pass=ConfigReader.getProperty("password");
       loginFlow( user,  pass);
        // Add login logic here
        loginPage.verifySuccess();
    }

    @Test(priority = 1) //retryAnalyzer = retryAnalyzer.class
    public void test_invalid_login() {

        String user=ConfigReader.getProperty("username");
        String pass="123";
        //String pass=ConfigReader.getProperty("password");

        loginFlow( user,  pass);
        // Add login logic here
        loginPage.verifyInvalidLogin();
    }

    @Test(priority = 3, enabled = true)
    public void test_forgot_Login(){

        String user=ConfigReader.getProperty("username");
        forgotLoginFlow(user);
    }





}
