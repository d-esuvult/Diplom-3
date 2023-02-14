package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By headerLogo = By.className("AppHeader_header__logo__2D0X2");
    private final By profileLink = By.linkText("Профиль");
    private final By logOutButton = By.xpath(".//button[text()='Выход']");

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickHeaderLogo() {
        driver.findElement(headerLogo).click();
    }

    public void waitForProfilePageToLoad() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(profileLink));
    }

    public Boolean checkThatProfilePageLoaded() {
        return driver.findElement(profileLink).isDisplayed();
    }

    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }
}