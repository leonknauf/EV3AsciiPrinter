package BerechnungPosition;
//Diese Klasse speichert alle Daten einer Linie
public class Linie {
    private int startX, startY, endX, endY; //Variablen für Start- und Endpunkt

    //Konstruktor mit Variablenübergabe
    public Linie(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    //Getter-Methoden
    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
}
