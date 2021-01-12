package BerechnungPosition;

import java.util.ArrayList;
import java.util.List;

import plott3r_V1_solved.Roboter;

public class Main {

	public static void main(String[] args) {
		
		
		
		//Drucktext aus Zeichen Laden. Wenn zeichen nicht druckbar wird es weggelassen.
		Text text= new Text("text.txt");
		List<ASCIIZeichen> zeichen = ladeDrucktext(text.getText());
		Roboter roboter;
		
		
		
		try {
			roboter = new Roboter();
			Blatt b =new Blatt(roboter);
			b.druckeZeichen(zeichen);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

	private static List<ASCIIZeichen> ladeDrucktext(String text) {
		List<ASCIIZeichen> drucktext = new ArrayList();
		List<ASCIIZeichen> buchstaben = LadeZeichen.importAsciiZeichen("zeichen.txt");

		for (int i = 0; i < text.length(); i++) { // Itereation durch den zu druckenden String
			char aktuellerBuchstabe = text.charAt(i); // buchtsabe aus String laden
			for (ASCIIZeichen j : buchstaben) { // Iteration durch Liste aller Druckbaren Zeichen
				if (j.compareZeichen(aktuellerBuchstabe)) { // Wenn druckbares zeichen gefunden ist
					drucktext.add(j); // wird es zum Drucktext hinzugefügt
				}
			}

		}

		return drucktext;

	}


}
