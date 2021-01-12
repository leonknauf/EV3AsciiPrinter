package BerechnungPosition;

import java.util.List;

import plott3r_V1_solved.Roboter;
import positions.Position2D;
import positions.Position3D;

public class Blatt {
	private Position2D platzZwischenBuchstaben = new Position2D(5, 0);
	private int geschwindigkeit = 10;
	private int breite = 140;
	private int zeilenabstand = 5;
	private int schriftgroesse = 5;

	// private Position2D offsetBuchstaben = new Position2D(0, 0);
	private Position2D offset = new Position2D(0, 0); // offset für den aktuellen Buchtsaben
	private Roboter roboter;

	public Blatt(Roboter roboter) {
		this.roboter = roboter;
		Menu m = new Menu();
		zeilenabstand=m.getZeilenabsstand();
		schriftgroesse=m.getSchriftgroesse();
		platzZwischenBuchstaben=new Position2D(m.getAbstandZwischenBuchstaben(), 0);
	}

	public void druckeZeichen(List<ASCIIZeichen> zeichen) {
		//Maximale Y Hoehe bestimmen
		int MaxY=0;
		for(ASCIIZeichen z: zeichen) {
			if(z.getMaxY()>MaxY) MaxY= z.getMaxY();
		}

		for (int i = 0; i < zeichen.size(); i++) {
			druckeEinZeichen(zeichen.get(i), offset);
			if (i == zeichen.size() - 1) { // Ueberpruefung ob es der letzte Buchstabe ist
				// Stift nach oben fahren bei letzten Buchstaben

				roboter.getZAchse().deaktiviere();

				break;// Schleife beenden, da kein offset fuer weitere Buchstaben berechnet werden muss
			}
			offsetBerechnen(zeichen.get(i), zeichen.get(i + 1),MaxY);

			try {
				// Position fuer naechsten Buchstaben anfahren
				roboter.moveToPosition(new Position3D(offset, false), geschwindigkeit);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

	private void druckeEinZeichen(ASCIIZeichen zeichen, Position2D startposition) {

		for (int i = 0; i < zeichen.getLinien().size(); i++) {
			try {

				// Ueberpruefen ob naechste Linie an aktueller Stelle beginnt

				if (i == 0) {
					// Fahren zur Startposition des Buchstaben
					roboter.moveToPosition(getStartPos(zeichen.getLinien().get(i), startposition), geschwindigkeit);
				}
				// Prüfen ob letzter Endpunkt mit Startpunkt uebereinstimmt
				if (i > 0 && !((int) getEndPos(zeichen.getLinien().get(i - 1), startposition)
						.getX() == (int) getStartPos(zeichen.getLinien().get(i), startposition).getX()
						&& (int) getEndPos(zeichen.getLinien().get(i - 1), startposition)
								.getY() == (int) getStartPos(zeichen.getLinien().get(i), startposition).getY())) {
					roboter.moveToPosition(getStartPos(zeichen.getLinien().get(i), startposition), geschwindigkeit);
				}

				roboter.moveToPosition(getEndPos(zeichen.getLinien().get(i), startposition), geschwindigkeit);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	private Position3D getStartPos(Linie linie, Position2D offset) {

		Position3D pos = new Position3D(linie.getStartX() * schriftgroesse, linie.getStartY() * schriftgroesse, false);
		pos.add(offset);
		return pos;

	}

	private Position3D getEndPos(Linie linie, Position2D offset) {
		Position3D pos = new Position3D(linie.getEndX() * schriftgroesse, linie.getEndY() * schriftgroesse, true);
		pos.add(offset);
		return pos;
	}

	private Position2D buchstabenOffset(ASCIIZeichen buchstabe) {

		return new Position2D(buchstabe.getMaxX() * schriftgroesse, 0);

	}

	private void offsetBerechnen(ASCIIZeichen letztesZeichen, ASCIIZeichen naechstesZeichen, int MaxY) {
		// Prüfen ob der nächste Buchstabe in die Zeile Passt
		if (naechstesZeichen != null) {

			if (offset.getX() + naechstesZeichen.getMaxX() < this.breite) {
				offset.add(buchstabenOffset(letztesZeichen));
				offset.add(platzZwischenBuchstaben);
			} else { // Nächste Zeile
				offset.setX(0);
				offset.setY(offset.getY() + (MaxY * schriftgroesse) + zeilenabstand); 
			}
		}

	}

}
