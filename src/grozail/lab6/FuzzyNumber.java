package grozail.lab6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by grozail on 24.10.16.
 *
 */
public class FuzzyNumber implements Comparable<FuzzyNumber> {
	private double ul, x, ur;
	private static Pattern pattern = Pattern.compile("<\\s*(\\d+\\.?\\d*),\\s*(\\d+\\.?\\d*),\\s*(\\d+\\.?\\d*)\\s*>");
	public FuzzyNumber(double ul, double x, double ur) {
		if (ul < 0) {
			this.ul = Double.NaN;
		}
		else {
			this.ul = ul;
		}
		if (ur < 0) {
			this.ur = Double.NaN;
		}
		else {
			this.ur = ur;
		}
		this.x = x;
	}

	public FuzzyNumber(String toParse) {
		Matcher matcher = pattern.matcher(toParse);
		if (matcher.find()) {
			ul = Double.valueOf(matcher.group(2)) - Double.valueOf(matcher.group(1));
			ur = Double.valueOf(matcher.group(3)) - Double.valueOf(matcher.group(2));
			if (ul < 0) {
				ul = Double.NaN;
			}
			if (ur < 0) {
				ur = Double.NaN;
			}
			x = Double.valueOf(matcher.group(2));
		}
	}

	public FuzzyNumber(FuzzyNumber toCopy) {
		ul = toCopy.getUl();
		x = toCopy.getX();
		ur = toCopy.getUr();
	}

	public double getUl() {
		return ul;
	}

	public double getUr() {
		return ur;
	}

	public double getX() {
		return x;
	}

	public static Pattern getPattern() {
		return pattern;
	}

	public void setUl(double ul) {
		if (ul < 0) {
			this.ul = Double.NaN;
		}
		else {
			this.ul = ul;
		}
	}

	public void setUr(double ur) {
		if (ur < 0) {
			this.ur = Double.NaN;
		}
		else {
			this.ur = ur;
		}
	}

	public void setX(double x) {
		this.x = x;
	}


	public boolean equals(Object obj) {
		if (obj instanceof FuzzyNumber) {
			FuzzyNumber toCompare = (FuzzyNumber) obj;
			if(toCompare.ul == ul && toCompare.ur == ur && toCompare.x == x) {
				return true;
			}
		}
		return false;
	}


	public String toString() {
		return "< " + (x - ul) + ", " + x + ", " + (x + ur) + " >";
	}

	public FuzzyNumber add(FuzzyNumber toAdd) throws IllegalArgumentException {
		if (Double.isNaN(ul) || Double.isNaN(ur) || Double.isNaN(toAdd.ul) || Double.isNaN(toAdd.ur)) {
			throw new IllegalArgumentException("adding exception");
		}
		return new FuzzyNumber(ul + toAdd.ul, x + toAdd.x, ur + toAdd.ur);
	}

	public FuzzyNumber sub(FuzzyNumber toSub) throws  IllegalArgumentException {
		if (Double.isNaN(ul) || Double.isNaN(ur) || Double.isNaN(toSub.ul) || Double.isNaN(toSub.ur)) {
			throw new IllegalArgumentException("subtraction exception");
		}
		return new FuzzyNumber(ul + toSub.ul, x - toSub.x, ul + toSub.ul);
	}

	public FuzzyNumber mul(FuzzyNumber toMul) throws IllegalArgumentException {
		if (Double.isNaN(ul) || Double.isNaN(ur) || Double.isNaN(toMul.ul) || Double.isNaN(toMul.ur)) {
			throw new IllegalArgumentException("multiplication exception");
		}
		return new FuzzyNumber(toMul.x * ul + x * toMul.ul + ul * toMul.ul, x * toMul.x, toMul.x * ur + x * toMul.ur + ur * toMul.ur);
	}

	public FuzzyNumber div(FuzzyNumber toDiv) throws IllegalArgumentException {
		if (toDiv.x <= 0 || Double.isNaN(ul) || Double.isNaN(ur) || Double.isNaN(toDiv.ul) || Double.isNaN(toDiv.ur)) {
			throw new IllegalArgumentException("cannot div by " + toDiv.toString());
		}
		return new FuzzyNumber("<" + ((x - ul) / (toDiv.x + toDiv.ur)) + "," + x/toDiv.x + "," + ((x+ur)/(toDiv.x - ul)) + ">");
	}

	public FuzzyNumber invert() throws IllegalArgumentException {
		if (x <= 0 || Double.isNaN(ul) || Double.isNaN(ur)) {
			throw new IllegalArgumentException("cannot invert: " + toString());
		}
		double begin, newx, end;
		begin = 1 / (x + ur);
		newx = 1 / x;
		end = 1 / (x - ul);
		return new FuzzyNumber(newx - begin, newx, end - newx);
	}


	public int compareTo(FuzzyNumber o) {
		return (ul + ur) < (o.ul + o.ur) ? -1 : (ul + ur) > (o.ul + o.ur) ? 1 : 0;
	}

	public void iterator() throws IllegalAccessException {
		throw new IllegalAccessException("Какой !@#$% придумал итерировать по полям класса?");
	}
}
