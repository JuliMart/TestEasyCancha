@tag
Feature: Perfil Usuario
	Background: 
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
    And hacer click en el botón "//div[contains(@class, 'notification_need_login')]/div[contains(@class, 'row pointer')]"
    When coloca en el campo usuario "//input[@name='email' and @type='email']" el texto "juli.martinezm@duocuc.cl"
    And coloca en el campo password "//input[@name='password' and @type='password']" el texto "Gael2901"
    And hace click en el boton "//button[contains(text(), 'Ingresar')]"
    Then presenta el mensaje de bienvenida "//div[contains(@class, 'user_welcome')]"
	
	Scenario: Ver lista amigos 
		Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
	#	And descartar prime "/html/body/div[1]/div/div/div/div[2]/a"
		And presionar el icono de perfil del footer "//*[@id='main-footer']/div/a[4]"
		Then ver lista amigos "//*[@id='profile-views']/main/section[3]/ul[1]/li[2]/a/span"