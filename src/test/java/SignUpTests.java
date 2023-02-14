import additional.api.JSONUser;
import additional.api.UserClient;
import additional.selenium.RandomUser;
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
import pageobjects.SignInPage;
import pageobjects.SignUpPage;

import static additional.selenium.URLs.BASE_URL;
import static org.junit.Assert.assertTrue;


public class SignUpTests {
    private WebDriver driver;
    private FrontPage frontPage;
    private SignInPage signInPage;
    private SignUpPage signUpPage;
    private String token;
    private UserClient userClient;
    private User user;
    RandomUser randomUser;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        frontPage = new FrontPage(driver);
        signInPage = new SignInPage(driver);
        signUpPage = new SignUpPage(driver);
        userClient = new UserClient();

        randomUser = new RandomUser();
    }

    @Test
    @Description("Проверить, что можно зарегистрироваться с корректными даннными")
    public void checkThatUserCanRegister() {
        user = randomUser.createRandomUser(6);
        frontPage.clickAccountButton();
        signInPage.waitForSignInPageToLoad();
        signInPage.clickSignUpLink();
        signUpPage.fillOutUserInfo(user.getName(), user.getEmail(), user.getPassword());
        signUpPage.clickSignUpButton();

        Response response = userClient.logUser(JSONUser.from(user));
        token = userClient.getToken(response);

        assertTrue(signInPage.checkSignUpIsSuccessful());
    }

    @Test
    @Description("Проверить, что нельзя зарегистрироваться, если пароль меньше 6 символов")
    public void checkThatUserCanNotRegisterWithSmallPassword() {
        user = randomUser.createRandomUser(3);
        frontPage.clickAccountButton();
        signInPage.waitForSignInPageToLoad();
        signInPage.clickSignUpLink();
        signUpPage.fillOutUserInfo(user.getName(), user.getEmail(), user.getPassword());

        assertTrue(signUpPage.checkPasswordInputError());
    }

    @After
    public void tearDown() throws IllegalArgumentException {
        try {
            userClient.deleteUser(token);
        } catch (IllegalArgumentException ignored) {}
        driver.quit();
    }
}