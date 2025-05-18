package selenium.stepDefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DocStringType;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import selenium.base.BaseTest;
import selenium.base.DriverManager;
import selenium.pages.LoginPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps() {
        this.loginPage = DriverManager.getPage(LoginPage.class);  // ✅ Fetch ready object
    }

    @Given("I am at login page")
    public void atLoginPage(){
        //do assert on login page
    }
    @Given("I wait for the login form to be available")
    public void waitForLoginForm() {
        loginPage.waitForLoginForm();
    }

    @When("I enter username from example {string}")
    public void enterUserName(String username) {
            loginPage.enterUsername(username);
    }

    @When("I enter username")
    public void enterUserName() {
        loginPage.enterUsername("admin");
    }

    @And("I enter password")
    public void enterPassword() {
            loginPage.enterPassword("admin123");
    }

    @And("I enter password from example {}")
    public void enterPassword(String password) {
        loginPage.enterPassword("password");
    }

    @When("I enter username though data table")
    public void enterUserName1(DataTable dt) {
        List<String> a=dt.asList();
        for(String username: a)
        loginPage.enterUsername(username);
    }

    @And("I enter password through datatable")
    public void enterPassword1(DataTable dt) {
        List<String> a=dt.asList();
        for(String password: a)
        loginPage.enterPassword(password);
    }

    @And("I click submit button")
    public void submitForm() {
        loginPage.submitForm();
    }

    @Then("I wait for dashboard to be loaded")
    public void waitForDashboard() {
        loginPage.waitForURLToContainDashboard();
    }

    @Then("I verify the dashboard URL")
    public void verifyDashboardUrl() {
        loginPage.verifyDashboardUrl();
    }

    @Then("I verify the cookie is set")
    public void verifyCookieIsSet() {
        loginPage.verifyCookie();
    }

    @Then("I verify the heading")
    public void verifyHeading() {
        loginPage.verifyHeading();
    }

    @Then("I verify User DropDown")
    public void verifyUserDropDown() {
        loginPage.verifyUserDropDown();
    }

    @Then("I verify that page is not loged in and proper error message is displayed")
    public void verifyInvalidLogin() {
        loginPage.verifyInvalidLogin();
    }

    @And("I add following items to cart:")
    public void iAddFollowingItemsToCart(List<Map<String,String>> dt) {
        for(Map<String,String> map : dt) {
            System.out.println(map.get("items"));
            System.out.println(map.get("count"));
        }
    }

    @And("I add following items to cart as List:")
    public void iAddFollowingItemsToCartAsList(DataTable dt) {

        List<String> list=dt.asList();
        for(String li: list){
            System.out.println(li);
        }
    }

    @And("I add following items to cart as map:")
    public void iAddFollowingItemsToCartAsMap(List<Map<String, String>> dt) {
        for(Map<String, String> row: dt){
            System.out.println(row.get("item"));
            System.out.println(row.get("count"));
            System.out.println(row.get("type"));
        }
    }

    @And("I add following items to cart as list of list of string:")
    public void iAddFollowingItemsToCartAsListOfListOfString(List<List<String>> dt) {
        for(List<String> row: dt)
            for(String s: row)
                System.out.println(s);
    }

    @And("I add following items to cart as single map:")
    public void iAddFollowingItemsToCartAsSingleMap(Map<String,String> map) {
        System.out.println(map.get("fruit"));
        System.out.println(map.get("count"));
    }

    @And("I add following items to cart as map of list:")
    public void iAddFollowingItemsToCartAsMapOfList(Map<String,List<String>> dt) {
     //   throw new PendingException();
//        System.out.println(dt.get("fruit"));
//        System.out.println(dt.get("count"));

    }
    @DocStringType
    public JSONObject jsonObject(String json) {
        JSONObject p=new JSONObject(json);
        return p;
    }


    @And("I give the route:")
        public void iGiveTheRoute(JSONObject json) {
        System.out.println(json.get("origin"));
    }
}
