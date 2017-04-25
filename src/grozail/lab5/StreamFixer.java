package grozail.lab5;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/**
 * Created by grozail on 19.10.16.
 *
 */
public class StreamFixer {
	public static void streamFix(String inFileName) throws IOException {
		Scanner scanner = new Scanner(new File(inFileName));
		String line;
		Pattern pattern = Pattern.compile(".?(\\.\\d\\d\\d+)");
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			System.out.println(line);
			Matcher matcher = pattern.matcher(line);
			StringBuilder stringBuilder = new StringBuilder(line);
			StringBuilder subNum = new StringBuilder();
			while (matcher.find()) {
				subNum.append(matcher.group(1).substring(0,3));
				int startIndex = stringBuilder.indexOf(matcher.group(1));
				stringBuilder.replace(startIndex, startIndex + matcher.group(1).length(), subNum.toString());
				subNum.delete(0,subNum.length());
			}
			System.out.println(stringBuilder.toString() + "\n");
		}
	}
}
