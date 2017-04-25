package grozail.lab5;

import java.io.IOException;

/**
 * Created by grozail on 19.10.16.
 *
 */
public class Lab5 {
	public Lab5() {
		try {
			StreamFixer.streamFix("/opt/!ProjectsJava/UniLabs/src/grozail/lab5/input_lab5");
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
}
