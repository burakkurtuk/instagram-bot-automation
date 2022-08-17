import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class App {

    WebDriver driver;
    String BASE_URL = "https://www.instagram.com/";

    By usernameLocator = By.cssSelector("input[name='username']");
    By passwordLocator = By.cssSelector("input[name='password']");
    By loginButtonLocator = By.cssSelector("button[type='submit']");
    By notificationLocator = By.cssSelector("button[class='_a9-- _a9_1']");
    By profileNameLocator = By.className("_aa_m");
    By postImageLocator = By.className("_aagu");
    By likeButtonLocator = By.cssSelector("span._aamw");
    By postCountLocator = By.className("_ac2a");
    By htmlTag = By.tagName("html");


    public App() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();


    }

    public void loginWith(String username, String password) {
        waitFor(usernameLocator);
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        waitFor(notificationLocator);
        driver.findElement(notificationLocator).click();


    }

    public void navigateToProfile(String profileName) {
        driver.navigate().to(BASE_URL.concat(profileName));
    }

    public void clickFirstPost() {
        waitFor(profileNameLocator);
        driver.findElements(postImageLocator).get(0).click();
    }

    public void likeAllPost() {
        int count = getPostCount();
        while (count >0) {
            waitFor(likeButtonLocator);
            driver.findElement(likeButtonLocator).click();
            driver.findElement(htmlTag).sendKeys(Keys.ARROW_RIGHT);
            count -- ;
        }

    }

    private int getPostCount() {
        String count = driver.findElement(postCountLocator).getText();
        return Integer.parseInt(count);
    }


    private void waitFor(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
