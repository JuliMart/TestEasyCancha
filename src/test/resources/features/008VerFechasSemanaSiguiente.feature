@tag
Feature: Reserva
  Background:
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
    And hacer click en el botón "//div[contains(@class, 'notification_need_login')]/div[contains(@class, 'row pointer')]"
    When coloca en el campo usuario "//input[@name='email' and @type='email']" el texto "juli.martinezm@duocuc.cl"
    And coloca en el campo password "//input[@name='password' and @type='password']" el texto "Gael2901"
    And hace click en el boton "//button[contains(text(), 'Ingresar')]"
    Then presenta el mensaje de bienvenida "//div[contains(@class, 'user_welcome')]"


@tag3 
	  Scenario: Fecha semana siguiente
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"    
		When hace click en el botón deporte "//div[contains(@class, 'col-xs-6 cardGutter pointer noselect') and @ng-click='chooseSports()']//h4[text()='Deportes']"
		And hace click en el botón tenis "//div[contains(@class, 'col-xs-6 col-md-4 sports_items ng-scope')]//a[contains(@ng-click, 'selectSport') and .//div[contains(@class, 'sport_item_img')]]"
		Then ver semana siguiente "//button[contains(@class, 'btn-slide') and contains(@ng-click, 'next()')]"
		
		