package grozail.lab3;


/**
 * Created by grozail on 12.10.16.
 *
 */
public class Point2D implements ITurnable, IMoveable {
	private String name;
	private double xCoordinate;
	private double yCoordinate;

	public Point2D() {
		name = null;
		xCoordinate = Double.NaN;
		yCoordinate = Double.NaN;
	}

	public Point2D(String name) {
		this.name = name;
		xCoordinate = Double.NaN;
		yCoordinate = Double.NaN;
	}

	public Point2D(String name, double x, double y) {
		this.name = name;
		xCoordinate = x;
		yCoordinate = y;
	}

	public Point2D(double x, double y) {
		name = null;
		xCoordinate = x;
		yCoordinate = y;
	}


	public String toString() {
		return name + " {" + xCoordinate + ", " + yCoordinate + "}";
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setXCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setYCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public void setCoordinates(double xCoordinate, double yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public double getXCoordinate() throws IllegalArgumentException {
		if (Double.isNaN(xCoordinate)) {
			throw new IllegalArgumentException("Point is not defined!");
		}
		return xCoordinate;
	}

	public double getYCoordinate() throws IllegalArgumentException {
		if (Double.isNaN(yCoordinate)) {
			throw new IllegalArgumentException("Point is not defined!");
		}
		return yCoordinate;
	}


	public void move(double deltaX, double deltaY) throws IllegalArgumentException {
		if (Double.isNaN(xCoordinate) || Double.isNaN(yCoordinate)) {
			throw new IllegalArgumentException("Point is not defined!");
		}
		xCoordinate += deltaX;
		yCoordinate += deltaY;
	}


	public void turn(double deltaFI) throws IllegalArgumentException {
		if (Double.isNaN(xCoordinate) || Double.isNaN(yCoordinate)) {
			throw new IllegalArgumentException("Point is not defined!");
		}
		if (xCoordinate == 0 && yCoordinate == 0) {
			return;
		} else {
			double r = Math.sqrt(xCoordinate * xCoordinate + yCoordinate * yCoordinate);
			double FI = Math.atan(yCoordinate / xCoordinate);
			if (xCoordinate < 0) {
				FI = Math.PI - FI;
			}
			xCoordinate = r * Math.cos(FI + deltaFI);
			yCoordinate = r * Math.sin(FI + deltaFI);
		}
	}
}
