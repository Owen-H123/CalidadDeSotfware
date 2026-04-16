package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By btnMiCuenta = By.xpath("//div[contains(@class, 'icon-profile-login-custom')]");
    private By inputEmail = By.xpath("//input[contains(@placeholder, 'ejemplo@mail.com')]");
    private By inputPass = By.cssSelector("input[type='password'][class*='vtex-styleguide-9-x-input']");
    private By checkboxTerms = By.name("chck_terms_cond");
    
    // Selectores más robustos para el botón de Submit
    private By btnSubmit = By.xpath("//button[contains(@class, 'vtex-login-2-x-sendButton')] | //button[@type='submit']");
    private By btnPopupCerrar = By.cssSelector("button[class*='close']");
    private By btnCerrarSesion = By.id("btn-fake-session-0");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void cerrarPopup() {
        try {
            // Intentar cerrar popup varias veces si es necesario
            WebElement close = wait.until(ExpectedConditions.elementToBeClickable(btnPopupCerrar));
            close.click();
            System.out.println("LOG: Popup inicial cerrado.");
        } catch (Exception e) {
            System.out.println("LOG: Sin popup inicial o error al cerrar.");
        }
    }

    public void login(String user, String pass) {
        cerrarPopup();
        wait.until(ExpectedConditions.elementToBeClickable(btnMiCuenta)).click();
        
        // El email debe tener formato válido para que el botón de submit se habilite
        String emailToUse = user.contains("@") ? user : user + "@gmail.com";
        
        wait.until(ExpectedConditions.elementToBeClickable(inputEmail)).sendKeys(emailToUse);
        wait.until(ExpectedConditions.elementToBeClickable(inputPass)).sendKeys(pass);

        // Click en términos con JS para asegurar que no haya overlays
        try {
            WebElement check = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxTerms));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", check);
        } catch (Exception e) {
            System.out.println("LOG: No se pudo clickear términos.");
        }

        // Click en enviar con JS por si el botón está 'deshabilitado' visualmente por overlays
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
        
        System.out.println("LOG: Intento de login enviado.");
    }

    public boolean estaLogueado() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(btnCerrarSesion)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
