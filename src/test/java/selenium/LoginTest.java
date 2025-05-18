package selenium;

import io.qameta.allure.Feature;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.BaseTest;
import selenium.base.DriverManager;
import selenium.pages.ForgotLoginPage;
import selenium.pages.LoginPage;
import selenium.stepDefs.LoginSteps;
import selenium.utils.ConfigReader;
import selenium.utils.selenium.sessionManager;

import java.sql.Driver;
import java.util.Set;

@Feature("Login Feature")
public class LoginTest {
    LoginPage loginPage= DriverManager.getPage(LoginPage.class);
    ForgotLoginPage forgotLoginPage= DriverManager.getPage(ForgotLoginPage.class);
    LoginSteps loginSteps=new LoginSteps();

    public void forgotLoginFlow(String user){

        loginPage.forgotLogin();
        forgotLoginPage.verifyForgotLoginPage();
        forgotLoginPage.enterUsername(user);
        forgotLoginPage.clickresetPasswordButton();
    }

    public void loginFlow(String user, String pass){

        loginSteps.waitForLoginForm();
       // loginSteps.enterUserName(2, user);
      //  loginSteps.enterPassword(pass);
        loginSteps.submitForm();
        Set<Cookie> cookieSet=DriverManager.getDriver().manage().getCookies();
        sessionManager.saveCookies(cookieSet);
    }

    @Test(priority = 2, dataProvider = "loginData" ) //retryAnalyzer = retryAnalyzer.class
    public void test_valid_login(String username, String password) {

       String user=username;
       String pass=password;
       loginFlow( user,  pass);
        // Add login logic here
        loginSteps.waitForDashboard();
        loginSteps.verifyDashboardUrl();
        loginSteps.verifyHeading();
        loginSteps.verifyCookieIsSet();
        loginSteps.verifyUserDropDown();
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

@DataProvider(name="loginData")
    public Object[][] loginData(){

        return new Object[][]{
                {"Admin","admin123"}
        };

    }
}
