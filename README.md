# Automatización Metro Perú - Estilo Simple

Este proyecto sigue una estructura limpia y fácil de entender, inspirada en estándares profesionales.

## 📁 Estructura del Proyecto
*   **`config`**: Configuración de Brave Browser (`BaseTest.java`).
*   **`pages`**: Localizadores (XPaths) y acciones de la web.
*   **`tests`**: Casos de prueba finales (`MetroTest.java`).

##  Cómo ejecutar la prueba

Usa este comando en tu terminal para correr el test:

```bash
mvn test -Dtest=tests.MetroTest
```

Si el comando `mvn` no funciona directo, usa la ruta completa:
```bash
/opt/homebrew/bin/mvn test -Dtest=tests.MetroTest
```

##  Notas
- **JaCoCo Actualizado**: Ya no deberías ver el error `major version 69`.
- **Navegador**: Configurado para **Brave**.
- **Pausa**: El test incluye una pausa al final para que puedas validar el resultado.
