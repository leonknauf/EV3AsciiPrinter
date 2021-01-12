package BerechnungPosition;



import java.io.File;
import java.io.IOException;
import java.util.Scanner;



//Laden des Texts aus Datei
public class Text {

	private String druckText = "";
	private String file;
	
	
	public Text(String Dateipfad) {
		this.file = Dateipfad;
		
		einlesen();
		
	}
	
	protected String getText() {
		return this.druckText;
	}
	
	protected String einlesen() {

		
		try {

			Scanner scanner = new Scanner(new File(file));
			druckText = scanner.next();
		    
		} catch (IOException e) {
			throw new RuntimeException("Konnte Datei nicht Einlesen!");
		}
	     return druckText;
		
	}
	
	
	
}
