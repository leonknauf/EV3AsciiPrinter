package BerechnungPosition;

import java.util.ArrayList;
import java.util.List;

import plott3r_V1_solved.Roboter;
import positions.Position2D;
import positions.Position3D;

public class Blatt {

	public static List<ASCIIZeichen> buchstaben = new ArrayList<>();
	public static Position2D offsetBuchstaben = new Position2D(0, 0);

	public static void main(String args[]) {
		
		
		buchstaben = LadeZeichen.importAsciiZeichen("zeichen.txt");
		Roboter r = null;
		try {
			r = new Roboter();
			//r.moveToPosition(new Position2D(20, 0),10 );
			//Button.waitForAnyPress();

		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i <= buchstaben.size(); i++) {
			List<Linie> linien = buchstaben.get(i).getLinien();
			
			
			for (Linie l : linien) {
				try {
					//LCD.drawInt(l.getStartX(), 1, 0);
					//LCD.drawInt(l.getStartY(), 5, 0);
					//LCD.drawInt(l.getEndX(), 1, 2);
					//LCD.drawInt(l.getEndY(), 10, 2);

					//Button.waitForAnyPress();
					
					
					r.moveToPosition(getStartPos(l), 10);
					r.moveToPosition(getEndPos(l), 10);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}

		}

	}

	private static Position3D getStartPos(Linie linie) {
		Position3D pos = new Position3D(linie.getStartX()*5, linie.getStartY()*5,false);
		return pos;

	}

	private static Position3D getEndPos(Linie linie) {
		Position3D pos = new Position3D(linie.getEndX()*5, linie.getEndY()*5,true);
		return pos;
	}

	private void buchstabenOffset(ASCIIZeichen buchstabe) {
		offsetBuchstaben.setX(offsetBuchstaben.getX() + buchstabe.getMaxX());

	}

}
