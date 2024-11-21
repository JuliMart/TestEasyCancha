 
@tag
Feature: Seleccion Deporte
  Background:
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
    And hacer click en el botón "//div[contains(@class, 'notification_need_login')]/div[contains(@class, 'row pointer')]"
    When coloca en el campo usuario "//input[@name='email' and @type='email']" el texto "juli.martinezm@duocuc.cl"
    And coloca en el campo password "//input[@name='password' and @type='password']" el texto "Gael2901"
    And hace click en el boton "//button[contains(text(), 'Ingresar')]"
    Then presenta el mensaje de bienvenida "//div[contains(@class, 'user_welcome')]"
 
 @tag2 
  Scenario: Filtrar Deporte Padel
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
    When hace click en el botón deporte '//*[@id="book-views"]/main/section[2]/div[1]/div[1]/div'
	  And hace click en el botón padel "//div[@class='sport_item_name ng-binding' and contains(text(), 'Padel')]"
    And hace click en el botón dia "//div[contains(@class, 'cds-day ng-scope') and .//span[text()='24'] and .//span[text()='dom.']]"
		Then selecciona la hora "//div[contains(@class, 'hour_item') and normalize-space()='18:30']" y presiona "//a[contains(@class, 'btn-success') and contains(@class, 'btn-standar') and contains(text(), 'Siguiente')]"
     