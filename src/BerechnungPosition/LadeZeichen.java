package BerechnungPosition;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//Diese Klasse dient dazu, Alle verfügbaren Ascii Zeichen aus einer Datei einzulesen und als Liste an ASCIIZeichen-Objekten zurückzugeben
public class LadeZeichen {
	
	//lädt eine Datei mit Ascii-Zechen-Informationen und gibt es als Liste zurück
	public static List<ASCIIZeichen> importAsciiZeichen(String dateiPfad) {
        List<ASCIIZeichen> zeichenObjekte = new ArrayList<ASCIIZeichen>();

        //Datei laden und in Zeilen aufteilen
        String text = ladeDatei(dateiPfad);
        String[] einzelneZeichen = text.split("\r\n");

        //Daten aus jeder Zeile extrahieren
        for (String einZeichen : einzelneZeichen) {
            if (einZeichen.equals("")) return zeichenObjekte;

            //Zeichen steht vor dem :, Liniendaten dahinter
            String zeichen = einZeichen.split(":")[0];
            String[] data = einZeichen.split(":")[1].split(";");

            List<Linie> tempLinien = new ArrayList<Linie>();

            //Liniendaten splitten
            for (String dataObject : data) {
                String[] linienDaten = dataObject.split(",");

                //Linie hinzufügen
                tempLinien.add(new Linie(Integer.parseInt(linienDaten[0]),
                        Integer.parseInt(linienDaten[1]),
                        Integer.parseInt(linienDaten[2]),
                        Integer.parseInt(linienDaten[3])));
            }

            //Zeichen hinzufügen
            zeichenObjekte.add(new ASCIIZeichen(tempLinien,zeichen.toCharArray()[0]));
        }

        //Zeichen zurückgeben
        return zeichenObjekte;
    }


    //Datei laden und als String zurückgeben
    private static String ladeDatei(String datName) {

        File file = new File(datName);

        //Prüfen ob Datei lesbar und gültig
        if (!file.canRead() || !file.isFile())
            System.exit(0);

        //Variablen erstellen
        FileReader fr = null;
        int c;
        StringBuffer buff = new StringBuffer();

        //Lesen solange Daten vorhanden und in Puffer schreiben
        try {
            fr = new FileReader(file);
            while ((c = fr.read()) != -1) {
                buff.append((char) c);
            }
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Puffer als String zurückgeben
        return buff.toString();
    }
}
