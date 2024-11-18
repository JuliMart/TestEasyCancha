@tag
Feature: Busqueda de Rival
	Background: 
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
    And hacer click en el botón "//div[contains(@class, 'notification_need_login')]/div[contains(@class, 'row pointer')]"
    When coloca en el campo usuario "//input[@name='email' and @type='email']" el texto "juli.martinezm@duocuc.cl"
    And coloca en el campo password "//input[@name='password' and @type='password']" el texto "Gael2901"
    And hace click en el boton "//button[contains(text(), 'Ingresar')]"
    Then presenta el mensaje de bienvenida "//div[contains(@class, 'user_welcome')]"
	
  @
@tag2
    Scenario: Editar perfil deporte 
    	    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
					And hace click en el botón Match "//a[contains(@class, 'menu-icon') and @ng-click='clickGotoMatch()']//span[text()='Match']"
					When hace click en el botón ajustes "//a[contains(@class, 'btn_dtl_profile')]//i[contains(@class, 'fa-gear')]"
					And hace click en el botón editar deporte '//*[@id="match"]/section[3]/div/div[5]/a'
					And hace click en el botón seleccionar nivel "//div[@id='match-views']/div/main/section[2]/div[2]/div[2]/span[3]/button"
    			And hace click en el botón seleccionar mano "//div[@id='match-views']/div/main/section[2]/div[3]/div[2]/span[2]/button"
    			And hace click en el botón seleccionar día "//div[@id='match-views']/div/main/section[3]/div[2]/div[2]/span[2]/button"
    			And selecciona la hora de inicio "//*[@id='start_time']/option[@value='string:08:00:00']"
					And selecciona la hora de término "//*[@id='end_time']/option[@value='string:09:00:00']"
					And hace click en el botón siguiente "//button[@class='btn_terminar_perfil ng-binding' and normalize-space(text())='Siguiente']"
					And hace click en el boton hombre '//*[@id="match-views"]/div/main/section[2]/div/div[2]/button[1]'
    			And hace click en el botón rango '//*[@id="match-views"]/div/main/section[3]/div/div[2]/button[4]' 
    			And hace click en el botón estilo de juego '//*[@id="match-views"]/div/main/section[4]/div[2]/div[2]/span[3]/button'
    			And hace click en el botón mano '//*[@id="match-views"]/div/main/section[4]/div[3]/div[2]/span[1]/button'
    			And hace click en el botón siguiente '//*[@id="match-views"]/div/main/section[5]/div[1]/div/section/button'
    			Then verifica mensaje "/html/body/div[1]/div/div/div[2]"
    			And presiona botón ok "/html/body/div[1]/div/div/div[3]/button"
    			
    			