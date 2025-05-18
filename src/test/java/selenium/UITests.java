package selenium;

import io.qameta.allure.Feature;
import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.base.BaseTest;
import selenium.base.DriverManager;
import selenium.pages.AdminPage;
import selenium.pages.DashboardPage;
import selenium.pages.UpgradePage;
import selenium.utils.selenium.sessionManager;
import selenium.utils.testNgUtils.retryAnalyzer;

import java.util.Iterator;
import java.util.Set;

@Feature("Dashboard Feature")
public class UITests extends BaseTest {
    Logger logger= DriverManager.getLogger();
    DashboardPage dashboard=DriverManager.getPage(DashboardPage.class);

    @BeforeMethod
    public void dashboadSetup(){
        logger.info("running before method");
        Set<Cookie> session=sessionManager.getCookies();
   //     if(session!=null && !session.isEmpty()) {
   //         logger.info("cookie found: attempting to add cookies to new driver");
//            // STEP 1: Go to minimal root page (not login page) - commented as orange hrm does not support cookie nly session
//            driver.get("https://opensource-demo.orangehrmlive.com");
//            Iterator<Cookie> cookie = session.iterator();
//            while (cookie.hasNext()) {
//                driver.manage().addCookie(cookie.next());
//            }
//            driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
//        }
//        else{
            logger.info("Logging In...");
            loginFlow();
    //    }
    }

    @Test
    public void test_upgradePage(){
        logger.info("test_upgradePage");
        AdminPage ap=dashboard.goToAdminPage();
        ap.verifyTable();
        Assert.fail();

    }

    @Test
    public void test_AdminPage(){
        logger.info("test_Admin page");
        UpgradePage up=dashboard.clickUpgradeButton();
        up.verifyUpgradePage();
        Assert.fail();


    }

    @Test
    public void test_Buzz(){
        logger.info("test_buzz");
        dashboard.scrollToBuzzItem();
    }

    @Test
    public void test_EmployeeAlert(){
        logger.info("test_employee alert");
        dashboard.clickEmployeeSetting();
    }

    @AfterMethod
    public void afterMethod(){
     //   driver.close();
      //  driver.switchTo().window(dashboardWindow);
       // dashboard.switchToDashboard();
    }

}
