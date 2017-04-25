package grozail.lab10.util;

/**
 * Created by grozail
 * on 4.12.16.
 */
public class Message {
	private int x;
	private int y;
	private StringBuilder message;

	public Message() {
		message = new StringBuilder("String");
		x = 50;
		y = 50;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public StringBuilder getMessage() {
		return message;
	}

	public void setMessage(StringBuilder message) {
		this.message = message;
	}


	public String toString() {
		return message.toString();
	}
}
