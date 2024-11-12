@tag
Feature: Login
@tag1
  
  Scenario: LoginPasswordIncorrecto
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
    And hacer click en el botón "//div[contains(@class, 'notification_need_login')]/div[contains(@class, 'row pointer')]"
    When coloca en el campo usuario "//input[@name='email' and @type='email']" el texto "juli.martinezm@duocuc.cl"
    And coloca en el campo password "//input[@name='password' and @type='password']" el texto "asdasd"
    And hace click en el boton "//button[contains(text(), 'Ingresar')]" 
    Then presenta el mensaje de error "//div[contains(@class, 'modal-content')]"
    