package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverInit;

public class BaseTest {
    protected WebDriver driver;


    @BeforeMethod
    public void setUp() {
        driver = DriverInit.initDriver();
        driver.get(ConfigReader.get("baseUrl"));
    }


    @AfterMethod
    public void tearDown() {
        DriverInit.quitDriver();
    }
}
