  
@tag
Feature: Login
  
  @tag4
  Scenario: LoginBtnIngresarDis
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
    And hacer click en el botón "//div[contains(@class, 'notification_need_login')]/div[contains(@class, 'row pointer')]"
    When coloca en el campo usuario "//input[@name='email' and @type='email']" el texto ""
    And coloca en el campo password "//input[@name='password' and @type='password']" el texto ""
    Then el boton ingresar debe estar deshabilitado "//button[contains(text(), 'Ingresar')]" 
 