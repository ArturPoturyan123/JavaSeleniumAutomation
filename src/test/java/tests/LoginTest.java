package tests;


import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {
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
