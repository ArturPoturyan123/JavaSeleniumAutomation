package tests;


import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends TestBase {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;


    @BeforeMethod
    public void initPage() {


        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();


    }

    @Test
    public void validLoginTest() {
        loginPage.enterEmail("arthurp@doublecoconut.com");
        loginPage.enterPassword("123456");
        loginPage.clickSubmit();
        Assert.assertTrue(dashboardPage.isAllAccountVisible(), "All Accounts text does not match!");



    }
}
