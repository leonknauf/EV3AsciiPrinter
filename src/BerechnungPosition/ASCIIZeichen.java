package BerechnungPosition;

import java.util.List;
//Diese Klasse dien dazu ein Objekt zu erstellen, welches Informationen für ein AsciiZeichen enthält
public class ASCIIZeichen {
    private List<Linie> linien;     //Liste mit Linien, welche zum Zeichnen benötigt werden
    private char zeichen;           //Das Zeichen, welches gemalt wird

    //Konstruktor mit Variablenübergabe
    public ASCIIZeichen(List<Linie> linien, char zeichen) {
        this.linien = linien;
        this.zeichen = zeichen;

    }

    //Überprüft ob das übergebene Zeichen dem Gespeicherten entspricht
    public boolean compareZeichen(char zeichen) {
        return this.zeichen == zeichen;
    }

    //Gibt den maximalen X-Wert der Linien zurück
    public int getMaxX() {
        int maxX = 0;
        for (Linie l : linien) {
            maxX = Math.max(Math.max(l.getStartX(),l.getEndX()), maxX);
        }
        return maxX;
    }
    //Gibt den maximalen Y-Wert der Linien zurück
    public int getMaxY() {
        int maxY = 0;
        for (Linie l : linien) {
            maxY = Math.max(Math.max(l.getStartY(),l.getEndY()), maxY);
        }
        return maxY;
    }

    //Gibt eine Liste mit allen Linien zurück
    public List<Linie> getLinien() {
        return linien;
    }
    

}
