package BerechnungPosition;

import java.util.List;

import plott3r_V1_solved.Roboter;
import positions.Position2D;
import positions.Position3D;

public class Blatt {
	private Position2D platzZwischenBuchstaben = new Position2D(5, 0);
	private int geschwindigkeit = 10;
	// private Position2D offsetBuchstaben = new Position2D(0, 0);
	private Position2D offset = new Position2D(0, 0); // offset für den aktuellen Buchtsaben
	private Roboter roboter;

	public Blatt(Roboter roboter, int space) {
		platzZwischenBuchstaben.setX(space);
		this.roboter = roboter;
	}

	public Blatt(Roboter roboter) {
		this.roboter = roboter;
	}

	public void druckeZeichen(List<ASCIIZeichen> zeichen) {

		for (ASCIIZeichen z : zeichen) {
			druckeEinZeichen(z, offset);
		}

	}

	private void druckeEinZeichen(ASCIIZeichen zeichen, Position2D startposition) {

		for (Linie l : zeichen.getLinien()) {
			try {
				roboter.moveToPosition(getStartPos(l, startposition), geschwindigkeit);
				roboter.moveToPosition(getEndPos(l, startposition), geschwindigkeit);
				
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		offset.add(buchstabenOffset(zeichen));
		offset.add(platzZwischenBuchstaben);
		
		
		try {
			roboter.moveToPosition(new Position3D(offset, false), geschwindigkeit);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	

	}

	private Position3D getStartPos(Linie linie, Position2D offset) {

		Position3D pos = new Position3D(linie.getStartX() * 5, linie.getStartY() * 5, false);
		pos.add(offset);
		return pos;

	}

	private Position3D getEndPos(Linie linie, Position2D offset) {
		Position3D pos = new Position3D(linie.getEndX() * 5, linie.getEndY() * 5, true);
		pos.add(offset);
		return pos;
	}

	private Position2D buchstabenOffset(ASCIIZeichen buchstabe) {

		return new Position2D(buchstabe.getMaxX() * 5, 0);

	}

}
