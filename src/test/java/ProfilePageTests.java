import additional.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.FrontPage;
import pageObjects.ProfilePage;
import pageObjects.SignInPage;

import static org.junit.Assert.assertTrue;

public class ProfilePageTests {
    WebDriver driver;
    SignInPage signInPage;
    FrontPage frontPage;
    ProfilePage profilePage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/login");

        User.getDefaultUser();
        signInPage = new SignInPage(driver);
        frontPage = new FrontPage(driver);
        profilePage = new ProfilePage(driver);

        signInPage.fillSignInInfo();
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
        driver.quit();
    }
}