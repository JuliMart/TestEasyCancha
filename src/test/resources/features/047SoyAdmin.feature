@tag
Feature: Ayuda y Faq
@tag1
	Scenario: unir mi centro deportivo
	    		Given al navegar hasta la url base "https://www.easycancha.com/es-CL"
    			And presiona soy administrador '//*[@id="page-top"]/header/div/a'
   				Then quiero unirme '//*[@id="botones_hero_app"]/a/span/p'