package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;



public class DashboardPage {

    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
//        PageFactory.initElements(DriverManager.getDriver(), this);
    }


    @FindBy(xpath = "//div[starts-with(normalize-space(text()), 'All Accounts')]")
    WebElement allAccountsText;

    public boolean isAllAccountVisible() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(allAccountsText));
            return allAccountsText.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    public String getAllAccountsTitle() {

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(allAccountsText));
        String fullText = allAccountsText.getText();
        if (fullText.contains("All Accounts")) {


            return "All Accounts";
        } else {
            return fullText;
        }    }

}
