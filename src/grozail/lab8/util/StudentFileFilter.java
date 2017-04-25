package grozail.lab8.util;

import grozail.util.Utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by grozail on 20.11.16.
 * filter
 */
public class StudentFileFilter extends FileFilter {
	public boolean accept(File file) {
		String name = file.getName();
		String extension = Utils.getFileExtension(name);
		return (extension != null && (extension.equals("sdb") || extension.equals("ssdb")) || file.isDirectory());
	}

	public String getDescription() {
		return "Students database files (*.sdb,*.ssdb)";
	}
}