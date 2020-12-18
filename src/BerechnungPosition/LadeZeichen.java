package BerechnungPosition;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LadeZeichen {
	
	
	public static List<ASCIIZeichen> importAsciiZeichen(String dateiPfad) {
        List<ASCIIZeichen> zeichenObjekte = new ArrayList<ASCIIZeichen>();

        String text = ladeDatei(dateiPfad);
        String[] einzelneZeichen = text.split("\r\n");
        for (String einZeichen : einzelneZeichen) {
            if (einZeichen.equals("")) return zeichenObjekte;

            String zeichen = einZeichen.split(":")[0];
            String[] data = einZeichen.split(":")[1].split(";");

            List<Linie> tempLinien = new ArrayList<Linie>();

            for (String dataObject : data) {
                String[] linienDaten = dataObject.split(",");

                tempLinien.add(new Linie(Integer.parseInt(linienDaten[0]),
                        Integer.parseInt(linienDaten[1]),
                        Integer.parseInt(linienDaten[2]),
                        Integer.parseInt(linienDaten[3])));
            }

            zeichenObjekte.add(new ASCIIZeichen(tempLinien,zeichen.toCharArray()[0]));
        }

        return zeichenObjekte;
    }

    private static String ladeDatei(String datName) {

        File file = new File(datName);

        if (!file.canRead() || !file.isFile())
            System.exit(0);

        FileReader fr = null;
        int c;
        StringBuffer buff = new StringBuffer();
        try {
            fr = new FileReader(file);
            while ((c = fr.read()) != -1) {
                buff.append((char) c);
            }
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return buff.toString();
    }
}
