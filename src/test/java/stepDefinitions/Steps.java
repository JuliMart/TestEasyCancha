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
    public void al_navegar_hasta_la_url(String url) {
        driver.get(url); // Navegar a la URL dada
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='book-views']"))); // Esperar a que un elemento sea visible
    }

    @And("hacer click en el botón {string}")
    public void hacer_click_en_el_boton(String botonXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement boton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(botonXPath))); // Esperar a que el botón sea clicable
        boton.click(); // Hacer clic en el botón
    }

    @When("coloca en el campo usuario {string} el texto {string}")
    public void coloca_en_el_campo_usuario_el_texto(String usuarioXPath, String texto) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(usuarioXPath))); // Esperar el campo de usuario
        inputField.clear(); // Limpiar el campo
        inputField.sendKeys(texto); // Ingresar texto
    }

    @When("coloca en el campo password {string} el texto {string}")
    public void coloca_en_el_campo_password_el_texto(String passwordXPath, String texto) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(passwordXPath))); // Esperar el campo de contraseña
        inputField.clear(); // Limpiar el campo
        inputField.sendKeys(texto); // Ingresar texto
    }

    @And("hace click en el boton {string}")
    public void hace_click_en_el_boton(String botonXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement boton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(botonXPath))); // Esperar que el botón sea clicable
        boton.click(); // Hacer clic en el botón
    }

    @Then("presenta el mensaje de bienvenida {string}")
    public void presenta_el_mensaje_de_bienvenida(String mensajeXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mensajeXPath))); // Esperar el mensaje de bienvenida
        String textoBienvenida = mensaje.getText(); // Obtener el texto del mensaje

        if (textoBienvenida.contains("Bienvenido")) { // Verificar si contiene "Bienvenido"
            System.out.println("Login exitoso: " + textoBienvenida); // Imprimir mensaje de éxito
        } else {
            System.out.println("No se encontró el mensaje de bienvenida esperado."); // Mensaje de error
        }
    }

    @Then("presenta el mensaje de error {string}")
    public void presenta_el_mensaje_de_error(String mensajeXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement mensajeError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mensajeXPath))); // Esperar el mensaje de error
        String textoError = mensajeError.getText(); // Obtener el texto del mensaje

        if (textoError.contains("Error") || textoError.contains("incorrecto")) { // Verificar mensaje de error
            System.out.println("Login fallido: " + textoError); // Imprimir mensaje de fallo
        } else {
            System.out.println("No se encontró el mensaje de error esperado."); // Mensaje de error
        }
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
    public void el_boton_ingresar_debe_estar_deshabilitado(String botonXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement boton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(botonXPath))); // Esperar el botón

        // Verificar que el botón esté deshabilitado
        boolean isDisabled = !boton.isEnabled();

        if (isDisabled) {
            System.out.println("El botón está deshabilitado como se esperaba."); // Mensaje de éxito
        } else {
            System.out.println("El botón no está deshabilitado, lo que no es esperado."); // Mensaje de error
        }
    }

    // Métodos para la característica de Reserva

    @Given("al navegar hasta la url de reserva {string}")
    public void al_navegar_hasta_la_url_de_reserva(String url) {
        driver.get(url); // Navegar a la URL de reserva
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='book-views']"))); // Esperar a que sea visible
    }

    @When("hace click en el botón deporte {string}")
    public void hace_click_en_el_boton_deporte(String deporteXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement botonDeporte = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deporteXPath))); // Esperar botón de deporte
        botonDeporte.click(); // Hacer clic
    }

    @When("hace click en el botón dia {string}")
    public void hace_click_en_el_boton_dia(String diaXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Esperar hasta que desaparezca la superposición de carga
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loadingOverlay")));

        // Esperar a que el botón del día sea clicable
        WebElement botonDia = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(diaXPath)));
        botonDia.click(); // Hacer clic en el botón del día
    }

    @When("selecciona la hora {string} y presiona {string}")
    public void selecciona_hora_y_presiona_siguiente(String horaXPath, String botonContinuarXPath) {
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
    }

    @Then("presenta el mensaje {string}")
    public void presenta_el_mensaje(String mensaje) {
        // Este método puede implementarse para validar el mensaje esperado
        System.out.println("Mensaje de reserva: " + mensaje); // Imprimir mensaje de reserva
    }

    // Método para apretar botón "Siguiente"
    @When("apretar boton {string}")
    public void apretar_boton(String botonXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement boton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(botonXPath))); // Esperar a que el botón sea clicable
        boton.click(); // Hacer clic
    }

    @When("hace click en el botón tenis {string}")
    public void hace_click_en_el_boton_tenis(String tenisXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement botonTenis = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tenisXPath))); // Esperar botón de tenis
        botonTenis.click(); // Hacer clic en el botón de tenis
    }

    @When("selecciona club {string}")
    public void selecciona_club(String xpathClub) {
        try {
            // Esperar hasta que desaparezca el overlay de carga (si es visible)
            Thread.sleep(2000); // Pequeña pausa para permitir que desaparezca el overlay de carga

            // Encontrar el elemento del club
            WebElement clubElement = driver.findElement(By.xpath(xpathClub));

            // Realizar scroll hacia el elemento
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clubElement);

            // Verificar si el elemento está habilitado antes de hacer clic
            if (clubElement.isDisplayed() && clubElement.isEnabled()) {
                // Intentar hacer clic con Selenium
                try {
                    clubElement.click();
                    System.out.println("Elemento de club clicado exitosamente: " + xpathClub);
                } catch (ElementClickInterceptedException e) {
                    // Si el clic es interceptado, intentar hacer clic mediante JavaScript
                    System.out.println("Elemento interceptado, intentando con JavaScript.");
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clubElement);
                }
            } else {
                System.err.println("Elemento del club no está visible o habilitado.");
            }

        } catch (NoSuchElementException e) {
            System.err.println("Error: No se encontró el elemento del club con el xpath: " + xpathClub);
            throw e;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error: Interrupción durante la espera.");
            throw new RuntimeException("Error durante la espera: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al intentar seleccionar el club: " + e.getMessage());
            throw e;
        }
    }

    @When("selecciona disponibilidad {string}")
    public void seleccionaDisponibilidad(String xpathDisponibilidad) {
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
    }

    @When("elige nro cancha {string}")
    public void seleccionaLaCancha(String xpathCancha) {
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
    }

    @When("presiona el boton no quiero proteger a nadie {string}")
    public void presiona_el_boton_no_proteger_a_nadie(String xpathBoton) {
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
    }

    @When("presionar el botón reservar y pagar {string}")
    public void presionar_el_boton_reservar_y_pagar(String xpathBoton) {
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
    }

    @When("presiona el boton agregar tarjeta de credito debito {string}")
    public void presiona_el_boton_agregar_tarjeta(String xpathBoton) {
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
    }

    @Then("verifica y selecciona la opción de no agregar tarjeta {string}")
    public void verifica_selecciona_no_agregar_tarjeta(String xpathBoton) {
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
    }

    @When("presionar el botón reservar y pagar más tarde {string}")
    public void presionar_el_boton_reservar_y_pagar_mas_tarde(String xpathBoton) {
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
    }
    
    
    @Then("verificar que botón fecha anterior esté deshabilitado {string}")
    public void verificar_boton_fecha_anterior_esta_deshabilitado(String xpathBoton) throws TimeoutException {
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
    }

    
    
    
    @When("presionar el botón reservas del footer {string}")
    public void presionar_el_boton_reservas_footer(String xpathBoton) {
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
    }
    @And("presionar boton pasadas {string}")
    public void presionar_boton_pasadas(String pasadasButtonXPath) {
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
    }

@When("presionar el botón anular {string}")
    public void presionar_el_boton_anular(String anularButtonXPath) {
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
    }


    @When("presionar el botón sí en el modal de confirmación {string}")
    public void presionar_el_boton_si_en_modal(String xpathBoton) {
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
    }


    @When("presionar el botón ok en el mensaje de éxito {string}")
    public void presionar_el_boton_ok_en_mensaje_exito(String xpathBoton) {
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
    }
    
    @Then("verificar que no tienes reservas activas {string}")
    public void verificar_no_tienes_reservas_activas(String xpathMensaje) {
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
    }

    @When("presionar el icono de perfil del footer {string}")
    public void presionar_el_icono_de_perfil_footer(String xpathBoton) {
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
    }

    @Given("al navegar hasta la url1 {string}")
    public void al_navegar_hasta_la_url1(String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Navegar a la URL
        driver.get(url);

        // Esperar hasta que la página esté completamente cargada
        wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));

        WebElement bookViewsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='book-views']")));
		System.out.println("Elemento 'book-views' está visible en la página.");
    }

    @When("hace click en el botón padel {string}")
    public void hace_click_en_el_boton_padel(String xpathOpcion) {
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
    }
    @Then("verificar que el elemento contenga la palabra Padel {string}")
    public void verificar_texto_contiene_padel(String xpathElemento) throws TimeoutException {
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
    }
    @Then("verificar que aparezcan deportes para seleccionar {string}")
    public void verificar_deportes_para_seleccionar(String xpathDeportes) throws TimeoutException {
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
    }
    @When("presionar el botón volver dos veces {string}")
    public void presionar_el_boton_volver_dos_veces(String xpathBoton) throws TimeoutException {
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
    }
    @Then("verificar que el elemento contenga la palabra Tenis {string}")
    public void verificar_texto_contiene_tenis(String xpathElemento) throws TimeoutException {
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
    }
    
    
    @When("presiona el btnFooter {string}")
    public void presiona_el_btn_footer(String btnFooterXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement btnFooter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btnFooterXPath))); // Esperar a que el botón del footer sea clicable
            btnFooter.click(); // Hacer clic en el botón del footer
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el botón del footer con XPath: " + btnFooterXPath);
            throw e;
        }
    }
    
    @When("hace click en el botón Match {string}")
    public void hace_click_en_el_boton_Match(String matchXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
        WebElement botonMatch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(matchXPath))); // Esperar botón de Match
        botonMatch.click(); // Hacer clic en el botón "Match"
        System.out.println("Botón 'Match' seleccionado exitosamente: " + matchXPath);
    }


    @Then("verificar rivales disponibles {string}")
    public void verificar_rivales_disponibles(String xpathRivales) throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

        // Esperar hasta que los rivales estén presentes en el DOM
        List<WebElement> rivales = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathRivales)));

        // Verificar si existen rivales disponibles
        if (!rivales.isEmpty()) {
            System.out.println("Se encontraron " + rivales.size() + " rivales disponibles para desafiar.");
        } else {
            throw new AssertionError("No se encontraron rivales disponibles para desafiar.");
        }
    }
    
    @When("hace click en el botón ajustes {string}")
    public void hace_click_en_el_boton_ajustes(String ajustesXPath) {
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
    }

    @When("hace click en el botón editar deporte {string}")
    public void hace_click_en_el_boton_editar_deporte(String editarXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
        WebElement botonEditar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(editarXPath))); // Esperar hasta que el botón "Editar este deporte" sea clicable
        botonEditar.click(); // Hacer clic en el botón
    }

    @When("hace click en el botón seleccionar nivel {string}")
    public void hace_click_en_el_boton_seleccionar_nivel(String nivelXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
        WebElement botonNivel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(nivelXPath)));
        botonNivel.click(); // Hacer clic en el botón "Seleccionar Nivel"
    }

    @When("hace click en el botón seleccionar mano {string}")
    public void hace_click_en_el_boton_seleccionar_mano(String manoXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Esperar hasta 60 segundos
        WebElement botonMano = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(manoXPath)));
        botonMano.click(); // Hacer clic en el botón "Seleccionar Mano"
    }


    @When("hace click en el botón seleccionar día {string}")
    public void hace_click_en_el_boton_seleccionar_dia(String diaXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement botonDia = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(diaXPath)));
        botonDia.click(); // Hacer clic en el botón "Seleccionar Día"
    }
    @When("hace click en el botón seleccionar día JU")
    public void hace_click_en_el_boton_seleccionar_dia_ju() {
        String xpathDiaJU = "//button[contains(@class, 'btn_component_c') and contains(text(), 'JU')]";
        WebElement botonDiaJU = driver.findElement(By.xpath(xpathDiaJU));
        botonDiaJU.click(); // Hacer clic en el botón "JU"
    }
    
    @When("selecciona la hora de inicio {string}")
    public void seleccionar_hora_inicio(String horaInicioXPath) {
        WebElement dropdownInicio = driver.findElement(By.xpath(horaInicioXPath));
        dropdownInicio.click(); // Seleccionar hora de inicio
    }

    @When("selecciona la hora de término {string}")
    public void seleccionar_hora_termino(String horaTerminoXPath) {
        WebElement dropdownTermino = driver.findElement(By.xpath(horaTerminoXPath));
        dropdownTermino.click(); // Seleccionar hora de término
    }
    @When("selecciona la comuna {string}")
    public void selecciona_la_comuna(String comunaXPath) {
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
    }


    
    @When("ingresa la comuna {string} en el campo de comuna {string}")
    public void ingresa_comuna(String comuna, String comunaXPath) {
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
    }
    @When("selecciona la comuna de la lista {string}")
    public void selecciona_la_comuna_de_la_lista(String comunaXPath) {
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
    }
    @When("hace click en el botón siguiente {string}")
    public void hace_click_en_el_boton_siguiente(String siguienteXPath) {
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
    }
    @When("hace click en el boton hombre {string}")
    public void hace_click_en_el_boton_hombre(String hombreXPath) {
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
    }

    @When("hace click en el botón rango {string}")
    public void hace_click_en_el_boton_rango(String rangoXPath) {
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
    }

    @When("hace click en el botón estilo de juego {string}")
    public void hace_click_en_el_boton_estilo_de_juego(String estiloJuegoXPath) {
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
    }
    @When("hace click en el botón mano {string}")
    public void hace_click_en_el_boton_mano(String manoXPath) {
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
    }
    @Then("hace click en el botón terminar perfil {string}")
    public void hace_click_en_el_boton_terminar_perfil(String terminarPerfilXPath) {
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
    }
    @Then("verifica mensaje {string}")
    public void verifica_mensaje(String mensajeXPath) {
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
    }
    @And("presiona botón ok {string}")
    public void presiona_boton_ok(String okButtonXPath) {
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
    }
    @And("presiona botón desafiar {string}")
    public void presiona_boton_desafiar(String desafiarButtonXPath) {
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
    }
    @And("elige hora de desafio {string}")
    public void elige_hora_de_desafio(String horaDesafioXPath) {
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
    }
    @And("presiona confirmacion {string}")
    public void presiona_confirmacion(String confirmacionButtonXPath) {
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
    }
    @Then("mensaje confirma desafio {string}")
    public void mensaje_confirma_desafio(String mensajeXPath) throws TimeoutException {
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
    }

    @And("presiona botón desafiar a {string}")
    public void presiona_boton_desafiar_a(String desafiarAXPath) {
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
    }
    @And("presiona anular {string}")
    public void presiona_anular(String anularButtonXPath) {
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
    }
    @And("confirma anulacion {string}")
    public void confirma_anulacion(String confirmacionAnulacionXPath) {
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
    }
    @Then("ver mis reservas {string}")
    public void ver_mis_reservas(String reservasXPath) {
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
    }
    @Then("presiona en reservas pasadas {string}")
    public void presiona_en_reservas_pasadas(String reservasPasadasXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement botonReservasPasadas = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reservasPasadasXPath))); // Esperar a que el botón de reservas pasadas sea clicable
            botonReservasPasadas.click(); // Hacer clic en el botón de reservas pasadas
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el botón de reservas pasadas con XPath: " + reservasPasadasXPath);
            throw e;
        }
    }
    @And("selecciona mes reserva {string}")
    public void selecciona_mes_reserva(String mesXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement mesSelectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mesXPath)));
            Select mesSelect = new Select(mesSelectElement);
            mesSelect.selectByVisibleText("Agosto"); // Seleccionar el mes de Agosto
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo seleccionar el mes con XPath: " + mesXPath);
            throw e;
        }
    }
    @And("selecciona año reserva {string}")
    public void selecciona_año_reserva(String añoXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement añoSelectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(añoXPath)));
            Select añoSelect = new Select(añoSelectElement);
            añoSelect.selectByVisibleText("2024"); // Seleccionar el año 2024
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo seleccionar el año con XPath: " + añoXPath);
            throw e;
        }
    }
    @And("presionar botón clubes {string}")
    public void presionar_boton_clubes(String botonClubesXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement botonClubes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(botonClubesXPath))); // Esperar a que el botón de clubes sea clicable
            botonClubes.click(); // Hacer clic en el botón de clubes
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el botón de clubes con XPath: " + botonClubesXPath);
            throw e;
        }
    }
    @And("presionar filtro club {string}")
    public void presionar_filtro_club(String filtroClubXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement botonFiltroClub = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(filtroClubXPath))); // Esperar a que el botón de filtro de club sea clicable
            botonFiltroClub.click(); // Hacer clic en el botón de filtro de club
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("TimeoutException: No se pudo hacer clic en el botón de filtro de club con XPath: " + filtroClubXPath);
            throw e;
        }
    }

    @When("elegir comunas {string}")
    public void elegir_comunas(String comunaXPath) {
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
    }

    @Then("elegir actividad {string}")
    public void elegir_actividad(String actividadXPath) {
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
    }
    @And("desplegar {string}")
    public void desplegar(String opcionXPath) {
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
    }
    @Then("aplicar filtros {string}")
    public void aplicar_filtros(String botonFiltrosXPath) {
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
    }
    @Then("editar datos personales {string}")
    public void editar_datos_personales(String editarDatosXPath) {
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
    }
    @And("descartar prime {string}")
    public void descartar_prime(String descartarPrimeXPath) {
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
    }
    @Then("ver lista amigos {string}")
    public void ver_lista_amigos(String verListaAmigosXPath) {
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
    }
    @Then("btn tarjetas {string}")
    public void btn_tarjetas(String btnTarjetasXPath) {
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
    }
}
