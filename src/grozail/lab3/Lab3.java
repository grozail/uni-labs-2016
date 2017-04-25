package grozail.lab3;

/**
 * Created by grozail on 9.10.16.
 *
 */
public class Lab3 {
	public Lab3() {
		try {
			Point2D p1 = new Point2D();
			p1.getXCoordinate();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Point2D p2 = new Point2D("A",1,1);
		System.out.println(p2.toString());
		p2.move(1,1);
		System.out.println(p2.toString());
		p2.turn(Math.PI/2);
		System.out.println(p2.toString());
	}
}
