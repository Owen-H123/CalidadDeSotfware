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

    private By btnMiCuenta = By.xpath("//div[contains(@class, 'login')] | //div[contains(@class, 'icon-profile')] | //*[contains(@class, 'vtex-login')]//button");
    private By inputEmail = By.xpath("//input[contains(@placeholder, 'mail') or contains(@type, 'email') or contains(@name, 'email')]");
    private By inputPass = By.xpath("//input[@type='password']");
    private By checkboxTerms = By.name("chck_terms_cond");
    
    // Selectores más robustos para el botón de Submit
    private By btnSubmitEmailFlow = By.xpath("//div[contains(@class, 'vtex-login-2-x-button')]//button[.//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'e-mail') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'correo')]]");
    private By btnSubmit = By.xpath("//div[contains(@class, 'sendButton')]//button | //button[contains(@class, 'sendButton')] | //button[@type='submit' and //span[contains(text(), 'Entrar') or contains(text(), 'Ingresar') or contains(text(), 'Acceder')]] | //div[contains(@class, 'login')]//button[@type='submit'] | //span[text()='Entrar']/parent::button");
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
        
        // Por si hay un paso previo para elegir opción de "Ingresar con e-mail y contraseña"
        try {
            WebElement btnEmailFlow = wait.until(ExpectedConditions.visibilityOfElementLocated(btnSubmitEmailFlow));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnEmailFlow);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("LOG: No habia boton para step previo de email flow, continuamos...");
        }

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
        try {
            WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
        } catch (Exception e) {
            try {
                System.out.println("LOG: btnSubmit timeout. Dumping DOM...");
                java.io.File domFile = new java.io.File("target/dom_dump.html");
                java.nio.file.Files.write(domFile.toPath(), driver.getPageSource().getBytes(java.nio.charset.StandardCharsets.UTF_8));
            } catch (Exception ex) {}
            throw e;
        }
        
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
