@tag
Feature: Filtrar por ubicacion
	Background: 
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
    And hacer click en el botón "//div[contains(@class, 'notification_need_login')]/div[contains(@class, 'row pointer')]"
    When coloca en el campo usuario "//input[@name='email' and @type='email']" el texto "juli.martinezm@duocuc.cl"
    And coloca en el campo password "//input[@name='password' and @type='password']" el texto "Gael2901"
    And hace click en el boton "//button[contains(text(), 'Ingresar')]"
    Then presenta el mensaje de bienvenida "//div[contains(@class, 'user_welcome')]"
    
@tag1
	Scenario: Filtrar cancha por ubicacion
		   Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
			 And presionar botón clubes "//*[@id='book-views']/main/section[2]/div[1]/div[2]/div"
			 And presionar filtro club "//*[@id='book-views']/main/section[2]/div[1]/div/span/button"
			 And desplegar "/html/body/div[1]/div/div/div[2]/div[2]/div[2]/select/option[6]"
			 And elegir comunas "/html/body/div[1]/div/div/div[2]/div[3]/div[2]/div[6]/label"
			 And elegir actividad "/html/body/div[1]/div/div/div[2]/div[4]/div[2]/div[5]/label"
			 Then aplicar filtros "/html/body/div[1]/div/div/div[3]/button[1]"