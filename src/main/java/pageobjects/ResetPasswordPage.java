package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private WebDriver driver;

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By signInButton = By.linkText("Войти");

    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }
}