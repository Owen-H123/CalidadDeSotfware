package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By iconMinicart = By.xpath("//div[contains(@class, 'minicart')]");
    private By btnFinalizar = By.xpath("//a[contains(@href, '/checkout')]//span[contains(text(), 'Finalizar')]");
    private By inputEmail = By.id("client-pre-email");

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void irAlCarrito() {
        wait.until(ExpectedConditions.elementToBeClickable(iconMinicart)).click();
    }

    public void finalizarCompra() {
        wait.until(ExpectedConditions.elementToBeClickable(btnFinalizar)).click();
    }

    public boolean enIdentificacion() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmail)).isDisplayed();
    }
}
