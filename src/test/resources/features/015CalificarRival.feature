@tag
Feature: Busqueda de Rival
	Background: 
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
    And hacer click en el botón "//div[contains(@class, 'notification_need_login')]/div[contains(@class, 'row pointer')]"
    When coloca en el campo usuario "//input[@name='email' and @type='email']" el texto "juli.martinezm@duocuc.cl"
    And coloca en el campo password "//input[@name='password' and @type='password']" el texto "Gael2901"
    And hace click en el boton "//button[contains(text(), 'Ingresar')]"
    Then presenta el mensaje de bienvenida "//div[contains(@class, 'user_welcome')]"
   
Scenario: Calificar rival
  Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
  And hace click en el botón Match "//a[contains(@class, 'menu-icon') and @ng-click='clickGotoMatch()']//span[text()='Match']"
  Then presiona calificar '//*[@id="match"]/section[4]/div[2]/div[2]/button'