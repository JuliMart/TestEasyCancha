package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utility;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {
    static WebDriver driver; // Instancia del WebDriver
    static String pathDriver = "./src/test/resources/chrome/chromedriver.exe"; // Ruta del driver de Chrome
    static String tipoDriver = "webdriver.chrome.driver"; // Tipo de driver

    @Before
    public void setUp() throws Exception {
        System.setProperty(tipoDriver, pathDriver); // Configurar el sistema con el path del driver
        driver = new ChromeDriver(); // Inicializar el WebDriver
        driver.manage().window().maximize(); // Maximizar la ventana del navegador
        driver.manage().deleteAllCookies(); // Borrar cookies para un inicio limpio
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit(); // Cerrar el navegador después de las pruebas
        }
    }

    @Given("al navegar hasta la url {string}")
    public void al_navegar_hasta_la_url(String url) throws IOException {
        driver.get(url); // Navegar a la URL dada
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='book-views']"))); // Esperar a que un elemento sea visible
    
    String obj = "al navegar hasta la url"; //nombre de la foto 
    Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
    }
    
    @And("hacer click en el botón {string}")
    public void hacer_click_en_el_boton(String botonXPath) throws IOException {
        try {
            // Configura una espera explícita de hasta 30 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Esperar a que el botón identificado por el XPath sea clicable
            WebElement boton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(botonXPath)));

            // Esperar 2 segundos antes de hacer clic
            Thread.sleep(2000);

            // Hacer clic en el botón
            boton.click();

            // Tomar una captura de pantalla
            String obj = "hacer click en el botón"; // Nombre de la captura
            Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");

            System.out.println("Se hizo clic en el botón y se tomó una captura de pantalla.");
        } catch (InterruptedException e) {
            throw new RuntimeException("Error durante la espera de 2 segundos antes de hacer clic en el botón.", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón.", e);
        }
        
        String obj = "hacer click en el botón"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
        }
    


    @When("coloca en el campo usuario {string} el texto {string}")
    public void coloca_en_el_campo_usuario_el_texto(String usuarioXPath, String texto) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(usuarioXPath))); // Esperar el campo de usuario
        inputField.clear(); // Limpiar el campo
        inputField.sendKeys(texto); // Ingresar texto
        String obj = "hacer click en el botón"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
    }

    @When("coloca en el campo password {string} el texto {string}")
    public void coloca_en_el_campo_password_el_texto(String passwordXPath, String texto) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(passwordXPath))); // Esperar el campo de contraseña
        inputField.clear(); // Limpiar el campo
        inputField.sendKeys(texto); // Ingresar texto
        String obj = "coloca en el campo password el texto"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
   
    }

    @And("hace click en el boton {string}")
    public void hace_click_en_el_boton(String botonXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement boton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(botonXPath))); // Esperar que el botón sea clicable
        boton.click(); // Hacer clic en el botón
        String obj = "hace click en el boton"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
   
    }

    @Then("presenta el mensaje de bienvenida {string}")
    public void presenta_el_mensaje_de_bienvenida(String mensajeXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mensajeXPath))); // Esperar el mensaje de bienvenida
        String textoBienvenida = mensaje.getText(); // Obtener el texto del mensaje

        if (textoBienvenida.contains("Bienvenido")) { // Verificar si contiene "Bienvenido"
            System.out.println("Login exitoso: " + textoBienvenida); // Imprimir mensaje de éxito
        } else {
            System.out.println("No se encontró el mensaje de bienvenida esperado."); // Mensaje de error
        }
        String obj = "presenta el mensaje de bienvenida"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
  
    }

    @Then("presenta el mensaje de error {string}")
    public void presenta_el_mensaje_de_error(String mensajeXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement mensajeError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mensajeXPath))); // Esperar el mensaje de error
        String textoError = mensajeError.getText(); // Obtener el texto del mensaje

        if (textoError.contains("Error") || textoError.contains("incorrecto")) { // Verificar mensaje de error
            System.out.println("Login fallido: " + textoError); // Imprimir mensaje de fallo
        } else {
            System.out.println("No se encontró el mensaje de error esperado."); // Mensaje de error
        }
        String obj = "presenta el mensaje de error"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
  
    }

    
    
  
    // Método para hacer clic en 'Olvidé contraseña'
    @Then("hacer click en olvide contraseña {string}")
    public void hacer_click_en_olvide_contrasena(String olvideContrasenaXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement olvideContrasenaElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(olvideContrasenaXPath)));
        olvideContrasenaElement.click();
        
        try {
            Thread.sleep(1000); // Esperar 1 segundo antes de tomar la foto
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        
        String obj = "hacer click en olvide contraseña"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
    }
    @Then("presiona recuperar clave {string}")
    public void presionar_recuperar_clave(String recuperarClaveXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement recuperarClaveElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(recuperarClaveXPath)));
        recuperarClaveElement.click();
        
        try {
            Thread.sleep(1000); // Esperar 1 segundo antes de tomar la foto
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        
        String obj = "presionar recuperar clave"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
    }


    // Método para hacer clic en 'Clickear por email'
    @Then("clickear por email {string}")
    public void clickear_por_email(String emailButtonXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement emailButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(emailButtonXPath)));
        emailButtonElement.click();
        
        try {
            Thread.sleep(1000); // Esperar 1 segundo antes de tomar la foto
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        
        String obj = "clickear por email"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
    }
    
    @Then("el boton ingresar debe estar deshabilitado {string}")
    public void el_boton_ingresar_debe_estar_deshabilitado(String botonXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement boton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(botonXPath))); // Esperar el botón

        // Verificar que el botón esté deshabilitado
        boolean isDisabled = !boton.isEnabled();

        if (isDisabled) {
            System.out.println("El botón está deshabilitado como se esperaba."); // Mensaje de éxito
        } else {
            System.out.println("El botón no está deshabilitado, lo que no es esperado."); // Mensaje de error
        }
    
    String obj = "el boton ingresar debe estar deshabilitado"; //nombre de la foto 
    Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
    }

    // Métodos para la característica de Reserva

    @Given("al navegar hasta la url de reserva {string}")
    public void al_navegar_hasta_la_url_de_reserva(String url) throws IOException {
        driver.get(url); // Navegar a la URL de reserva
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='book-views']"))); // Esperar a que sea visible
        String obj = "al navegar hasta la url de reserva"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
       
    }

    @When("hace click en el botón deporte {string}")
    public void hace_click_en_el_boton_deporte(String deporteXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement botonDeporte = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deporteXPath))); // Esperar botón de deporte

        try {
            Thread.sleep(2000); // Esperar 2 segundos adicionales antes de hacer clic
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error durante la espera antes de hacer clic en el botón de deporte.", e);
        }

        botonDeporte.click(); // Hacer clic
        String obj = "hace click en el botón deporte"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
    }

    @When("hace click en el botón dia {string}")
    public void hace_click_en_el_boton_dia(String diaXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Esperar hasta que desaparezca la superposición de carga
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

        // Esperar a que el botón del día sea clicable
        WebElement botonDia = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(diaXPath)));
        botonDia.click(); // Hacer clic en el botón del día
        String obj = "hace click en el botón dia"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
    
    }

    @When("selecciona la hora {string} y presiona {string}")
    public void selecciona_hora_y_presiona_siguiente(String horaXPath, String botonContinuarXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar seleccionar la hora hasta 5 veces
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar hasta que la página esté lista (sin overlay de carga)
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Localizar el elemento de la hora y desplazarse hasta él
                WebElement hora = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(horaXPath)));
                js.executeScript("arguments[0].scrollIntoView(true);", hora);

                // Intentar hacer clic con Selenium
                wait.until(ExpectedConditions.elementToBeClickable(hora)).click();
                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (StaleElementReferenceException | org.openqa.selenium.ElementClickInterceptedException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en la hora nuevamente. Intento: " + retryCount);
            } catch (org.openqa.selenium.TimeoutException e) {
                // Si el elemento no se encuentra, intentar nuevamente
                retryCount++;
                System.out.println("Timeout esperando el botón de hora. Intento: " + retryCount);
            }
        }

        // Verificar si se logró hacer clic en la hora, si no se logró lanzar una excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en la hora '" + horaXPath + "' después de múltiples intentos.");
        }

        // Esperar hasta que desaparezca el overlay de carga nuevamente antes de hacer clic en el botón
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

        // Ahora, localizar el botón "Continuar" y hacer clic
        try {
            WebElement botonContinuar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(botonContinuarXPath)));
            js.executeScript("arguments[0].scrollIntoView(true);", botonContinuar);

            // Intentar hacer clic en el botón "Continuar"
            wait.until(ExpectedConditions.elementToBeClickable(botonContinuar)).click();
            System.out.println("Botón 'Continuar' clicado exitosamente.");

        } catch (StaleElementReferenceException | org.openqa.selenium.TimeoutException e) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'Continuar' después de múltiples intentos.");
        }
        String obj = "selecciona la hora {string} y presiona {string}"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
    
  
        
    }

    @Then("presenta el mensaje {string}")
    public void presenta_el_mensaje(String mensaje) throws IOException {
        // Este método puede implementarse para validar el mensaje esperado
        System.out.println("Mensaje de reserva: " + mensaje); // Imprimir mensaje de reserva
        String obj = "presenta el mensaje"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
    
  
    }

    // Método para apretar botón "Siguiente"
    @When("apretar boton {string}")
    public void apretar_boton(String botonXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement boton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(botonXPath))); // Esperar a que el botón sea clicable
        boton.click(); // Hacer clic
        String obj = "apretar boton"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
    
    }

    @When("hace click en el botón tenis {string}")
    public void hace_click_en_el_boton_tenis(String tenisXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement botonTenis = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tenisXPath))); // Esperar botón de tenis

        try {
            Thread.sleep(2000); // Esperar 2 segundos adicionales antes de hacer clic
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error durante la espera antes de hacer clic en el botón de tenis.", e);
        }

        botonTenis.click(); // Hacer clic en el botón de tenis
        String obj = "hace click en el botón tenis"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
    }


    @And("selecciona club {string}")
    public void seleccionaClub(String clubXPath) throws IOException {
        try {
            // Configura una espera explícita de hasta 30 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Espera a que el elemento identificado por el XPath sea clicable
            WebElement botonClub = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(clubXPath)));

            // Desplázate al botón (opcional) y espera 2 segundos
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botonClub);
            Thread.sleep(2000); // Espera 2 segundos adicionales antes de hacer clic

            // Haz clic en el botón
            botonClub.click();
            System.out.println("Se hizo clic en el botón 'Selecciona club' después de esperar 2 segundos.");
        } catch (InterruptedException e) {
            throw new RuntimeException("Error durante la espera de 2 segundos antes de hacer clic en el botón 'Selecciona club'.", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'Selecciona club'.", e);
        }
        String obj = "selecciona club"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
    }

    @When("selecciona disponibilidad {string}")
    public void seleccionaDisponibilidad(String xpathDisponibilidad) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Esperar a que desaparezca el overlay de carga, si existe
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

            // Esperar hasta que el elemento de disponibilidad esté visible
            WebElement disponibilidadElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathDisponibilidad)));

            // Realizar scroll hacia el elemento
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", disponibilidadElement);

            // Esperar brevemente para asegurarse de que el scroll se haya completado
            Thread.sleep(1000);

            // Verificar si el elemento está habilitado antes de hacer clic
            if (disponibilidadElement.isDisplayed() && disponibilidadElement.isEnabled()) {
                // Intentar hacer clic con Selenium
                disponibilidadElement.click();
                System.out.println("Elemento de disponibilidad clicado exitosamente: " + xpathDisponibilidad);
            } else {
                System.err.println("Elemento de disponibilidad no está visible o habilitado, intentando clic con JavaScript.");
                js.executeScript("arguments[0].click();", disponibilidadElement);
            }
        } catch (ElementClickInterceptedException e) {
            System.out.println("Elemento interceptado, intentando con JavaScript.");
            js.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpathDisponibilidad)));
        } catch (NoSuchElementException e) {
            System.err.println("Error: No se encontró el elemento con el xpath: " + xpathDisponibilidad);
            throw e;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error: Interrupción durante la espera.");
            throw new RuntimeException("Error durante la espera: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al intentar seleccionar la disponibilidad: " + e.getMessage());
            throw e;
        }
        String obj = "selecciona disponibilidad"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
    }

    @When("elige nro cancha {string}")
    public void seleccionaLaCancha(String xpathCancha) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Esperar hasta que el elemento esté visible y presente
            WebElement canchaElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathCancha)));

            // Verificar si el elemento está habilitado y hacer scroll hacia él
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", canchaElement);

            // Intentar hacer clic con Selenium
            if (canchaElement.isDisplayed() && canchaElement.isEnabled()) {
                canchaElement.click();
                System.out.println("Cancha seleccionada exitosamente: " + xpathCancha);
            } else {
                System.err.println("El elemento de la cancha no está visible o habilitado, intentando clic con JavaScript.");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", canchaElement);
            }
        } catch (NoSuchElementException e) {
            System.err.println("Error: No se encontró la cancha con el xpath: " + xpathCancha);
            throw e;
        } catch (ElementClickInterceptedException e) {
            System.err.println("Error: Otro elemento está interceptando el clic en la cancha, intentando con JavaScript.");
            WebElement canchaElement = driver.findElement(By.xpath(xpathCancha));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", canchaElement);
        } catch (Exception e) {
            System.err.println("Error inesperado al intentar seleccionar la cancha: " + e.getMessage());
            throw e;
        }
        String obj = "elige nro cancha"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
    }

    @When("presiona el boton no quiero proteger a nadie {string}")
    public void presiona_el_boton_no_proteger_a_nadie(String xpathBoton) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar seleccionar el botón hasta 5 veces
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar 2 segundos antes de buscar el botón
                Thread.sleep(2000);

                // Esperar hasta que desaparezca cualquier overlay de carga
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Localizar el botón
                WebElement botonNoProteger = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathBoton)));

                // Hacer scroll hasta el elemento
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botonNoProteger);

                // Intentar hacer clic
                wait.until(ExpectedConditions.elementToBeClickable(botonNoProteger)).click();
                isClicked = true; // Si el clic es exitoso, salir del bucle
                System.out.println("Botón 'No quiero proteger a nadie' clicado exitosamente: " + xpathBoton);

            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en el botón 'No quiero proteger a nadie' nuevamente. Intento: " + retryCount);
            } catch (org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Timeout esperando el botón 'No quiero proteger a nadie'. Intento: " + retryCount);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error: Interrupción durante la espera.");
                throw new RuntimeException("Error durante la espera: " + e.getMessage());
            }
        }

        // Si no se pudo hacer clic en el botón después de 5 intentos, lanzar una excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'No quiero proteger a nadie' después de múltiples intentos.");
        }
        String obj = "presiona el boton no quiero proteger a nadie"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
    }

    @When("presionar el botón reservar y pagar {string}")
    public void presionar_el_boton_reservar_y_pagar(String xpathBoton) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar seleccionar el botón hasta 5 veces
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar hasta que desaparezca cualquier overlay de carga
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Localizar el botón y esperar a que sea clicable
                WebElement botonReservarPagar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBoton)));

                // Hacer scroll hasta el elemento si no está en el área visible
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botonReservarPagar);

                // Intentar hacer clic en el botón
                botonReservarPagar.click();
                isClicked = true; // Si el clic es exitoso, salir del bucle
                System.out.println("Botón 'Reservar y pagar' clicado exitosamente: " + xpathBoton);

            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en el botón 'Reservar y pagar' nuevamente. Intento: " + retryCount);
            } catch (org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Timeout esperando el botón 'Reservar y pagar'. Intento: " + retryCount);
            }
        }

        // Si no se pudo hacer clic en el botón después de 5 intentos, lanzar una excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'Reservar y pagar' después de múltiples intentos.");
        }
        String obj = "presionar el botón reservar y pagar"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
  
    }

    @When("presiona el boton agregar tarjeta de credito debito {string}")
    public void presiona_el_boton_agregar_tarjeta(String xpathBoton) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar seleccionar el botón hasta 5 veces
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar hasta que desaparezca cualquier overlay de carga
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Localizar el botón y esperar a que sea clicable
                WebElement botonAgregarTarjeta = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBoton)));

                // Hacer scroll hasta el elemento si no está en el área visible
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botonAgregarTarjeta);

                // Intentar hacer clic en el botón
                botonAgregarTarjeta.click();
                isClicked = true; // Si el clic es exitoso, salir del bucle
                System.out.println("Botón 'Agregar tarjeta de crédito / débito' clicado exitosamente: " + xpathBoton);

            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en el botón 'Agregar tarjeta de crédito / débito' nuevamente. Intento: " + retryCount);
            } catch (org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Timeout esperando el botón 'Agregar tarjeta de crédito / débito'. Intento: " + retryCount);
            }
        }

        // Si no se pudo hacer clic en el botón después de 5 intentos, lanzar una excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'Agregar tarjeta de crédito / débito' después de múltiples intentos.");
        }
        String obj = "presiona el boton agregar tarjeta de credito debito"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
  
    }

    @Then("verifica y selecciona la opción de no agregar tarjeta {string}")
    public void verifica_selecciona_no_agregar_tarjeta(String xpathBoton) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Esperar hasta que el modal esté visible
            WebElement modalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'modal-window') and @role='dialog']")));

            if (modalElement.isDisplayed()) {
                System.out.println("Modal de agregar tarjeta mostrado exitosamente.");

                // Hacer scroll al modal para asegurar visibilidad si es necesario
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", modalElement);

                // Localizar el botón "No agregar tarjeta y volver"
                WebElement botonNoAgregar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBoton)));

                // Hacer scroll hasta el botón para asegurar visibilidad
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botonNoAgregar);

                // Intentar hacer clic en el botón
                botonNoAgregar.click();
                System.out.println("Botón 'No agregar tarjeta y volver' clicado exitosamente.");
            }
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("No se encontró el modal de agregar tarjeta en el tiempo especificado.");
        } catch (NoSuchElementException e) {
            System.err.println("No se encontró el botón 'No agregar tarjeta y volver'.");
        } catch (ElementClickInterceptedException e) {
            System.err.println("El clic en el botón fue interceptado, intentando con JavaScript.");
            WebElement botonNoAgregar = driver.findElement(By.xpath(xpathBoton));
            js.executeScript("arguments[0].click();", botonNoAgregar);
        } catch (Exception e) {
            System.err.println("Error inesperado al intentar seleccionar la opción de no agregar tarjeta: " + e.getMessage());
            throw e;
        }
        String obj = "verifica y selecciona la opción de no agregar tarjeta"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
    }

    @When("presionar el botón reservar y pagar más tarde {string}")
    public void presionar_el_boton_reservar_y_pagar_mas_tarde(String xpathBoton) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar seleccionar el botón hasta 5 veces
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar hasta que desaparezca cualquier overlay de carga
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Localizar el botón y esperar a que sea clicable
                WebElement botonReservarPagar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBoton)));

                // Hacer scroll hasta el elemento si no está en el área visible
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botonReservarPagar);

                // Intentar hacer clic en el botón
                botonReservarPagar.click();
                isClicked = true; // Si el clic es exitoso, salir del bucle
                System.out.println("Botón 'Reservar y pagar más tarde' clicado exitosamente: " + xpathBoton);

            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en el botón 'Reservar y pagar más tarde' nuevamente. Intento: " + retryCount);
            } catch (org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Timeout esperando el botón 'Reservar y pagar más tarde'. Intento: " + retryCount);
            }
        }

        // Si no se pudo hacer clic en el botón después de 5 intentos, lanzar una excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'Reservar y pagar más tarde' después de múltiples intentos.");
        }
        String obj = "presionar el botón reservar y pagar más tarde"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
 
    }
    
    
    @Then("verificar que botón fecha anterior esté deshabilitado {string}")
    public void verificar_boton_fecha_anterior_esta_deshabilitado(String xpathBoton) throws TimeoutException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Esperar a que el botón esté visible
		WebElement boton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathBoton)));

		// Verificar si el botón tiene el atributo "disabled" presente
		boolean estaDeshabilitado = boton.getAttribute("disabled") != null;

		if (estaDeshabilitado) {
		    System.out.println("El botón 'fecha anterior' está correctamente deshabilitado.");
		} else {
		    throw new AssertionError("El botón 'fecha anterior' no está deshabilitado como se esperaba.");
		}
	      String obj = "verificar que botón fecha anterior esté deshabilitado"; //nombre de la foto 
	      Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	 
    }

    
    
    
    @When("presionar el botón reservas del footer {string}")
    public void presionar_el_boton_reservas_footer(String xpathBoton) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar hasta que el botón esté presente en el DOM
                WebElement botonReservas = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathBoton)));

                // Verificar si el botón es visible y habilitado
                if (botonReservas.isDisplayed() && botonReservas.isEnabled()) {
                    // Hacer scroll hasta el elemento si no está en el área visible
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botonReservas);

                    // Intentar hacer clic en el botón con JavaScript
                    js.executeScript("arguments[0].click();", botonReservas);
                    isClicked = true; // Si el clic es exitoso, salir del bucle
                    System.out.println("Botón 'Reservas' del footer clicado exitosamente: " + xpathBoton);
                } else {
                    throw new ElementNotInteractableException("El botón 'Reservas' no está visible o habilitado.");
                }

            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en el botón 'Reservas' del footer nuevamente. Intento: " + retryCount);
            } catch (org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Timeout esperando el botón 'Reservas' del footer. Intento: " + retryCount);
            } catch (ElementNotInteractableException e) {
                retryCount++;
                System.out.println("El botón 'Reservas' no está interactuable. Intento: " + retryCount);
            }
        }

        // Si no se pudo hacer clic en el botón después de 5 intentos, lanzar una excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'Reservas' del footer después de múltiples intentos.");
        }
        String obj = "presionar el botón reservas del footer"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
 
    }
    @And("presionar boton pasadas {string}")
    public void presionar_boton_pasadas(String pasadasButtonXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90)); // Esperar hasta 90 segundos
            WebElement botonPasadas = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pasadasButtonXPath))); // Esperar a que el botón esté clickeable
            
            botonPasadas.click(); // Hacer clic en el botón "Pasadas"
            System.out.println("Botón 'Pasadas' presionado exitosamente: " + pasadasButtonXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript si está interceptado
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(pasadasButtonXPath)));
                System.out.println("Botón 'Pasadas' presionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón 'Pasadas' con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón 'Pasadas': " + e.getMessage());
        }
        String obj = "presionar boton pasadas"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
    }

@When("presionar el botón anular {string}")
    public void presionar_el_boton_anular(String anularButtonXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
            WebElement botonAnular = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(anularButtonXPath))); // Esperar a que el botón "Anular" esté clickeable
            
            botonAnular.click(); // Hacer clic en el botón "Anular"
            System.out.println("Botón 'Anular' presionado exitosamente: " + anularButtonXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript si está interceptado
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(anularButtonXPath)));
                System.out.println("Botón 'Anular' presionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón 'Anular' con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón 'Anular': " + e.getMessage());
        }
        String obj = "presionar el botón anular"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
    }


    @When("presionar el botón sí en el modal de confirmación {string}")
    public void presionar_el_boton_si_en_modal(String xpathBoton) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar seleccionar el botón hasta 5 veces
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar hasta que el botón sea clicable
                WebElement botonSi = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBoton)));

                // Hacer scroll hasta el elemento si no está en el área visible
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botonSi);

                // Intentar hacer clic en el botón
                botonSi.click();
                isClicked = true; // Si el clic es exitoso, salir del bucle
                System.out.println("Botón 'Sí' clicado exitosamente: " + xpathBoton);

            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en el botón 'Sí' nuevamente. Intento: " + retryCount);
            } catch (org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Timeout esperando el botón 'Sí'. Intento: " + retryCount);
            }
        }

        // Si no se pudo hacer clic en el botón después de 5 intentos, lanzar una excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'Sí' después de múltiples intentos.");
        }
        String obj = "presionar el botón sí en el modal de confirmación"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
 
    }


    @When("presionar el botón ok en el mensaje de éxito {string}")
    public void presionar_el_boton_ok_en_mensaje_exito(String xpathBoton) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar seleccionar el botón hasta 5 veces
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar hasta que el botón sea clicable
                WebElement botonOk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBoton)));

                // Hacer scroll hasta el elemento si no está en el área visible
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botonOk);

                // Intentar hacer clic en el botón
                botonOk.click();
                isClicked = true; // Si el clic es exitoso, salir del bucle
                System.out.println("Botón 'Ok' clicado exitosamente: " + xpathBoton);

            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en el botón 'Ok' nuevamente. Intento: " + retryCount);
            } catch (org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Timeout esperando el botón 'Ok'. Intento: " + retryCount);
            }
        }

        // Si no se pudo hacer clic en el botón después de 5 intentos, lanzar una excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'Ok' después de múltiples intentos.");
        }
        String obj = "presionar el botón ok en el mensaje de éxito"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
    }
    
    @Then("verificar que no tienes reservas activas {string}")
    public void verificar_no_tienes_reservas_activas(String xpathMensaje) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // Esperar hasta que el mensaje esté presente
            WebElement mensajeReservasInactivas = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMensaje)));

            // Verificar que el mensaje es visible en la página
            if (mensajeReservasInactivas.isDisplayed()) {
                System.out.println("Mensaje 'No tienes reservas activas' encontrado exitosamente: " + xpathMensaje);
            } else {
                throw new RuntimeException("Mensaje 'No tienes reservas activas' no está visible.");
            }

        } catch (org.openqa.selenium.TimeoutException e) {
            throw new RuntimeException("No se pudo encontrar el mensaje 'No tienes reservas activas' después de esperar.");
        }
        String obj = "verificar que no tienes reservas activas"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
    }

    @When("presionar el icono de perfil del footer {string}")
    public void presionar_el_icono_de_perfil_footer(String xpathBoton) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar seleccionar el botón hasta 5 veces
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar hasta que el botón sea clicable
                WebElement botonPerfil = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBoton)));

                // Hacer scroll hasta el elemento si no está en el área visible
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botonPerfil);

                // Intentar hacer clic en el botón
                botonPerfil.click();
                isClicked = true; // Si el clic es exitoso, salir del bucle
                System.out.println("Botón 'Perfil' del footer clicado exitosamente: " + xpathBoton);

            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en el botón 'Perfil' del footer nuevamente. Intento: " + retryCount);
            } catch (org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Timeout esperando el botón 'Perfil' del footer. Intento: " + retryCount);
            }
        }

        // Si no se pudo hacer clic en el botón después de 5 intentos, lanzar una excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'Perfil' del footer después de múltiples intentos.");
        }
        String obj = "presionar el icono de perfil del footer"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
    }

    @Given("al navegar hasta la url1 {string}")
    public void al_navegar_hasta_la_url1(String url) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Navegar a la URL
        driver.get(url);

        // Esperar hasta que la página esté completamente cargada
        wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));

        WebElement bookViewsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='book-views']")));
		System.out.println("Elemento 'book-views' está visible en la página.");
	      String obj = "al navegar hasta la url1"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	 
    }

    @When("hace click en el botón padel {string}")
    public void hace_click_en_el_boton_padel(String xpathOpcion) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar seleccionar el botón hasta 5 veces
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar hasta que la página esté completamente cargada
                wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));

                // Esperar hasta que el elemento con la opción "Golf" sea visible y clicable
                WebElement opcionPadel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOpcion)));

                // Hacer scroll hasta el elemento si no está en el área visible
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", opcionPadel);

                // Intentar hacer clic en el botón con JavaScript
                js.executeScript("arguments[0].click();", opcionPadel);
                isClicked = true; // Si el clic es exitoso, salir del bucle
                System.out.println("Opción 'Padel' seleccionada exitosamente: " + xpathOpcion);

            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en el botón 'Padel' nuevamente. Intento: " + retryCount);
            }
        }

        // Si no se pudo hacer clic en el botón después de 5 intentos, lanzar una excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'Golf' después de múltiples intentos.");
        }
        String obj = "hace click en el botón padel"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
    }
    @Then("verificar que el elemento contenga la palabra Padel {string}")
    public void verificar_texto_contiene_padel(String xpathElemento) throws TimeoutException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Esperar a que Angular termine de procesar solicitudes pendientes
        wait.until(driver -> js.executeScript(
                "return window.angular !== undefined && angular.element(document).injector().get('$http').pendingRequests.length === 0"
        ));

        // Esperar a que el elemento esté presente y visible en el DOM
		WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathElemento)));

		// Utilizar JavaScript para obtener el texto del elemento
		String textoElemento = (String) js.executeScript("return arguments[0].innerText;", elemento);

		// Verificar si el texto contiene "Padel"
		if (textoElemento.contains("Padel")) {
		    System.out.println("El elemento contiene la palabra esperada: " + textoElemento);
		} else {
		    throw new AssertionError("El elemento no contiene la palabra esperada ('Padel'): " + textoElemento);
		}
		String obj = "verificar que el elemento contenga la palabra Padel"; //nombre de la foto 
        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
 
    }
    @Then("verificar que aparezcan deportes para seleccionar {string}")
    public void verificar_deportes_para_seleccionar(String xpathDeportes) throws TimeoutException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Esperar hasta que Angular termine de procesar solicitudes pendientes
		wait.until(driver -> js.executeScript(
		        "return window.angular !== undefined && angular.element(document).injector().get('$http').pendingRequests.length === 0"
		));

		// Esperar a que el contenedor con los deportes esté visible en la página
		WebElement contenedorDeportes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathDeportes)));

		// Obtener la lista de deportes disponibles en el contenedor
		List<WebElement> deportes = contenedorDeportes.findElements(By.xpath(".//div[contains(@class, 'sport_item')]"));

		// Verificar que haya al menos un deporte disponible para seleccionar
		if (deportes.isEmpty()) {
		    throw new AssertionError("No se encontraron deportes disponibles para seleccionar.");
		}

		System.out.println("Deportes encontrados para seleccionar: " + deportes.size());
		  String obj = "verificar que aparezcan deportes para seleccionar"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	 
    }
    @When("presionar el botón volver dos veces {string}")
    public void presionar_el_boton_volver_dos_veces(String xpathBoton) throws TimeoutException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 2; i++) {
            try {
                // Esperar hasta que Angular termine de procesar solicitudes pendientes
                wait.until(driver -> js.executeScript(
                        "return window.angular !== undefined && angular.element(document).injector().get('$http').pendingRequests.length === 0"
                ));

                // Esperar a que el botón sea visible y clicable
                WebElement botonVolver = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBoton)));

                // Hacer scroll hasta el elemento si no está en el área visible
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botonVolver);
                Thread.sleep(1000); // Esperar un segundo para asegurarse de que el scroll se completó

                // Intentar hacer clic en el botón con JavaScript
                js.executeScript("arguments[0].click();", botonVolver);
                System.out.println("Botón 'Volver' clicado exitosamente. Intento: " + (i + 1));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Error durante la espera del scroll", e);
            }
        }
  	  String obj = "presionar el botón volver dos veces"; //nombre de la foto 
      Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");

    }
    @Then("verificar que el elemento contenga la palabra Tenis {string}")
    public void verificar_texto_contiene_tenis(String xpathElemento) throws TimeoutException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Esperar a que Angular termine de procesar solicitudes pendientes
        wait.until(driver -> js.executeScript(
                "return window.angular !== undefined && angular.element(document).injector().get('$http').pendingRequests.length === 0"
        ));

        // Esperar a que el elemento esté presente y visible en el DOM
		WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathElemento)));

		// Utilizar JavaScript para obtener el texto del elemento
		String textoElemento = (String) js.executeScript("return arguments[0].innerText;", elemento);

		// Verificar si el texto contiene "Tenis"
		if (textoElemento.contains("Tenis")) {
		    System.out.println("El elemento contiene la palabra esperada: " + textoElemento);
		} else {
		    throw new AssertionError("El elemento no contiene la palabra esperada ('Tenis'): " + textoElemento);
		}
		  String obj = "verificar que el elemento contenga la palabra Tenis"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	 
    }
    
    
    @When("presiona el btnFooter {string}")
    public void presiona_el_btn_footer(String btnFooterXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement btnFooter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btnFooterXPath))); // Esperar a que el botón del footer sea clicable
            btnFooter.click(); // Hacer clic en el botón del footer
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el botón del footer con XPath: " + btnFooterXPath);
            throw e;
        }
		  String obj = "presiona el btnFooter"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    
    @When("hace click en el botón Match {string}")
    public void hace_click_en_el_boton_Match(String matchXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
        WebElement botonMatch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(matchXPath))); // Esperar botón de Match
        botonMatch.click(); // Hacer clic en el botón "Match"
        System.out.println("Botón 'Match' seleccionado exitosamente: " + matchXPath);
		  String obj = "hace click en el botón Match"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }


    @Then("verificar rivales disponibles {string}")
    public void verificar_rivales_disponibles(String xpathRivales) throws TimeoutException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

        // Esperar hasta que los rivales estén presentes en el DOM
        List<WebElement> rivales = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathRivales)));

        // Verificar si existen rivales disponibles
        if (!rivales.isEmpty()) {
            System.out.println("Se encontraron " + rivales.size() + " rivales disponibles para desafiar.");
        } else {
            throw new AssertionError("No se encontraron rivales disponibles para desafiar.");
        }
		  String obj = "verificar rivales disponibles"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    
    @When("hace click en el botón ajustes {string}")
    public void hace_click_en_el_boton_ajustes(String ajustesXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
            WebElement botonAjustes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ajustesXPath))); // Esperar hasta que el botón de ajustes sea clicable
            
            // Desplazar la vista hacia el botón para asegurarse de que está visible
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botonAjustes);
            
            botonAjustes.click(); // Hacer clic en el botón de ajustes
            System.out.println("Botón 'Ajustes' seleccionado exitosamente: " + ajustesXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(ajustesXPath)));
                System.out.println("Botón 'Ajustes' seleccionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón 'Ajustes' con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón 'Ajustes': " + e.getMessage());
        }
		  String obj = "hace click en el botón ajustes"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }

    @When("hace click en el botón editar deporte {string}")
    public void hace_click_en_el_boton_editar_deporte(String editarXPath) throws IOException {
        try {
            // Configura una espera explícita de hasta 60 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

            // Espera a que el botón esté presente
            WebElement botonEditar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(editarXPath)));

            // Espera 2 segundos adicionales
            Thread.sleep(2000);

            // Forzar el clic con JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonEditar);
            System.out.println("Se hizo clic en el botón 'Editar deporte' usando JavaScript.");
        } catch (InterruptedException e) {
            throw new RuntimeException("Error durante la espera antes de hacer clic en el botón 'Editar deporte'.", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'Editar deporte'.", e);
        }
		  String obj = "hace click en el botón editar deporte"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }


    @When("hace click en el botón seleccionar nivel {string}")
    public void hace_click_en_el_boton_seleccionar_nivel(String nivelXPath) throws IOException {
        try {
            // Configura una espera explícita de hasta 60 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

            // Espera hasta que el botón identificado por el XPath sea clicable
            WebElement botonNivel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(nivelXPath)));

            // Desplázate al botón (opcional) y espera 2 segundos
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botonNivel);
            Thread.sleep(2000); // Esperar 2 segundos adicionales antes del clic

            // Haz clic en el botón
            botonNivel.click();
            System.out.println("Se hizo clic en el botón 'Seleccionar nivel' después de esperar 2 segundos.");
        } catch (InterruptedException e) {
            throw new RuntimeException("Error durante la espera de 2 segundos antes de hacer clic en el botón 'Seleccionar nivel'.", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'Seleccionar nivel'.", e);
        }
		  String obj = "hace click en el botón seleccionar nivel"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }


    @When("hace click en el botón seleccionar mano {string}")
    public void hace_click_en_el_boton_seleccionar_mano(String manoXPath) throws IOException {
        try {
            // Configura una espera explícita de hasta 60 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

            // Espera hasta que el botón identificado por el XPath sea clicable
            WebElement botonMano = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(manoXPath)));

            // Desplázate al botón (opcional) y espera 2 segundos
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botonMano);
            Thread.sleep(2000); // Esperar 2 segundos adicionales antes del clic

            // Haz clic en el botón
            botonMano.click();
            System.out.println("Se hizo clic en el botón 'Seleccionar mano' después de esperar 2 segundos.");
        } catch (InterruptedException e) {
            throw new RuntimeException("Error durante la espera de 2 segundos antes de hacer clic en el botón 'Seleccionar mano'.", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'Seleccionar mano'.", e);
        }
		  String obj = "hace click en el botón seleccionar mano"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }



    @When("hace click en el botón seleccionar día {string}")
    public void hace_click_en_el_boton_seleccionar_dia(String diaXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement botonDia = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(diaXPath)));
        botonDia.click(); // Hacer clic en el botón "Seleccionar Día"
		  String obj = "hace click en el botón seleccionar día"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @When("hace click en el botón seleccionar día JU")
    public void hace_click_en_el_boton_seleccionar_dia_ju() throws IOException {
        String xpathDiaJU = "//button[contains(@class, 'btn_component_c') and contains(text(), 'JU')]";
        WebElement botonDiaJU = driver.findElement(By.xpath(xpathDiaJU));
        botonDiaJU.click(); // Hacer clic en el botón "JU"
		  String obj = "hace click en el botón seleccionar día JU"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    
    @When("selecciona la hora de inicio {string}")
    public void seleccionar_hora_inicio(String horaInicioXPath) throws IOException {
        WebElement dropdownInicio = driver.findElement(By.xpath(horaInicioXPath));
        dropdownInicio.click(); // Seleccionar hora de inicio
		  String obj = "selecciona la hora de inicio"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }

    @When("selecciona la hora de término {string}")
    public void seleccionar_hora_termino(String horaTerminoXPath) throws IOException {
        WebElement dropdownTermino = driver.findElement(By.xpath(horaTerminoXPath));
        dropdownTermino.click(); // Seleccionar hora de término
		  String obj = "selecciona la hora de término"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @When("selecciona la comuna {string}")
    public void selecciona_la_comuna(String comunaXPath) throws IOException {
        try {
            // Buscar el elemento con el XPath proporcionado
            WebElement comuna = driver.findElement(By.xpath(comunaXPath));
            
            // Esperar a que sea clicable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.elementToBeClickable(comuna));
            
            // Hacer clic en la comuna
            comuna.click();
            System.out.println("Comuna seleccionada exitosamente: " + comunaXPath);
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar seleccionar la comuna: " + e.getMessage());
        }
		  String obj = "selecciona la comuna"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }


    
    @When("ingresa la comuna {string} en el campo de comuna {string}")
    public void ingresa_comuna(String comuna, String comunaXPath) throws IOException {
        try {
            // Buscar el campo de entrada de la comuna con el XPath proporcionado
            WebElement campoComuna = driver.findElement(By.xpath(comunaXPath));

            // Esperar a que el campo esté visible y listo para ser utilizado
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOf(campoComuna));

            // Limpiar el campo (opcional) e ingresar la comuna
            campoComuna.clear();
            campoComuna.sendKeys(comuna);

            System.out.println("Comuna ingresada exitosamente: " + comuna);
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar ingresar la comuna: " + e.getMessage());
        }
		  String obj = "ingresa la comuna {string} en el campo de comuna {string}"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @When("selecciona la comuna de la lista {string}")
    public void selecciona_la_comuna_de_la_lista(String comunaXPath) throws IOException {
        try {
            // Esperar a que el elemento esté presente y visible en el DOM
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            WebElement comuna = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(comunaXPath)));

            // Esperar a que el elemento sea clicable
            wait.until(ExpectedConditions.elementToBeClickable(comuna));

            // Hacer clic en la comuna
            comuna.click();
            System.out.println("Comuna seleccionada exitosamente: " + comunaXPath);
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar seleccionar la comuna de la lista: " + e.getMessage());
        }
		  String obj = "selecciona la comuna de la lista"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @When("hace click en el botón siguiente {string}")
    public void hace_click_en_el_boton_siguiente(String siguienteXPath) throws IOException {
        try {
            // Buscar el elemento con el XPath proporcionado
            WebElement botonSiguiente = driver.findElement(By.xpath(siguienteXPath));
            
            // Desplazar la vista hacia el botón para asegurarse de que está visible
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botonSiguiente);
            
            // Esperar hasta 60 segundos para que el elemento esté habilitado y clickeable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.elementToBeClickable(botonSiguiente));
            
            // Verificar si el botón está habilitado antes de hacer clic
            if (botonSiguiente.isEnabled()) {
                botonSiguiente.click();
                System.out.println("Botón 'Siguiente' seleccionado exitosamente: " + siguienteXPath);
            } else {
                System.out.println("El botón 'Siguiente' no está habilitado.");
            }
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(siguienteXPath)));
                System.out.println("Botón 'Siguiente' seleccionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón 'Siguiente' con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón 'Siguiente': " + e.getMessage());
        }
		  String obj = "hace click en el botón siguiente"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @When("hace click en el boton hombre {string}")
    public void hace_click_en_el_boton_hombre(String hombreXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
            WebElement botonHombre = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(hombreXPath))); // Esperar hasta que el botón "Hombre" sea clicable
            
            botonHombre.click(); // Hacer clic en el botón "Hombre"
            System.out.println("Botón 'Hombre' seleccionado exitosamente: " + hombreXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(hombreXPath)));
                System.out.println("Botón 'Hombre' seleccionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón 'Hombre' con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón 'Hombre': " + e.getMessage());
        }
		  String obj = "hace click en el boton hombre"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }

    @When("hace click en el botón rango {string}")
    public void hace_click_en_el_boton_rango(String rangoXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
            WebElement botonRango = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(rangoXPath))); // Esperar hasta que el botón sea clicable
            
            botonRango.click(); // Hacer clic en el botón
            System.out.println("Botón de rango seleccionado exitosamente: " + rangoXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(rangoXPath)));
                System.out.println("Botón de rango seleccionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón de rango con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón de rango: " + e.getMessage());
        }
		  String obj = "hace click en el botón rango"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }

    @When("hace click en el botón estilo de juego {string}")
    public void hace_click_en_el_boton_estilo_de_juego(String estiloJuegoXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos

            // Esperar hasta que desaparezca el overlay de carga si está presente
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loadingOverlay")));

            // Esperar hasta que el botón "Estilo de juego" sea clickeable
            WebElement botonEstiloJuego = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(estiloJuegoXPath)));
            
            botonEstiloJuego.click(); // Hacer clic en el botón "Estilo de juego"
            System.out.println("Botón 'Estilo de juego' seleccionado exitosamente: " + estiloJuegoXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript si está interceptado
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(estiloJuegoXPath)));
                System.out.println("Botón 'Estilo de juego' seleccionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón 'Estilo de juego' con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón 'Estilo de juego': " + e.getMessage());
        }
		  String obj = "hace click en el botón estilo de juego"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @When("hace click en el botón mano {string}")
    public void hace_click_en_el_boton_mano(String manoXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos

            // Esperar hasta que desaparezca el overlay de carga si está presente
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loadingOverlay")));

            // Esperar hasta que el botón "Mano" sea clickeable
            WebElement botonMano = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(manoXPath)));
            
            botonMano.click(); // Hacer clic en el botón "Mano"
            System.out.println("Botón 'Mano' seleccionado exitosamente: " + manoXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript si está interceptado
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(manoXPath)));
                System.out.println("Botón 'Mano' seleccionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón 'Mano' con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón 'Mano': " + e.getMessage());
        }
		  String obj = "hace click en el botón mano"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("hace click en el botón terminar perfil {string}")
    public void hace_click_en_el_boton_terminar_perfil(String terminarPerfilXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos

            // Esperar hasta que desaparezca el overlay de carga si está presente
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loadingOverlay")));

            // Esperar hasta que el botón "Terminar perfil" sea clickeable
            WebElement botonTerminarPerfil = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(terminarPerfilXPath)));
            
            botonTerminarPerfil.click(); // Hacer clic en el botón "Terminar perfil"
            System.out.println("Botón 'Terminar perfil' seleccionado exitosamente: " + terminarPerfilXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript si está interceptado
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(terminarPerfilXPath)));
                System.out.println("Botón 'Terminar perfil' seleccionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón 'Terminar perfil' con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón 'Terminar perfil': " + e.getMessage());
        }
		  String obj = "hace click en el botón terminar perfil"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("verifica mensaje {string}")
    public void verifica_mensaje(String mensajeXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Esperar hasta 30 segundos
            WebElement mensajeElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mensajeXPath))); // Esperar a que el elemento esté visible

            String mensajeTexto = mensajeElemento.getText();
            if (mensajeTexto.equals("Tu perfil ha sido actualizado con éxito.")) {
                System.out.println("Mensaje de éxito verificado correctamente.");
            } else {
                throw new AssertionError("El mensaje no coincide. Se esperaba: 'Tu perfil ha sido actualizado con éxito.', pero se encontró: '" + mensajeTexto + "'");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar el mensaje de éxito: " + e.getMessage());
        }
		  String obj = "verifica mensaje"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("presiona botón ok {string}")
    public void presiona_boton_ok(String okButtonXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Esperar hasta 30 segundos
            WebElement botonOk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(okButtonXPath))); // Esperar a que el botón "OK" sea clickeable
            
            botonOk.click(); // Hacer clic en el botón "OK"
            System.out.println("Botón 'OK' presionado exitosamente: " + okButtonXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript si está interceptado
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(okButtonXPath)));
                System.out.println("Botón 'OK' presionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón 'OK' con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón 'OK': " + e.getMessage());
        }
		  String obj = "presiona botón ok"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("presiona botón desafiar {string}")
    public void presiona_boton_desafiar(String desafiarButtonXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Esperar hasta 30 segundos
            WebElement botonDesafiar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(desafiarButtonXPath))); // Esperar a que el botón "Desafiar" sea clickeable
            
            botonDesafiar.click(); // Hacer clic en el botón "Desafiar"
            System.out.println("Botón 'Desafiar' presionado exitosamente: " + desafiarButtonXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript si está interceptado
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(desafiarButtonXPath)));
                System.out.println("Botón 'Desafiar' presionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón 'Desafiar' con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón 'Desafiar': " + e.getMessage());
        }
		  String obj = "presiona botón desafiar"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("elige hora de desafio {string}")
    public void elige_hora_de_desafio(String horaDesafioXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
            WebElement horaDesafio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(horaDesafioXPath))); // Esperar a que el elemento esté visible
            
            horaDesafio.click(); // Hacer clic en el elemento para elegir la hora de desafío
            System.out.println("Hora de desafío seleccionada exitosamente: " + horaDesafioXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript si está interceptado
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(horaDesafioXPath)));
                System.out.println("Hora de desafío seleccionada exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al seleccionar la hora de desafío con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar seleccionar la hora de desafío: " + e.getMessage());
        }
		  String obj = "elige hora de desafio"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("presiona confirmacion {string}")
    public void presiona_confirmacion(String confirmacionButtonXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
            WebElement botonConfirmacion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(confirmacionButtonXPath))); // Esperar a que el botón de confirmación sea clickeable
            
            botonConfirmacion.click(); // Hacer clic en el botón de confirmación
            System.out.println("Botón de confirmación presionado exitosamente: " + confirmacionButtonXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript si está interceptado
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(confirmacionButtonXPath)));
                System.out.println("Botón de confirmación presionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón de confirmación con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón de confirmación: " + e.getMessage());
        }
		  String obj = "presiona confirmacion"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("mensaje confirma desafio {string}")
    public void mensaje_confirma_desafio(String mensajeXPath) throws TimeoutException, IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Esperar hasta 30 segundos
            WebElement mensajeElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mensajeXPath))); // Esperar a que el pop-up esté visible

            String mensajeTexto = mensajeElemento.getText();
            String mensajeEsperado = "El desafío ha sido confirmado"; // Cambia este texto al mensaje exacto que esperas

            if (mensajeTexto.contains(mensajeEsperado)) { // Validar que el mensaje contenga el texto esperado
                System.out.println("Mensaje de confirmación de desafío verificado correctamente.");
            } else {
                throw new AssertionError("El mensaje no coincide. Se esperaba que contuviera: '" + mensajeEsperado + "', pero se encontró: '" + mensajeTexto + "'");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar el mensaje de confirmación de desafío: " + e.getMessage());
        }
		  String obj = "mensaje confirma desafio"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }

    @And("presiona botón desafiar a {string}")
    public void presiona_boton_desafiar_a(String desafiarAXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Esperar hasta 30 segundos
            WebElement botonDesafiarA = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(desafiarAXPath))); // Esperar a que el botón "Desafiar a" sea clickeable
            
            botonDesafiarA.click(); // Hacer clic en el botón "Desafiar a"
            System.out.println("Botón 'Desafiar a' presionado exitosamente: " + desafiarAXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript si está interceptado
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(desafiarAXPath)));
                System.out.println("Botón 'Desafiar a' presionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón 'Desafiar a' con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón 'Desafiar a': " + e.getMessage());
        }
		  String obj = "presiona botón desafiar a"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("presiona anular {string}")
    public void presiona_anular(String anularButtonXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
            WebElement botonAnular = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(anularButtonXPath))); // Esperar a que el botón "Anular" sea clickeable
            
            botonAnular.click(); // Hacer clic en el botón "Anular"
            System.out.println("Botón 'Anular' presionado exitosamente: " + anularButtonXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript si está interceptado
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(anularButtonXPath)));
                System.out.println("Botón 'Anular' presionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón 'Anular' con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón 'Anular': " + e.getMessage());
        }
		  String obj = "presiona anular"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("confirma anulacion {string}")
    public void confirma_anulacion(String confirmacionAnulacionXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
            WebElement botonConfirmarAnulacion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(confirmacionAnulacionXPath))); // Esperar a que el botón de confirmación de anulación sea clickeable
            
            botonConfirmarAnulacion.click(); // Hacer clic en el botón de confirmación de anulación
            System.out.println("Botón de confirmación de anulación presionado exitosamente: " + confirmacionAnulacionXPath);
        } catch (ElementClickInterceptedException e) {
            System.err.println("Elemento interceptado por otro elemento, intentando con JavaScript.");
            try {
                // Intentar hacer clic con JavaScript si está interceptado
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath(confirmacionAnulacionXPath)));
                System.out.println("Botón de confirmación de anulación presionado exitosamente usando JavaScript.");
            } catch (Exception jsException) {
                throw new RuntimeException("Error al hacer clic en el botón de confirmación de anulación con JavaScript: " + jsException.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar hacer clic en el botón de confirmación de anulación: " + e.getMessage());
        }
		  String obj = "confirma anulacion"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("ver mis reservas {string}")
    public void ver_mis_reservas(String reservasXPath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Esperar hasta 30 segundos
            WebElement reservasElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(reservasXPath))); // Esperar a que el elemento esté visible

            String textoElemento = reservasElemento.getText();
            String textoEsperado = "Mis reservas";

            if (textoElemento.equals(textoEsperado)) {
                System.out.println("Texto verificado correctamente: 'Mis reservas'.");
            } else {
                throw new AssertionError("El texto no coincide. Se esperaba: '" + textoEsperado + "', pero se encontró: '" + textoElemento + "'");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar el texto 'Mis reservas': " + e.getMessage());
        }
		  String obj = "ver mis reservas"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("presiona en reservas pasadas {string}")
    public void presiona_en_reservas_pasadas(String reservasPasadasXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement botonReservasPasadas = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reservasPasadasXPath))); // Esperar a que el botón de reservas pasadas sea clicable
            botonReservasPasadas.click(); // Hacer clic en el botón de reservas pasadas
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el botón de reservas pasadas con XPath: " + reservasPasadasXPath);
            throw e;
        }
		  String obj = "presiona en reservas pasadas"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("selecciona mes reserva {string}")
    public void selecciona_mes_reserva(String mesXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement mesSelectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mesXPath)));
            Select mesSelect = new Select(mesSelectElement);
            mesSelect.selectByVisibleText("Agosto"); // Seleccionar el mes de Agosto
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo seleccionar el mes con XPath: " + mesXPath);
            throw e;
        }
		  String obj = "selecciona mes reserva"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("selecciona año reserva {string}")
    public void selecciona_año_reserva(String añoXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement añoSelectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(añoXPath)));
            Select añoSelect = new Select(añoSelectElement);
            añoSelect.selectByVisibleText("2024"); // Seleccionar el año 2024
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo seleccionar el año con XPath: " + añoXPath);
            throw e;
        }
		  String obj = "selecciona año reserva"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("presionar botón clubes {string}")
    public void presionar_boton_clubes(String botonClubesXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement botonClubes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(botonClubesXPath))); // Esperar a que el botón de clubes sea clicable
            botonClubes.click(); // Hacer clic en el botón de clubes
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el botón de clubes con XPath: " + botonClubesXPath);
            throw e;
        }
		  String obj = "presionar botón clubes"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("presionar filtro club {string}")
    public void presionar_filtro_club(String filtroClubXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement botonFiltroClub = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(filtroClubXPath))); // Esperar a que el botón de filtro de club sea clicable
            botonFiltroClub.click(); // Hacer clic en el botón de filtro de club
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el botón de filtro de club con XPath: " + filtroClubXPath);
            throw e;
        }
		  String obj = "presionar filtro club"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }

    @When("elegir comunas {string}")
    public void elegir_comunas(String comunaXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Esperar a que el elemento que represente la comuna esté clicable
            WebElement comunaElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(comunaXPath)));
            comunaElement.click(); // Hacer clic en el elemento de la comuna
            
            System.out.println("Se eligió la comuna con XPath: " + comunaXPath);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el elemento de la comuna con XPath: " + comunaXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        }
		  String obj = "elegir comunas"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }

    @Then("elegir actividad {string}")
    public void elegir_actividad(String actividadXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Esperar a que el elemento de actividad sea clicable
            WebElement actividadElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(actividadXPath)));
            actividadElement.click(); // Hacer clic en el elemento de la actividad
            
            System.out.println("Se eligió la actividad con XPath: " + actividadXPath);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el elemento de la actividad con XPath: " + actividadXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        }
		  String obj = "elegir actividad"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("desplegar {string}")
    public void desplegar(String opcionXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Esperar a que el elemento del desplegable sea clicable
            WebElement opcionElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(opcionXPath)));
            opcionElement.click(); // Hacer clic en la opción del desplegable
            
            System.out.println("Se seleccionó la opción del desplegable con XPath: " + opcionXPath);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en la opción del desplegable con XPath: " + opcionXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        }
		  String obj = "desplegar"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("aplicar filtros {string}")
    public void aplicar_filtros(String botonFiltrosXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Esperar a que el botón de aplicar filtros sea clicable
            WebElement botonFiltros = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(botonFiltrosXPath)));
            botonFiltros.click(); // Hacer clic en el botón de aplicar filtros
            
            System.out.println("Se hizo clic en el botón de aplicar filtros con XPath: " + botonFiltrosXPath);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el botón de aplicar filtros con XPath: " + botonFiltrosXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        }
		  String obj = "aplicar filtros"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("editar datos personales {string}")
    public void editar_datos_personales(String editarDatosXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Esperar hasta que el overlay de carga desaparezca
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loadingOverlay')]")));

            // Esperar a que el elemento de editar datos personales sea clicable
            WebElement editarDatosElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(editarDatosXPath)));
            editarDatosElement.click(); // Hacer clic en el enlace para editar datos personales

            System.out.println("Se hizo clic en el enlace para editar datos personales con XPath: " + editarDatosXPath);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el enlace de editar datos personales con XPath: " + editarDatosXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.err.println("ElementClickInterceptedException: Otro elemento estaba bloqueando el clic en el enlace de editar datos personales con XPath: " + editarDatosXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        }
		  String obj = "editar datos personales"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("descartar prime {string}")
    public void descartar_prime(String descartarPrimeXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Esperar a que el elemento de descartar prime sea clicable
            WebElement descartarPrimeElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(descartarPrimeXPath)));
            descartarPrimeElement.click(); // Hacer clic en el enlace para descartar prime
            
            System.out.println("Se hizo clic en el enlace para descartar prime con XPath: " + descartarPrimeXPath);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el enlace para descartar prime con XPath: " + descartarPrimeXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        }
		  String obj = "descartar prime"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("ver lista amigos {string}")
    public void ver_lista_amigos(String verListaAmigosXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Esperar hasta que el overlay de carga desaparezca
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loadingOverlay')]")));

            // Esperar a que el elemento de editar datos personales sea clicable
            WebElement editarDatosElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(verListaAmigosXPath)));
            editarDatosElement.click(); // Hacer clic en el enlace para editar datos personales

            System.out.println("Se hizo clic en el enlace para editar datos personales con XPath: " + verListaAmigosXPath);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el enlace de editar datos personales con XPath: " + verListaAmigosXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.err.println("ElementClickInterceptedException: Otro elemento estaba bloqueando el clic en el enlace de editar datos personales con XPath: " + verListaAmigosXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        }
		  String obj = "ver lista amigos"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("btn tarjetas {string}")
    public void btn_tarjetas(String btnTarjetasXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Esperar hasta que el overlay de carga desaparezca (si existe)
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loadingOverlay')]")));

            // Esperar a que el botón de tarjetas sea clicable
            WebElement btnTarjetasElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btnTarjetasXPath)));

            // Desplazarse al elemento antes de hacer clic (opcional si está oculto o fuera de vista)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", btnTarjetasElement);

            // Hacer clic en el botón de tarjetas
            btnTarjetasElement.click();

            System.out.println("Se hizo clic en el botón de tarjetas con XPath: " + btnTarjetasXPath);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el botón de tarjetas con XPath: " + btnTarjetasXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.err.println("ElementClickInterceptedException: Otro elemento estaba bloqueando el clic en el botón de tarjetas con XPath: " + btnTarjetasXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        }
		  String obj = "btn tarjetas"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("seleccionar pais {string}")
    public void seleccionar_pais(String seleccionarPaisXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Esperar hasta que el overlay de carga desaparezca (si existe)
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loadingOverlay')]")));

            // Esperar a que el elemento "Seleccionar País" sea clicable
            WebElement seleccionarPaisElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(seleccionarPaisXPath)));

            // Desplazarse al elemento antes de hacer clic (opcional si está oculto o fuera de vista)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", seleccionarPaisElement);

            // Hacer clic en el elemento "Seleccionar País"
            seleccionarPaisElement.click();

            System.out.println("Se hizo clic en el enlace para seleccionar país con XPath: " + seleccionarPaisXPath);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el enlace para seleccionar país con XPath: " + seleccionarPaisXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.err.println("ElementClickInterceptedException: Otro elemento estaba bloqueando el clic en el enlace para seleccionar país con XPath: " + seleccionarPaisXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        }
		  String obj = "seleccionar pais"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("elegir pais para utilizar la app {string}")
    public void elegir_pais_para_utilizar_la_app(String elegirPaisXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Esperar hasta que el overlay de carga desaparezca (si existe)
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loadingOverlay')]")));

            // Esperar a que el elemento "Elegir País para utilizar la app" sea clicable
            WebElement elegirPaisElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elegirPaisXPath)));

            // Desplazarse al elemento antes de hacer clic (opcional si está oculto o fuera de vista)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", elegirPaisElement);

            // Hacer clic en el elemento "Elegir País para utilizar la app"
            elegirPaisElement.click();

            System.out.println("Se hizo clic en el enlace para elegir país para utilizar la app con XPath: " + elegirPaisXPath);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el enlace para elegir país con XPath: " + elegirPaisXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.err.println("ElementClickInterceptedException: Otro elemento estaba bloqueando el clic en el enlace para elegir país con XPath: " + elegirPaisXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        }
		  String obj = "elegir pais para utilizar la app"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("seleccionar idioma {string}")
    public void seleccionar_idioma(String seleccionarIdiomaXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Esperar hasta que el overlay de carga desaparezca (si existe)
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loadingOverlay')]")));

            // Esperar a que el elemento "Seleccionar Idioma" sea clicable
            WebElement seleccionarIdiomaElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(seleccionarIdiomaXPath)));

            // Desplazarse al elemento antes de hacer clic (opcional si está oculto o fuera de vista)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", seleccionarIdiomaElement);

            // Hacer clic en el elemento "Seleccionar Idioma"
            seleccionarIdiomaElement.click();

            System.out.println("Se hizo clic en el enlace para seleccionar idioma con XPath: " + seleccionarIdiomaXPath);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el enlace para seleccionar idioma con XPath: " + seleccionarIdiomaXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.err.println("ElementClickInterceptedException: Otro elemento estaba bloqueando el clic en el enlace para seleccionar idioma con XPath: " + seleccionarIdiomaXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        }
		  String obj = "seleccionar idioma"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("seleccionar ingles {string}")
    public void seleccionar_ingles(String seleccionarInglesXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Esperar hasta que el overlay de carga desaparezca (si existe)
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loadingOverlay')]")));

            // Esperar a que el elemento "Seleccionar Inglés" sea clicable
            WebElement seleccionarInglesElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(seleccionarInglesXPath)));

            // Desplazarse al elemento antes de hacer clic (opcional si está oculto o fuera de vista)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", seleccionarInglesElement);

            // Hacer clic en el elemento "Seleccionar Inglés"
            seleccionarInglesElement.click();

            System.out.println("Se hizo clic en el enlace para seleccionar inglés con XPath: " + seleccionarInglesXPath);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el enlace para seleccionar inglés con XPath: " + seleccionarInglesXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.err.println("ElementClickInterceptedException: Otro elemento estaba bloqueando el clic en el enlace para seleccionar inglés con XPath: " + seleccionarInglesXPath);
            throw e; // Lanzar la excepción para manejar el fallo adecuadamente
        }
		  String obj = "seleccionar ingles"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("seleccionar cambiar contrasena {string}")
    public void seleccionar_cambiar_contrasena(String cambiarContrasenaXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        // Intentar varias veces para asegurarse de que el elemento se puede clicar
        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede por overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement cambiarContrasenaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cambiarContrasenaXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", cambiarContrasenaElement);
                
                // Esperar a que el elemento sea clicable
                wait.until(ExpectedConditions.elementToBeClickable(cambiarContrasenaElement)).click();
                
                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'cambiar contraseña' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar hacer clic en el botón cambiar contraseña: " + e.getMessage());
            }
            
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'cambiar contraseña' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "seleccionar cambiar contrasena"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }


    @And("nueva contrasena {string} el texto {string}")
    public void nueva_contrasena(String newPasswordXPath, String texto) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newPasswordXPath))); // Esperar el campo de nueva contraseña
        inputField.clear(); // Limpiar el campo
        inputField.sendKeys(texto); // Ingresar texto
        
        // Capturar evidencia
        String obj = "nueva contrasena"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }

    @And("repite nuevo password {string} el texto {string}")
    public void repite_nuevo_password(String repeatPasswordXPath, String texto) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(repeatPasswordXPath))); // Esperar el campo de repetir contraseña
        inputField.clear(); // Limpiar el campo
        inputField.sendKeys(texto); // Ingresar texto
        
        // Capturar evidencia
        String obj = "repite nuevo password"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("confirma cambio password {string}")
    public void confirma_cambio_password(String confirmarCambioPasswordXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement botonConfirmar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(confirmarCambioPasswordXPath))); // Esperar a que el botón sea clicable
        botonConfirmar.click(); // Hacer clic en el botón para confirmar el cambio de contraseña

        // Capturar evidencia
        String obj = "confirma cambio password"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("seleccionar notificaciones {string}")
    public void seleccionar_notificaciones(String notificacionesXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement notificacionesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(notificacionesXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", notificacionesElement);

                // Esperar a que el elemento sea clicable
                wait.until(ExpectedConditions.elementToBeClickable(notificacionesElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'notificaciones' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar hacer clic en las notificaciones: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en el botón 'notificaciones' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "seleccionar notificaciones"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @And("preferencia notificacion {string}")
    public void preferencia_notificacion(String preferenciaXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement preferenciaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(preferenciaXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", preferenciaElement);

                // Esperar a que el elemento sea clicable
                wait.until(ExpectedConditions.elementToBeClickable(preferenciaElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'preferencia notificacion' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar hacer clic en la preferencia de notificación: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en 'preferencia notificacion' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "preferencia notificacion"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("mover switch {string}")
    public void mover_switch(String switchXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento del switch sea visible y desplazarse hacia él
                WebElement switchElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(switchXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", switchElement);

                // Esperar a que el elemento sea clicable
                wait.until(ExpectedConditions.elementToBeClickable(switchElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando mover el 'switch' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar mover el switch: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en el switch después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "mover switch"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("selecciona lista espera {string}")
    public void selecciona_lista_espera(String listaEsperaXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement listaEsperaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(listaEsperaXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", listaEsperaElement);

                // Esperar a que el elemento sea clicable
                wait.until(ExpectedConditions.elementToBeClickable(listaEsperaElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'selecciona lista espera' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar seleccionar lista de espera: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en 'selecciona lista espera' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "selecciona lista espera"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("selecciona ver convenios {string}")
    public void selecciona_ver_convenios(String verConveniosXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement verConveniosElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(verConveniosXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", verConveniosElement);

                // Esperar a que el elemento sea clicable
                wait.until(ExpectedConditions.elementToBeClickable(verConveniosElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'selecciona ver convenios' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar seleccionar ver convenios: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en 'selecciona ver convenios' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "selecciona ver convenios"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("selecciona mis cupones {string}")
    public void selecciona_mis_cupones(String misCuponesXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement misCuponesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(misCuponesXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", misCuponesElement);

                // Esperar a que el elemento sea clicable
                wait.until(ExpectedConditions.elementToBeClickable(misCuponesElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'selecciona mis cupones' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar seleccionar mis cupones: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en 'selecciona mis cupones' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "selecciona mis cupones"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("selecciona preguntas frecuentes {string}")
    public void selecciona_preguntas_frecuentes(String preguntasFrecuentesXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement preguntasFrecuentesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(preguntasFrecuentesXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", preguntasFrecuentesElement);

                // Esperar a que el elemento sea clicable y hacer clic
                wait.until(ExpectedConditions.elementToBeClickable(preguntasFrecuentesElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'selecciona preguntas frecuentes' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar seleccionar preguntas frecuentes: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en 'selecciona preguntas frecuentes' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "selecciona preguntas frecuentes"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("selecciona cerrar sesion {string}")
    public void selecciona_cerrar_sesion(String cerrarSesionXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement cerrarSesionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cerrarSesionXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", cerrarSesionElement);

                // Esperar a que el elemento sea clicable y hacer clic
                wait.until(ExpectedConditions.elementToBeClickable(cerrarSesionElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'selecciona cerrar sesion' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar seleccionar cerrar sesión: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en 'selecciona cerrar sesion' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "selecciona cerrar sesion"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("presiona ver inscripciones {string}")
    public void presiona_ver_inscripciones(String verInscripcionesXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement verInscripcionesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(verInscripcionesXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", verInscripcionesElement);

                // Esperar a que el elemento sea clicable y hacer clic
                wait.until(ExpectedConditions.elementToBeClickable(verInscripcionesElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'presiona ver inscripciones' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar presionar ver inscripciones: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en 'presiona ver inscripciones' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "presiona ver inscripciones"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("presiona calificar {string}")
    public void presiona_calificar(String calificarXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement calificarElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(calificarXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", calificarElement);

                // Esperar a que el elemento sea clicable y hacer clic
                wait.until(ExpectedConditions.elementToBeClickable(calificarElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'presiona calificar' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar presionar calificar: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en 'presiona calificar' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "presiona_calificar"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("presiona agrega un nuevo deporte {string}")
    public void presiona_agrega_un_nuevo_deporte(String agregaDeporteXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement agregaDeporteElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(agregaDeporteXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", agregaDeporteElement);

                // Esperar a que el elemento sea clicable y hacer clic
                wait.until(ExpectedConditions.elementToBeClickable(agregaDeporteElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'presiona agrega un nuevo deporte' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar presionar agrega un nuevo deporte: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en 'presiona agrega un nuevo deporte' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "presiona_agrega_un_nuevo_deporte"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("presiona boton comencemos {string}")
    public void presiona_boton_comencemos(String botonXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement botonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(botonXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botonElement);

                // Esperar a que el elemento sea clicable y hacer clic
                wait.until(ExpectedConditions.elementToBeClickable(botonElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'presiona boton comencemos' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar presionar boton comencemos: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en 'presiona boton comencemos' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "presiona_boton_comencemos"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("presiona flecha volver {string}")
    public void presiona_flecha_volver(String flechaVolverXPath) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int retryCount = 0;
        boolean isClicked = false;

        // Intentar hacer clic hasta 5 veces si no se puede debido a overlays u otros problemas
        while (retryCount < 5 && !isClicked) {
            try {
                // Esperar a que el overlay de carga desaparezca
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

                // Esperar a que el elemento sea visible y desplazarse hacia él
                WebElement flechaVolverElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(flechaVolverXPath)));
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", flechaVolverElement);

                // Esperar a que el elemento sea clicable y hacer clic
                wait.until(ExpectedConditions.elementToBeClickable(flechaVolverElement)).click();

                isClicked = true; // Si el clic es exitoso, salir del bucle

            } catch (ElementClickInterceptedException | org.openqa.selenium.TimeoutException e) {
                retryCount++;
                System.out.println("Intentando hacer clic en 'presiona flecha volver' nuevamente. Intento: " + retryCount);
            } catch (Exception e) {
                throw new RuntimeException("Error al intentar presionar flecha volver: " + e.getMessage());
            }
        }

        // Si después de varios intentos no se pudo hacer clic, lanzar excepción
        if (!isClicked) {
            throw new RuntimeException("No se pudo hacer clic en 'presiona flecha volver' después de múltiples intentos.");
        }

        // Esperar un segundo antes de capturar la evidencia
        try {
            Thread.sleep(1000); // Esperar 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Capturar evidencia
        String obj = "presiona_flecha_volver"; // Nombre de la foto
        Utility.captureScreenShot(driver, "evidencias\\" + obj + " " + Utility.GetTimeStampValue() + ".png");
    }
    @Then("ver semana siguiente {string}")
    public void verSemanaSiguiente(String xpath) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Espera a que desaparezca la superposición de carga
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay.loadingOverlayWhite")));

            // Espera a que el botón sea clicable
            WebElement botonSemanaSiguiente = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el botón
            botonSemanaSiguiente.click();
            System.out.println("Se ha hecho clic en el botón de 'ver semana siguiente'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'ver semana siguiente'.", e);
        }
		  String obj = "ver semana siguiente"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("click en clases y escuelas {string}")
    public void clickEnClasesYEscuelas(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el elemento identificado por el XPath sea clicable
            WebElement elementoClasesYEscuelas = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el elemento
            elementoClasesYEscuelas.click();
            System.out.println("Se ha hecho clic en 'clases y escuelas'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el elemento 'clases y escuelas'.", e);
        }
		  String obj = "click en clases y escuelas"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("seleccionar escuela {string}")
    public void seleccionarEscuela(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el elemento identificado por el XPath sea clicable
            WebElement escuela = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el elemento
            escuela.click();
            System.out.println("Se ha seleccionado la escuela.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el elemento 'seleccionar escuela'.", e);
        }
		  String obj = "seleccionar escuela"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("elige quiero mes gratis {string}")
    public void eligeQuieroMesGratis(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el botón identificado por el XPath sea clicable
            WebElement botonMesGratis = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el botón
            botonMesGratis.click();
            System.out.println("Se ha hecho clic en 'Quiero mes gratis'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'Quiero mes gratis'.", e);
        }
		  String obj = "elige quiero mes gratis"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("suscribir a prime {string}")
    public void suscribirAPrime(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el botón identificado por el XPath sea clicable
            WebElement botonSuscribirPrime = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el botón
            botonSuscribirPrime.click();
            System.out.println("Se ha hecho clic en 'Suscribir a Prime'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'Suscribir a Prime'.", e);
        }
		  String obj = "suscribir a prime"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("agregar tarjeta nueva {string}")
    public void agregarTarjetaNueva(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el elemento identificado por el XPath sea clicable
            WebElement botonAgregarTarjeta = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el botón
            botonAgregarTarjeta.click();
            System.out.println("Se ha hecho clic en 'Agregar tarjeta nueva'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'Agregar tarjeta nueva'.", e);
        }
		  String obj = "agregar tarjeta nueva"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }

    @When("agrega numero tarjeta {string} el texto {string}")
    public void agregaNumeroTarjeta(String xpath, String texto) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement campoNumeroTarjeta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            campoNumeroTarjeta.clear();
            campoNumeroTarjeta.sendKeys(texto);
            System.out.println("Se ha ingresado el número de tarjeta: " + texto);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al ingresar el número de tarjeta.", e);
        }
		  String obj = "agrega numero tarjeta {string} el texto {string}"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("nombre tarjeta {string} el texto {string}")
    public void nombreTarjeta(String xpath, String texto) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement campoNombreTarjeta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            campoNombreTarjeta.clear();
            campoNombreTarjeta.sendKeys(texto);
            System.out.println("Se ha ingresado el nombre en la tarjeta: " + texto);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al ingresar el nombre en la tarjeta.", e);
        }
		  String obj = "nombre tarjeta {string} el texto {string}"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("fecha expiracion {string} el texto {string}")
    public void fechaExpiracion(String xpath, String texto) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement campoFechaExpiracion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            campoFechaExpiracion.clear();
            campoFechaExpiracion.sendKeys(texto);
            System.out.println("Se ha ingresado la fecha de expiración: " + texto);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al ingresar la fecha de expiración.", e);
        }
		  String obj = "fecha expiracion {string} el texto {string}"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("cvv {string} el texto {string}")
    public void cvv(String xpath, String texto) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement campoCVV = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            campoCVV.clear();
            campoCVV.sendKeys(texto);
            System.out.println("Se ha ingresado el CVV: " + texto);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al ingresar el CVV.", e);
        }
		  String obj = "cvv {string} el texto {string}"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("presiona agregar tarjeta {string}")
    public void presionaAgregarTarjeta(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el botón identificado por el XPath sea clicable
            WebElement botonAgregarTarjeta = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el botón
            botonAgregarTarjeta.click();
            System.out.println("Se ha hecho clic en 'Agregar tarjeta'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'Agregar tarjeta'.", e);
        }
		  String obj = "presiona agregar tarjeta"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("hace click en ver terminos {string}")
    public void haceClickEnVerTerminos(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el enlace identificado por el XPath sea clicable
            WebElement enlaceVerTerminos = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el enlace
            enlaceVerTerminos.click();
            System.out.println("Se ha hecho clic en 'Ver términos'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el enlace 'Ver términos'.", e);
        }
		  String obj = "hace click en ver terminos"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("solicita contacto {string}")
    public void solicitaContacto(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el botón esté presente
            WebElement botonSolicitarContacto = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

            // Espera 2 segundos antes de intentar el clic
            Thread.sleep(2000);

            // Forzar el clic con JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botonSolicitarContacto);

            System.out.println("Se ha hecho clic en 'Solicitar contacto' usando JavaScript.");
        } catch (InterruptedException e) {
            throw new RuntimeException("Ocurrió un error durante la espera antes de hacer clic.", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'Solicitar contacto'.", e);
        }
		  String obj = "solicita contacto"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }


    @Then("presiona soy administrador {string}")
    public void presionaSoyAdministrador(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el botón identificado por el XPath sea clicable
            WebElement botonSoyAdministrador = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Espera 3 segundos antes de hacer clic en el botón
            Thread.sleep(3000);

            // Haz clic en el botón
            botonSoyAdministrador.click();
            System.out.println("Se ha hecho clic en 'Soy Administrador' después de esperar 3 segundos.");

        } catch (InterruptedException e) {
            throw new RuntimeException("Ocurrió un error durante la espera de 3 segundos antes de hacer clic.", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'Soy Administrador'.", e);
        }
		  String obj = "presiona soy administrador"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("quiero unirme {string}")
    public void quieroUnirme(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el elemento identificado por el XPath sea clicable
            WebElement botonQuieroUnirme = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el botón
            botonQuieroUnirme.click();
            System.out.println("Se ha hecho clic en 'Quiero unirme'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'Quiero unirme'.", e);
        }
		  String obj = "quiero unirme"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }

    @Then("presionar ver ranking {string}")
    public void presionarVerRanking(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el botón identificado por el XPath sea clicable
            WebElement botonVerRanking = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Espera 2 segundos antes de hacer clic
            Thread.sleep(2000);

            // Haz clic en el botón
            botonVerRanking.click();
            System.out.println("Se ha hecho clic en 'Ver Ranking' después de esperar 2 segundos.");

        } catch (InterruptedException e) {
            throw new RuntimeException("Ocurrió un error durante la espera de 2 segundos antes de hacer clic.", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el botón 'Ver Ranking'.", e);
        }
		  String obj = "presionar ver ranking"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Given("al navegar hasta la url base {string}")
    public void alNavegarHastaLaUrlBase(String urlBase) throws IOException {
        try {
            // Navega a la URL base proporcionada
            driver.get(urlBase);
            System.out.println("Se ha navegado correctamente a la URL base: " + urlBase);

            // Configura una espera explícita para verificar que la página haya cargado
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe(urlBase));
            System.out.println("La página base ha cargado correctamente.");
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al navegar a la URL base: " + urlBase, e);
        }
		  String obj = "al navegar hasta la url base"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("elegir apple store {string}")
    public void elegirAppleStore(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el elemento identificado por el XPath sea clicable
            WebElement appleStoreLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el enlace
            appleStoreLink.click();
            System.out.println("Se ha hecho clic en el enlace 'Apple Store'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el enlace 'Apple Store'.", e);
        }
		  String obj = "elegir apple store"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("elegir google play {string}")
    public void elegirGooglePlay(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el enlace identificado por el XPath sea clicable
            WebElement googlePlayLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el enlace
            googlePlayLink.click();
            System.out.println("Se ha hecho clic en el enlace 'Google Play'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el enlace 'Google Play'.", e);
        }
		  String obj = "elegir google play"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @And("elegir huawei store {string}")
    public void elegirHuaweiStore(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el enlace identificado por el XPath sea clicable
            WebElement huaweiStoreLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el enlace
            huaweiStoreLink.click();
            System.out.println("Se ha hecho clic en el enlace 'Huawei Store'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el enlace 'Huawei Store'.", e);
        }
		  String obj = "elegir huawei store"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("seleccionar brasil {string}")
    public void seleccionarBrasil(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el elemento identificado por el XPath sea clicable
            WebElement brasilLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el enlace o imagen de Brasil
            brasilLink.click();
            System.out.println("Se ha hecho clic en el enlace o imagen de 'Brasil'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el enlace o imagen de 'Brasil'.", e);
        }
		  String obj = "seleccionar brasils"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("elegir argentina {string}")
    public void elegirArgentina(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el elemento identificado por el XPath sea clicable
            WebElement argentinaLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el enlace o imagen de Argentina
            argentinaLink.click();
            System.out.println("Se ha hecho clic en el enlace o imagen de 'Argentina'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el enlace o imagen de 'Argentina'.", e);
        }
		  String obj = "elegir argentina"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("elegir colombia {string}")
    public void elegirColombia(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el elemento identificado por el XPath sea clicable
            WebElement colombiaLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el enlace o imagen de Colombia
            colombiaLink.click();
            System.out.println("Se ha hecho clic en el enlace o imagen de 'Colombia'.");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el enlace o imagen de 'Colombia'.", e);
        }
		  String obj = "elegir colombia"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("elegir peru {string}")
    public void elegirPeru(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el elemento identificado por el XPath sea clicable
            WebElement peruLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el enlace o imagen de Perú
            peruLink.click();
            System.out.println("Se ha hecho clic en el enlace o imagen de 'Perú'.");

       } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el enlace o imagen de 'Perú'.", e);
        }
		  String obj = "elegir peru"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("elegir usa {string}")
    public void elegirUsa(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el elemento identificado por el XPath sea clicable
            WebElement usaLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el enlace o imagen de USA
            usaLink.click();
            System.out.println("Se ha hecho clic en el enlace o imagen de 'USA'.");

      } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el enlace o imagen de 'USA'.", e);
        }
		  String obj = "verificar que el elemento contenga la palabra Tenis"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }
    @Then("elegir mexico {string}")
    public void elegirMexico(String xpath) throws IOException {
        try {
            // Configura una espera explícita de hasta 10 segundos
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Espera a que el elemento identificado por el XPath sea clicable
            WebElement mexicoLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Haz clic en el enlace o imagen de México
            mexicoLink.click();
            System.out.println("Se ha hecho clic en el enlace o imagen de 'México'.");

       } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al interactuar con el enlace o imagen de 'México'.", e);
        }
		  String obj = "elegir mexico"; //nombre de la foto 
	        Utility.captureScreenShot(driver,"evidencias\\"+obj+" "+Utility.GetTimeStampValue()+".png");
	
    }

}
