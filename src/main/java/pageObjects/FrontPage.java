package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrontPage {
    private final WebDriver driver;

    public FrontPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By accountButton = By.xpath(".//a[@href='/account']");
    private final By bottomLogInButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By frontPageText = By.xpath(".//h1[text()='Соберите бургер']");
    private final By bunsPanel = By.xpath(".//span[text()='Булки']/..");
    private final By saucePanel = By.xpath(".//span[text()='Соусы']/..");
    private final By mainPanel = By.xpath(".//span[text()='Начинки']/..");

    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    public void clickBottomLogInButton() {
        driver.findElement(bottomLogInButton).click();
    }

    public void waitForFrontPageToLoad() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(frontPageText));
    }

    public Boolean checkFrontPageAppears() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(frontPageText));
        return driver.findElement(frontPageText).isDisplayed();
    }

    public void clickBunsPanel() {
        driver.findElement(bunsPanel).click();
    }

    public void clickSaucePanel() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(saucePanel));
        driver.findElement(saucePanel).click();
    }

    public void clickMainPanel() {
        driver.findElement(mainPanel).click();
    }

    public Boolean checkThatBunPanelIsSelected() {
        return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.attributeContains(bunsPanel, "class", "current"));
    }

    public Boolean checkThatSaucePanelIsSelected() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeContains(saucePanel, "class", "current"));
    }

    public Boolean checkThatMainPanelIsSelected() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeContains(mainPanel, "class", "current"));
    }
}