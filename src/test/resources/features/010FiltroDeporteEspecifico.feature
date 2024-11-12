 
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
    When hace click en el botón deporte "//div[contains(@class, 'col-xs-6 cardGutter pointer noselect') and @ng-click='chooseSports()']//h4[text()='Deportes']"
	  And hace click en el botón padel "//div[@class='sport_item_name ng-binding' and contains(text(), 'Padel')]"
    And hace click en el botón dia "//div[contains(@class, 'cds-day ng-scope') and .//span[text()='15'] and .//span[text()='vie.']]"
		And selecciona la hora "//div[contains(@class, 'hour_item') and normalize-space()='09:00']" y presiona "//a[contains(@class, 'btn-success') and contains(@class, 'btn-standar') and contains(text(), 'Siguiente')]"
    Then verificar que el elemento contenga la palabra Padel "//div[contains(@id, 'book-views')]//section[2]//div[contains(@class, 'row') and position()=6]//div[1]//div[2]//div[1]"
     