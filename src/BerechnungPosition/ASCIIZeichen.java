package BerechnungPosition;

import java.util.List;

public class ASCIIZeichen {
    private List<Linie> linien;
    private char zeichen;

    public ASCIIZeichen(List<Linie> linien, char zeichen) {
        this.linien = linien;
        this.zeichen = zeichen;

    }

    public boolean compareZeichen(char zeichen) {
        return this.zeichen == zeichen;
    }

    public int getMaxX() {
        int maxX = 0;
        for (Linie l : linien) {
            maxX = Math.max(Math.max(l.getStartX(),l.getEndX()), maxX);
        }
        return maxX;
    }

    public int getMaxY() {
        int maxY = 0;
        for (Linie l : linien) {
            maxY = Math.max(Math.max(l.getStartY(),l.getEndY()), maxY);
        }
        return maxY;
    }

    public List<Linie> getLinien() {
        return linien;
    }
}
