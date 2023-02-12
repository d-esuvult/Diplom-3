import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.FrontPage;

import static org.junit.Assert.assertTrue;

public class ConstructorPageTests {
    WebDriver driver;
    FrontPage frontPage;
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        frontPage = new FrontPage(driver);
    }

    @Test
    @Description("Проверить, что работает переход к разделу \"Булки\"")
    public void checkThatBunPanelCanBeSelected() {
        frontPage.clickMainPanel();
        frontPage.clickBunsPanel();

        assertTrue(frontPage.checkThatBunPanelIsSelected());
    }
    @Test
    @Description("Проверить, что работает переход к разделу \"Соусы\"")
    public void checkThatSaucePanelCanBeSelected(){
        frontPage.clickSaucePanel();

        assertTrue(frontPage.checkThatSaucePanelIsSelected());
    }
    @Test
    @Description("Проверить, что работает переход к разделу \"Начинки\"")
    public void checkThatMainPanelCanBeSelected() {
        frontPage.clickMainPanel();

        assertTrue(frontPage.checkThatMainPanelIsSelected());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
