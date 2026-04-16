package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By inputSearch = By.xpath("//input[contains(@placeholder, '¿Qué buscas?')]");
    private By btnAgregar = By.xpath("(//span[text()='Agregar'])[1]/parent::button");

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void buscar(String producto) {
        wait.until(ExpectedConditions.elementToBeClickable(inputSearch)).sendKeys(producto + Keys.ENTER);
    }

    public void agregarAlCarrito() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAgregar)).click();
    }
}
