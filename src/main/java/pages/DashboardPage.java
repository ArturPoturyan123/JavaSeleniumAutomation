package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.DriverInit.driver;

public class DashboardPage {


    public DashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[starts-with(normalize-space(text()), 'All Accounts')]")
    WebElement allAccountsText;

    public boolean isAllAccountVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(allAccountsText));
            return allAccountsText.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    public String getAllAccountsTitle() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(allAccountsText));
        String fullText = allAccountsText.getText();
        if (fullText.contains("All Accounts")) {


            return "All Accounts";
        } else {
            return fullText;
        }    }

}
