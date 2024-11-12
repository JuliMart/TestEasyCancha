@tag
Feature: Reservas
  Background:
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
    And hacer click en el botón "//div[contains(@class, 'notification_need_login')]/div[contains(@class, 'row pointer')]"
    When coloca en el campo usuario "//input[@name='email' and @type='email']" el texto "juli.martinezm@duocuc.cl"
    And coloca en el campo password "//input[@name='password' and @type='password']" el texto "Gael2901"
    And hace click en el boton "//button[contains(text(), 'Ingresar')]"
    Then presenta el mensaje de bienvenida "//div[contains(@class, 'user_welcome')]"
    
    
     @tag1
 Scenario: ReservaCancha
    Given al navegar hasta la url "https://www.easycancha.com/book/search?lang=es-CL&country=CL"
		When hace click en el botón deporte "//*[@id='book-views']/main/section[2]/div[1]/div[1]/div/h4"
		And hace click en el botón tenis "//div[contains(@class, 'col-xs-6 col-md-4 sports_items ng-scope')]//a[contains(@ng-click, 'selectSport') and .//div[contains(@class, 'sport_item_img')]]"
		And hace click en el botón dia "//div[contains(@class, 'cds-day ng-scope') and .//span[text()='16'] and .//span[text()='sáb.']]"
		And selecciona la hora "//div[contains(@class, 'hour_item') and normalize-space()='07:00']" y presiona "//a[contains(@class, 'btn-success') and contains(@class, 'btn-standar') and contains(text(), 'Siguiente')]"
		And selecciona club "//div[contains(@class, 'card clubCard pointer') and contains(@ng-click, 'csLoadClubDispo') and .//div[contains(text(), 'Youtopia Raquet')]]"
		And selecciona disponibilidad "//div[@id='book-views']//div[contains(@class, 'hour-data pointer active') and .//span[text()='07:00']]"
		And elige nro cancha '//*[@id="book-views"]//div[@class="hour-sub pointer flex-direction-column" and .//span[contains(text(), "Cancha 2")]]'
		And presiona el boton no quiero proteger a nadie "//div[@id='booking-insurance-modal']//button[contains(@class, 'insuranceButton') and .//h4[contains(text(), 'No quiero proteger a nadie')]]"
    And presionar el botón reservar y pagar "//button[contains(@class, 'reserva_btn_primary') and .//span[contains(text(), 'Reservar y pagar')]]"
	  And presiona el boton agregar tarjeta de credito debito "//button[contains(@class, 'addCreditCardButton') and contains(text(), 'Agregar tarjeta de crédito / débito')]"
    Then verifica y selecciona la opción de no agregar tarjeta "//button[contains(@class, 'btn_verde') and contains(text(), 'No agregar tarjeta y volver')]"
	  