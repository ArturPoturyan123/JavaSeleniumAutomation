package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.DriverManager;

public class DriverInit {
    public static WebDriver driver;


    public static WebDriver initDriver() {
        String browser = ConfigReader.get("firefoxBrowser");
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver() {
        DriverManager.q();
        if (driver != null) {
            driver.quit();
            driver = null;

        }
    }
}
