package tests;


import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;


    @BeforeMethod
    public void initPage() {

        loginPage = new LoginPage(driver);

    }

    @Test
    public void validLoginTest() {
        loginPage.enterEmail("arthurp@doublecoconut.com");
        loginPage.enterPassword("123456");
        loginPage.clickSubmit();
    }


}
