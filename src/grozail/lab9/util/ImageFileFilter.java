package grozail.lab9.util;

import grozail.util.Utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by grozail
 * on 4.12.16.
 */
public class ImageFileFilter extends FileFilter {
	public boolean accept(File file) {
		String name = file.getName();
		String extension = Utils.getFileExtension(name);
		return (extension != null && (extension.equals("png") || extension.equals("jpg"))) || file.isDirectory();
	}

	public String getDescription() {
		return "Images (*.png, *.jpg)";
	}
}
