import additional.api.JSONUser;
import additional.api.UserBuilder;
import additional.api.UserClient;
import additional.selenium.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.FrontPage;
import pageobjects.ResetPasswordPage;
import pageobjects.SignInPage;
import pageobjects.SignUpPage;

import static additional.selenium.URLs.*;
import static org.junit.Assert.assertTrue;

public class SingInTests {
    private WebDriver driver;
    FrontPage frontPage;
    SignInPage signInPage;
    SignUpPage signUpPage;
    ResetPasswordPage resetPasswordPage;
    UserClient userClient;
    JSONUser jsonUser;
    String token;
    User user;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        frontPage = new FrontPage(driver);
        signInPage = new SignInPage(driver);
        signUpPage = new SignUpPage(driver);
        resetPasswordPage = new ResetPasswordPage(driver);

        userClient = new UserClient();
        jsonUser = UserBuilder.createRandomUser();
        Response response = userClient.createNewUser(jsonUser);
        token = userClient.getToken(response);

        user = User.from(jsonUser);
    }

    @Test
    @Description("Проверить, что работает вход по кнопке \"Войти в аккаунт на главной\"")
    public void checkThatCanLogInFromFrontPage() {
        driver.get(BASE_URL);

        frontPage.clickBottomLogInButton();
        signInPage.waitForSignInPageToLoad();
        signInPage.fillSignInInfo(user.getEmail(), user.getPassword());
        signInPage.clickSignInButton();

        assertTrue(frontPage.checkFrontPageAppears());
    }

    @Test
    @Description("Проверить, что работает вход через кнопку \"Личный кабинет\"")
    public void checkThatCanLogInFromAccountButton() {
        driver.get(BASE_URL);

        frontPage.clickAccountButton();
        signInPage.waitForSignInPageToLoad();
        signInPage.fillSignInInfo(user.getEmail(), user.getPassword());
        signInPage.clickSignInButton();

        assertTrue(frontPage.checkFrontPageAppears());
    }

    @Test
    @Description("Проверить, что работает вход через кнопку в форме регистрации")
    public void checkThatCanLogInFromSignUpPage() {
        driver.get(REGISTER);

        signUpPage.clickSignInButton();
        signInPage.waitForSignInPageToLoad();
        signInPage.fillSignInInfo(user.getEmail(), user.getPassword());
        signInPage.clickSignInButton();

        assertTrue(frontPage.checkFrontPageAppears());
    }

    @Test
    @Description("Проверить, что работает вход через кнопку в форме восстановления пароля")
    public void checkThatCanLogInFromResetPasswordPage() {
        driver.get(RESET_PASSWORD);

        resetPasswordPage.clickSignInButton();
        signInPage.waitForSignInPageToLoad();
        signInPage.fillSignInInfo(user.getEmail(), user.getPassword());
        signInPage.clickSignInButton();

        assertTrue(frontPage.checkFrontPageAppears());
    }

    @After
    public void tearDown() {
        userClient.deleteUser(token);
        driver.quit();
    }
}
