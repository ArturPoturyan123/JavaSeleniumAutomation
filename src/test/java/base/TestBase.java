package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverManager;

public class TestBase {
    protected WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
