package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By inputSearch = By.xpath("//input[@id='search-autocomplete-input' or @type='text' and (contains(@class,'searchbar') or contains(@class,'Search') or contains(@placeholder, 'buscar') or contains(@placeholder, 'busca') or contains(@placeholder, 'Qué'))]");
    private By btnAgregar = By.xpath("(//button[contains(@class, 'add-to-cart')] | //div[contains(@class, 'buyButton')]//button | //button[.//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'agregar') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'añadir')]])[1]");

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void buscar(String producto) {
        org.openqa.selenium.WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputSearch));
        input.clear();
        input.sendKeys(producto);
        try { Thread.sleep(1000); } catch (Exception e) {}
        input.sendKeys(Keys.ENTER);
    }

    public void agregarAlCarrito() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnAgregar)).click();
        } catch (Exception e) {
            System.out.println("LOG: btnAgregar no encontrado. Dumping DOM...");
            try {
                java.nio.file.Files.writeString(java.nio.file.Paths.get("target/dom_search_dump.html"), driver.getPageSource());
            } catch (Exception ex) {}
            throw e;
        }
    }
}
