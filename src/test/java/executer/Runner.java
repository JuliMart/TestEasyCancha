package executer;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", // Ruta de la carpeta donde están todos los .feature
    glue = "stepDefinitions",                 // Ruta del paquete con las definiciones de pasos
    plugin = {"json:target/cucumber.json",
    		"pretty",
    		"html:target/cucumber-reports/report.html", 
    		"junit:target/cucumber-results.xml"}, // Opcional: para generar reportes
    monochrome = true                          // Para una salida más legible en la consola
)
public class Runner {
}