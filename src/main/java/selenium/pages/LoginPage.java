package selenium.pages;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import selenium.base.BasePage;


@Listeners({AllureTestNg.class})
public class LoginPage extends BasePage {

    private By username = By.name("username");
    private By password = By.name("password");
    private By loginForm = By.cssSelector("form.oxd-form");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By loginError=By.cssSelector("div.oxd-alert-content.oxd-alert-content--error");

    private By forgotLogin=By.cssSelector(".orangehrm-login-forgot");

    private By heading=By.cssSelector("h6");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @Override
    public String getHeadingText(){
        return getElementText(driver.findElement(heading));
    }

    @Given("I wait for the login form to be available")
    public void waitForLoginForm() {
        waitForElementVisibility(loginForm);
    }

    @Step("Wait for the dashboard page to be loaded")
    public void waitForURLToContainDashboard() {
        waitForUrlToContain("dashboard");
    }

    @Step("Enter username `{0}`")
    public void enterUsername(String user) {
        enterText(username, user);
    }

    @Step("Enter password `{0}`")
    public void enterPassword(String pass) {
        enterText(password, pass);
    }

    @Step("submit login form")
    public void submitForm() {
        clickElement(loginButton);
    }

    @Step("Click forgot login")
    public void forgotLogin() {
        clickElement(forgotLogin);
    }

    public void waitForHeading() {
        waitForElementVisibility(By.xpath("//h6"));
    }

    @Step("Verify user dropdown")
    public void verifyUserDropDown() {
//        waitForURLToContainDashboard();
//        verifyDashboardUrl();
//        verifyCookie();
//        verifyHeading();
        super.verifyUserDropDown(heading);
    }

    @Step("Verify invalid login ")
    public void verifyInvalidLogin() {

        String currentUrl=driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"),"Login url not loaded");
        Assert.assertFalse(currentUrl.contains("dashboard"));
        WebElement error= waitForElementVisibility(loginError);
        Assert.assertTrue(error.isDisplayed(),"invalid login error not found");
        Assert.assertTrue(error.findElement(By.cssSelector("p")).getText().contains("Invalid credentials"));
        Assert.assertEquals(driver.findElement(By.cssSelector("h5")).getText(),"Login");


    }

    @Step("Verify the URL contains 'dashboard'")
    public void verifyDashboardUrl() {
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "URL doesn't contain 'dashboard'");
    }
    @Step("Verify the user cookie is set")
    public void verifyCookie() {
        Assert.assertNotNull(getCookie("orangehrm"), "Cookie 'orangehrm' is null");
    }

    @Step("Verify the heading on the dashboard")
    @Then("I verify the heading")
    public void verifyHeading() {
        waitForHeading();
        Assert.assertEquals(getHeadingText(), "Dashboard", "Heading text is incorrect");
    }

}
