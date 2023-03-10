package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By accountPage = By.xpath(".//h2[text()='Вход']");
    private final By signUpLink = By.linkText("Зарегистрироваться");
    private final By emailField = By.xpath(".//label[text()='Email']/../input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    private final By signInButton = By.xpath(".//button[text()='Войти']");


    public void waitForSignInPageToLoad() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(accountPage));
    }

    public void clickSignUpLink() {
        driver.findElement(signUpLink).click();
    }

    public Boolean checkSignUpIsSuccessful() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(accountPage));
        return driver.findElement(accountPage).isDisplayed();
    }

    public void fillOutEmail(String email) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).sendKeys(email);
    }

    public void fillOutPassword(String password) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }

    public void fillSignInInfo(String email, String password) {
        fillOutEmail(email);
        fillOutPassword(password);
    }

    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    public Boolean checkThatSignInPageAppeared() {
        return driver.findElement(accountPage).isDisplayed();
    }
}