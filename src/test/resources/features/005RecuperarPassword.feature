
@tag
Feature: Login

 @tag4	
 Scenario: Recordar usuario
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
    And hacer click en el botón "//div[contains(@class, 'notification_need_login')]/div[contains(@class, 'row pointer')]"
		And hacer click en olvide contraseña "//*[@id='scrollContainer']/main/section/div/form/div/div[5]/div[2]/a"
		And clickear por email "//*[@id='scrollContainer']/div[3]/section/div[2]/div[2]/button"
		When coloca en el campo usuario "//input[@name='email' and @type='email']" el texto "juli.martinezm@duocuc.cl"
		And presiona recuperar clave "//*[@id='scrollContainer']/div[1]/section/div/form/div/div[2]/button"
 