import additional.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.FrontPage;
import pageObjects.ResetPasswordPage;
import pageObjects.SignInPage;
import pageObjects.SignUpPage;

import static org.junit.Assert.assertTrue;

public class SingInTests {
    private WebDriver driver;
    FrontPage frontPage;
    SignInPage signInPage;
    SignUpPage signUpPage;
    ResetPasswordPage resetPasswordPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        frontPage = new FrontPage(driver);
        signInPage = new SignInPage(driver);
        signUpPage = new SignUpPage(driver);
        resetPasswordPage = new ResetPasswordPage(driver);

        User.getDefaultUser();
    }

    @Test
    @Description("Проверить, что работает вход по кнопке \"Войти в аккаунт на главной\"")
    public void checkThatCanLogInFromFrontPage() {
        driver.get("https://stellarburgers.nomoreparties.site");

        frontPage.clickBottomLogInButton();
        signInPage.waitForSignInPageToLoad();
        signInPage.fillSignInInfo();
        signInPage.clickSignInButton();

        assertTrue(frontPage.checkFrontPageAppears());
    }

    @Test
    @Description("Проверить, что работает вход через кнопку \"Личный кабинет\"")
    public void checkThatCanLogInFromAccountButton() {
        driver.get("https://stellarburgers.nomoreparties.site");

        frontPage.clickAccountButton();
        signInPage.waitForSignInPageToLoad();
        signInPage.fillSignInInfo();
        signInPage.clickSignInButton();

        assertTrue(frontPage.checkFrontPageAppears());
    }

    @Test
    @Description("Проверить, что работает вход через кнопку в форме регистрации")
    public void checkThatCanLogInFromSignUpPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");

        signUpPage.clickSignInButton();
        signInPage.waitForSignInPageToLoad();
        signInPage.fillSignInInfo();
        signInPage.clickSignInButton();

        assertTrue(frontPage.checkFrontPageAppears());
    }

    @Test
    @Description("Проверить, что работает вход через кнопку в форме восстановления пароля")
    public void checkThatCanLogInFromResetPasswordPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");

        resetPasswordPage.clickSignInButton();
        signInPage.waitForSignInPageToLoad();
        signInPage.fillSignInInfo();
        signInPage.clickSignInButton();

        assertTrue(frontPage.checkFrontPageAppears());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
