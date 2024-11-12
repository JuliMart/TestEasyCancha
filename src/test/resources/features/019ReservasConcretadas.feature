@tag
Feature: Reservas
	Background: 
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
    And hacer click en el botón "//div[contains(@class, 'notification_need_login')]/div[contains(@class, 'row pointer')]"
    When coloca en el campo usuario "//input[@name='email' and @type='email']" el texto "juli.martinezm@duocuc.cl"
    And coloca en el campo password "//input[@name='password' and @type='password']" el texto "Gael2901"
    And hace click en el boton "//button[contains(text(), 'Ingresar')]"
    Then presenta el mensaje de bienvenida "//div[contains(@class, 'user_welcome')]"
	
@tag2
    Scenario: Ver reserva 
    	    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"	
  				When presiona el btnFooter "//*[@id='main-footer']/div/a[2]/span"
  				And presiona en reservas pasadas "//*[@id='scrollContainer']/main/section[3]/div[1]/div/div[3]/button[1]"
  				And selecciona mes reserva "//*[@id='scrollContainer']/main/section[4]/div/div[1]/div[1]/select"
  				And selecciona año reserva "//*[@id='scrollContainer']/main/section[4]/div/div[1]/div[2]/select"