import additional.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.FrontPage;
import pageObjects.SignInPage;
import pageObjects.SignUpPage;

import static org.junit.Assert.assertTrue;


public class SignUpTests {
    private WebDriver driver;
    private FrontPage frontPage;
    private SignInPage signInPage;
    private SignUpPage signUpPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        frontPage = new FrontPage(driver);
        signInPage = new SignInPage(driver);
        signUpPage = new SignUpPage(driver);
    }

    @Test
    @Description("Проверить, что можно зарегистрироваться с корректными даннными")
    public void checkThatUserCanRegister() {
        User.getRandomUser(6);
        frontPage.clickAccountButton();
        signInPage.waitForSignInPageToLoad();
        signInPage.clickSignUpLink();
        signUpPage.fillOutUserInfo();
        signUpPage.clickSignUpButton();

        assertTrue(signInPage.checkSignUpIsSuccessful());
    }

    @Test
    @Description("Проверить, что нельзя зарегистрироваться, если пароль меньше 6 символов")
    public void checkThatUserCanNotRegisterWithSmallPassword() {
        User.getRandomUser(3);
        frontPage.clickAccountButton();
        signInPage.waitForSignInPageToLoad();
        signInPage.clickSignUpLink();
        signUpPage.fillOutUserInfo();

        assertTrue(signUpPage.checkPasswordInputError());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
