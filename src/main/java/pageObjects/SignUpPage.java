package pageObjects;

import additional.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SignUpPage {

    private final WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By nameField = By.xpath(".//label[text()='Имя']/..//input");
    private final By emailField = By.xpath(".//label[text()='Email']/..//input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/..//input");
    private final By signUpButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By passwordInputError = By.xpath(".//p[text()='Некорректный пароль']");
    private final By signInButton = By.linkText("Войти");

    public void fillOutName() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(nameField).sendKeys(User.getName());
    }

    public void fillOutEmail() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).sendKeys(User.getEmail());
    }

    public void fillOutPassword() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(User.getPassword() + Keys.TAB);

    }

    public void fillOutUserInfo() {
        fillOutName();
        fillOutEmail();
        fillOutPassword();
    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    public Boolean checkPasswordInputError() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(passwordInputError));
        return driver.findElement(passwordInputError).isDisplayed();
    }

    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }
}