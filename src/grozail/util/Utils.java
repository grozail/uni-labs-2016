package grozail.util;

/**
 * Created by grozail on 20.11.16.
 * utils
 */
public class Utils {
	public static String getFileExtension(String name) {
		int pointIndex = name.lastIndexOf('.');
		if (pointIndex == -1) {
			return null;
		}
		if (pointIndex == name.length() - 1) {
			return null;
		}
		return name.substring(pointIndex + 1);
	}
}
