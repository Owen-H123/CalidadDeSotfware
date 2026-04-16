package tests;

import config.BaseTest;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.SearchPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MetroTest extends BaseTest {

    @Test
    public void testFlujoMetro() throws InterruptedException {
        LoginPage login = new LoginPage(driver, wait);
        SearchPage search = new SearchPage(driver, wait);
        CheckoutPage checkout = new CheckoutPage(driver, wait);

        // 1. Login (Usamos un formato de correo real para que Metro lo acepte)
        login.login("owen.hugo@gmail.com", "Metro2024$$");
        
        // Espera para ver el resultado en pantalla antes de seguir
        System.out.println("LOG: Esperando carga de sesión...");
        Thread.sleep(5000); 

        // 2. Buscar y Agregar
        search.buscar("Leche Gloria");
        search.agregarAlCarrito();
        Thread.sleep(2000);

        // 3. Checkout
        checkout.irAlCarrito();
        checkout.finalizarCompra();

        // 4. Validar llegada a identificación
        boolean completado = checkout.enIdentificacion();
        Assertions.assertTrue(completado, "ERROR: No se alcanzó la pantalla de identificación.");
        
        System.out.println("LOG: ¡Prueba completada con éxito!");
    }
}
