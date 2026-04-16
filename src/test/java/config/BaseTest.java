package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Brave Browser.app/Contents/MacOS/Brave Browser");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://www.metro.pe");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            // driver.quit(); // Comentado por si quieres ver el resultado final
        }
    }
}
