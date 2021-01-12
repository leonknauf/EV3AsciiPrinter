package BerechnungPosition;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class Menu {
	
	Integer schriftgroesse = 5;   //0
	Integer zeilenabsstand = 5;	//1
	Integer abstandZwischenBuchstaben =5;//2
	
	int aktiverWert=0;
	
	public Menu() {
		auswahl();
		
	}
	
	
	
	protected Integer getSchriftgroesse() {
		return schriftgroesse;
	}



	protected Integer getZeilenabsstand() {
		return zeilenabsstand;
	}



	protected Integer getAbstandZwischenBuchstaben() {
		return abstandZwischenBuchstaben;
	}



	private void auswahl() {
		int id;
		
		
		do {
			drawMenu();
			id = Button.waitForAnyEvent();
			
			switch(aktiverWert) {
			case 0:
				if (id == Button.ID_RIGHT) {
					schriftgroesse++;
				}
				if (id == Button.ID_LEFT) {
					if(schriftgroesse>1)schriftgroesse--;
				}
				break;
			case 1:
				if (id == Button.ID_RIGHT) {
					zeilenabsstand++;
				}
				if (id == Button.ID_LEFT) {
					if(zeilenabsstand>1)zeilenabsstand--;
				}
				break;
			case 2:
				if (id == Button.ID_RIGHT) {
					abstandZwischenBuchstaben++;
				}
				if (id == Button.ID_LEFT) {
					if(abstandZwischenBuchstaben>1)abstandZwischenBuchstaben--;
				}
				break;
			}
			if(id==Button.ID_UP && aktiverWert>0) aktiverWert--;
			if(id==Button.ID_DOWN && aktiverWert<3) aktiverWert++;
			
		}while(id != Button.ID_ENTER);
		
	}
	
	private void drawMenu() {
		// Groesse
		LCD.clear();
		AuswahlMenue(schriftgroesse, 0, "Groesse");
		AuswahlMenue(zeilenabsstand, 2, "Zeilenabstand");
		AuswahlMenue(abstandZwischenBuchstaben, 4, "Abstand Buchst.");
		zeigeAktiv();
		

		
		
	}
	private void AuswahlMenue(Integer werte, int startPosition, String name ) {
		LCD.drawString(name + ":", 0, startPosition);
		LCD.drawChar('<', 2, startPosition+1);
		if(werte>10) {
			LCD.drawInt(werte - 1, 3, startPosition+1);
			LCD.drawInt(werte, 6, startPosition+1);
			//LCD.drawChar((char) 94, 5, startPosition+3);
			LCD.drawInt(werte + 1, 9, startPosition+1);
			LCD.drawChar('>', 11, startPosition+1);
		}else if(werte==9) {
			LCD.drawInt(werte - 1, 3, startPosition+1);
		LCD.drawInt(werte, 5, startPosition+1);
		//LCD.drawChar((char) 94, 4, startPosition+3);
		LCD.drawInt(werte + 1, 7, startPosition+1);
		LCD.drawChar('>', 9, startPosition+1);
		}else if(werte==10) {
			LCD.drawInt(werte - 1, 3, startPosition+1);
			LCD.drawInt(werte, 5, startPosition+1);
			//LCD.drawChar((char) 94, 5, startPosition+3);
			LCD.drawInt(werte + 1, 8, startPosition+1);
			LCD.drawChar('>', 10, startPosition+1);
		
		}else {
			LCD.drawInt(werte - 1, 3, startPosition+1);
		LCD.drawInt(werte, 5, startPosition+1);
		//LCD.drawChar((char) 94, 4, startPosition+3);
		LCD.drawInt(werte + 1, 7, startPosition+1);
		LCD.drawChar('>', 8, startPosition+1);
		}
	}
	private void zeigeAktiv() {
		switch(aktiverWert) {
		case 0:
			LCD.drawChar('>', 0, 1);
			break;
		case 1:
			LCD.drawChar('>', 0, 3);
			break;
		case 2:
			LCD.drawChar('>', 0, 5);
			break;
			
		}
	}

}
