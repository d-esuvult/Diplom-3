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
import pageobjects.ProfilePage;
import pageobjects.SignInPage;

import static additional.selenium.URLs.LOGIN;
import static org.junit.Assert.assertTrue;

public class ProfilePageTests {
    WebDriver driver;
    SignInPage signInPage;
    FrontPage frontPage;
    ProfilePage profilePage;
    UserClient userClient;
    JSONUser jsonUser;
    String token;
    User user;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(LOGIN);

        signInPage = new SignInPage(driver);
        frontPage = new FrontPage(driver);
        profilePage = new ProfilePage(driver);

        userClient = new UserClient();
        jsonUser = UserBuilder.createRandomUser();
        Response response = userClient.createNewUser(jsonUser);
        token = userClient.getToken(response);

        user = User.from(jsonUser);

        signInPage.fillSignInInfo(user.getEmail(), user.getPassword());
        signInPage.clickSignInButton();
        frontPage.waitForFrontPageToLoad();
    }

    @Test
    @Description("Проверить, что работает переход в личный кабинет по клику на кнопку \"Личный кабинет\"")
    public void checkThatLoggedInUserCanAccessProfilePage() {
        frontPage.clickAccountButton();
        profilePage.waitForProfilePageToLoad();

        assertTrue(profilePage.checkThatProfilePageLoaded());
    }

    @Test
    @Description("Проверить, что работает переход из личного кабинета в конструктор по клику на кнопку \"Конструктор\"")
    public void checkThatLoggedInUserCanAccessConstructorPageByClickingConstructorButton() {
        frontPage.clickAccountButton();
        profilePage.waitForProfilePageToLoad();
        profilePage.clickConstructorButton();

        assertTrue(frontPage.checkFrontPageAppears());
    }

    @Test
    @Description("Проверить, что работает переход из личного кабинета в конструктор по клику на лого")
    public void checkThatLoggedInUserCanAccessConstructorPageByClickingLogo() {
        frontPage.clickAccountButton();
        profilePage.waitForProfilePageToLoad();
        profilePage.clickHeaderLogo();

        assertTrue(frontPage.checkFrontPageAppears());
    }

    @Test
    @Description("Проверить, что работает выход по кнопке \"Выход\" в личном кабинете")
    public void checkThatLoggedInUserCanLogOut() {
        frontPage.clickAccountButton();
        profilePage.waitForProfilePageToLoad();
        profilePage.clickLogOutButton();
        signInPage.waitForSignInPageToLoad();

        assertTrue(signInPage.checkThatSignInPageAppeared());
    }

    @After
    public void tearDown() {
        userClient.deleteUser(token);
        driver.quit();
    }
}